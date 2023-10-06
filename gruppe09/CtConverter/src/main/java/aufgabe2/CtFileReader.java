package aufgabe2;

import aufgabe1.LoggerFactory;
import aufgabe1.MyLogger;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The CtFileReader reads the contents from a ct file or a txt and bin file and saves them in a {@link Person}.
 */
public class CtFileReader {
    private final MyLogger ctLogger;

    /**
     * Initializes a new logger from {@link MyLogger} and {@link LoggerFactory}.
     */
    public CtFileReader() {
        ctLogger = new LoggerFactory().getMyLogger();
    }

    /**
     * The method reads two files (a txt and a bin file) and saves their contents in a {@link Person}.
     *
     * @param txtFilePath {@code not null} The path of a txt file, containing a persons' data.
     * @param binFilePath {@code not null} The path of a bin file, containing the ct data.
     * @return phold, a new {@link Person} with the read data from the txt and bin file.
     * @throws FileNotFoundException If the file could not be found.
     */
    public Person readCtFile(Path txtFilePath, Path binFilePath) {
        Person phold = new Person();
        if (txtFilePath.toString().endsWith(".txt") && binFilePath.toString().endsWith(".bin")) {
            ctLogger.logInfo("trying to read for object:" + phold);
            try {
                readFile(txtFilePath, phold);
                readFile(binFilePath, phold);
                return phold;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ctLogger.logWarn("Wrong file format. Pls use one ct file or txt and bin file.");
        }
        return phold;
    }

    /**
     * The method reads one ct file and saves its contents in a {@link Person}.
     *
     * @param ctFilePath {@code not null} The path of a ct file, containing a persons' as well as their ct data.
     * @return phold, a new {@link Person} with the read data from the ct file.
     * @throws FileNotFoundException If the file could not be found.
     */
    public Person readCtFile(Path ctFilePath) {
        Person phold = new Person();
        if (ctFilePath.toString().endsWith(".ct")) {
            ctLogger.logInfo("trying to read for object:" + phold);
            try {
                readFile(ctFilePath, phold);
                return phold;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ctLogger.logWarn("Wrong file format. Pls use one ct file or txt and bin file.");
        }
        return phold;
    }

    /**
     * The method creates a new file with the given path and reads the data from it.
     *
     * @param filePath {@code not null} The path to a file.
     * @param phold    {@code not null} The {@link Person} to save the files data in.
     * @throws FileNotFoundException If the file could not be found.
     */
    protected void readFile(Path filePath, Person phold) throws FileNotFoundException {
        File ctFile = new File(filePath.toString());
        String actualLine;
        String actualKeyWord;
        int lineCounter = 1;
        HashMap<Integer, String> comments = new HashMap<>();
        if (new File(System.getProperty("user.dir") + File.separator + "ConvertedFiles").mkdir()) {
            ctLogger.logInfo("created successfully folder ConvertedFiles.");
        }
        String[] stringArray;
        if (ctFile.getName().endsWith(".bin")) {
            readBinaryCt(phold, ctFile);
        }
        if (ctFile.getName().endsWith(".ct") | ctFile.getName().endsWith(".txt")) {
            try (BufferedReader br = new BufferedReader(new FileReader(ctFile))) {
                while ((actualLine = br.readLine()) != null) {
                    stringArray = actualLine.split("\t");
                    actualKeyWord = stringArray[0];

                    readFileSwitch(br, actualKeyWord, stringArray, ctLogger, phold, lineCounter, comments);
                    lineCounter++;
                }
            } catch (IOException e) {
                ctLogger.logError("Failure at Reading Txt- or CT-File");
                e.printStackTrace();
            }
        }
    }


    /**
     * The method saves the persons name, birthdate, weight, height, identification number, image path, comments (if there are any) and the ct data into the given {@link Person}.
     *
     * @param br            A {@link BufferedReader} to read the data.
     * @param actualKeyWord {@code not null} The first part of the line.
     * @param stringArray   The second part of the line.
     * @param ctLogger      a {@link MyLogger} object.
     * @param phold         a {@link Person} object.
     * @param lineCounter   Counts the previous lines of the file.
     * @param comments      The comments of a file.
     * @throws IOException If something goes wrong with the in or output.
     */
    private void readFileSwitch(BufferedReader br, String actualKeyWord, String[] stringArray, MyLogger ctLogger, Person phold, int lineCounter, HashMap<Integer, String> comments) throws IOException {
        switch (actualKeyWord) {
            case "name" -> {
                phold.setName(stringArray[1]);
                ctLogger.logInfo("Name was read and saved.");
            }
            case "birth" -> {
                phold.setBirth(LocalDate.parse(stringArray[1], DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                ctLogger.logInfo("Birthdate was read and saved.");
            }
            case "weight" -> {
                phold.setWeight(Integer.parseInt(stringArray[1]));
                ctLogger.logInfo("Weight was read and saved.");
            }
            case "height" -> {
                phold.setHeight(Integer.parseInt(stringArray[1]));
                ctLogger.logInfo("Height was read and saved.");
            }
            case "IZ" -> {
                phold.setIz(stringArray[1]);
                ctLogger.logInfo("Identification Number was read and saved.");
            }
            case "image" -> {
                phold.setImagePath(stringArray[1]);
                ctLogger.logInfo("Image path was read and saved.");
            }
            case "#" -> {
                comments.put(lineCounter, stringArray[1]);
                phold.setComments(comments);

            }
            case "DATA" -> readCtData(br, phold);
        }
    }

    /**
     * Handles the ct-data part of a ct file.
     * It will read the given dimension size and the values for each point.
     * The progress of reading the ct data will be shown.
     *
     * @param br    BufferedReader from the caller which will continue reading the file.
     * @param phold an instance of Person for saving the information.
     * @throws IOException when the buffer instance has failed or been interrupted.
     */
    private void readCtData(BufferedReader br, Person phold) {
        try {
            int xDimension = Integer.parseInt(br.readLine());
            int yDimension = Integer.parseInt(br.readLine());
            int zDimension = Integer.parseInt(br.readLine());

            Short[][][] dimensions = new Short[xDimension + 1][yDimension + 1][zDimension + 1];
            phold.setDimensionSize(new Integer[]{xDimension, yDimension, zDimension});
            System.out.printf("\nDimensions of the CT-Data [X,Y,Z]:%s \n", Arrays.toString(phold.getDimensionSize()));
            br.mark(0);
            if (br.readLine() != null) {
                br.reset();
                for (int z = 0; z < zDimension; z++) {
                    //shows the progress of reading the ct-data
                    // reads the empty line after each block
                    if (z > 0) {
                        br.readLine();
                    }
                    for (int y = 0; y < yDimension; y++) {
                        String[] xValuesString = br.readLine().split("\\s");
                        if ((z > 0) && (y == yDimension - 1)) {
                            System.out.printf("CT-Block %d was successful read from %d blocks. \n", z, zDimension);
                        }
                        for (int x = 0; x < xDimension; x++) {
                            dimensions[x][y][z] = Short.parseShort(xValuesString[x]);
                        }
                    }
                }
                System.out.printf("CT-Block %d was successful read from %d blocks. \n", zDimension, zDimension);
                ctLogger.logInfo("All CT blocks were successful read.");
                phold.setCtData(dimensions);
            } else {
                ctLogger.logInfo("No CT-Data in the specific file. Will be read from bin file if path was given.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the ct-data part of a bin file.
     * It will read the predefined dimension size and the values for each point of it.
     * The progress of reading the ct data will be shown.
     *
     * @param phold an instance of Person for saving the information.
     * @param bin   bin file which should be read.
     */
    private void readBinaryCt(Person phold, File bin) {
        Integer[] dimensionSize = phold.getDimensionSize();
        Short[][][] dimensions = new Short[dimensionSize[0]][dimensionSize[1]][dimensionSize[2]];
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(bin)))) {
            for (int z = 0; z < dimensionSize[2]; z++) {
                System.out.printf("CT-Block %d was successful read from %d blocks. \n", z, dimensionSize[2]);
                for (int y = 0; y < dimensionSize[1]; y++) {
                    for (int x = 0; x < dimensionSize[0]; x++) {
                        dimensions[x][y][z] = dis.readShort();
                    }
                }
            }
            System.out.printf("CT-Block %d was successful read from %d blocks. \n", dimensionSize[2], dimensionSize[2]);
            ctLogger.logInfo("All CT blocks were successful read.");
            phold.setCtData(dimensions);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}