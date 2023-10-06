
package aufgabe2;

import aufgabe1.LoggerFactory;
import aufgabe1.MyLogger;

import java.io.*;
import java.nio.file.*;
import java.time.format.DateTimeFormatter;

/**
 * The CtFileWriter class writes a {@link Person} into a ct file or into a text and bin file.
 * The txt file contains the name, birthdate, weight, height, special identification number and a path to a ct image.
 * The bin file contains the numbers that make up a ct.
 * The ct file contains the same elements as the txt and bin file combined.
 */
public class CtFileWriter {


    MyLogger myLogger = new LoggerFactory().getMyLogger();

    /**
     * The method creates a ct file and writes a {@link Person} into it.
     *
     * @param person The person to write into a file.
     */


    public void writeCtFile(Person person, Path folderPath) {
        folderPath = Path.of((folderPath + File.separator + "ConvertedFiles"));
        if (new File(folderPath.toUri()).mkdir()) {
            myLogger.logInfo("created successfully folder ConvertedFiles.");
        }
        myLogger.logInfo("ConvertedFiles folder ist at: " + folderPath);

        File ctFile = new File(folderPath + File.separator + "dataViewer.ct");
        writeTxt(ctFile, person, true);
    }


    /**
     * The method creates a txt and bin file and writes a {@link Person} them.
     *
     * @param person The person to write into the files.
     */
    public void writeTxtAndBinFile(Person person, Path folderPath) {
        //txt
        folderPath = Path.of(folderPath + File.separator + "ConvertedFiles");
        if (new File(folderPath.toUri()).mkdir()) {
            myLogger.logInfo("created successfully folder ConvertedFiles.");
        }
        myLogger.logInfo("ConvertedFiles folder ist at: " + folderPath);
        File txtFile = new File(folderPath + File.separator + "dataViewer.txt");
        writeTxt(txtFile, person, false);
        File binFile = new File(folderPath + File.separator + "dataViewer.bin");
        //bin
        try (DataOutputStream binWriter = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(binFile)))) {

            for (int z = 0; z < person.getDimensionSize()[2]; z++) {
                for (int y = 0; y < person.getDimensionSize()[1]; y++) {
                    for (int x = 0; x < person.getDimensionSize()[0]; x++) {
                        binWriter.writeShort(person.getCtData()[x][y][z]);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("The binary file could not be created!");
            e.printStackTrace();
        }
    }

    /**
     * The method writes the ct data from a {@link Person} into the according ct file.
     *
     * @param person   The person to write into the file.
     * @param ctWriter BufferedWriter to write into the file.
     */
    private void writeCt(Person person, BufferedWriter ctWriter) {
        try {

            for (int z = 0; z < person.getDimensionSize()[2]; z++) {
                if (z > 0) ctWriter.newLine();
                for (int y = 0; y < person.getDimensionSize()[1]; y++) {
                    ctWriter.write("\n");
                    for (int x = 0; x < person.getDimensionSize()[0]; x++) {
                        ctWriter.write(person.getCtData()[x][y][z].toString() + " ");
                    }
                }
            }
            myLogger.logInfo("The CT was written into the file.");
        } catch (IOException e) {
            System.err.println("The ct part could not be written into the file!");
            e.printStackTrace();
        }

    }

    /**
     * The method writes the name, birthday, weight, height and identification number of a patient from {@link Person},
     * as well as the dimension sizes of the ct into a txt file.
     *
     * @param filePath    Name of the txt file.
     * @param person      The according person, whose data will be written into the txt file.
     * @param isForCtFile true {{@link #writeCt(Person, BufferedWriter)}}
     */
    private void writeTxt(File filePath, Person person, Boolean isForCtFile) {
        try (BufferedWriter txtWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)))){

            Integer[] dimensionsSize = person.getDimensionSize();

            txtWriter.write("name\t" + person.getName());
            txtWriter.newLine();
            txtWriter.write("birth\t" + person.getBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            txtWriter.newLine();
            txtWriter.write("weight\t" + person.getWeight());
            txtWriter.newLine();
            txtWriter.write("height\t" + person.getHeight());
            txtWriter.newLine();
            txtWriter.write("IZ\t" + person.getIz());
            txtWriter.newLine();
            if(person.getImagePath()!=null){
            txtWriter.write("image\t" + person.getImagePath());
            }
            myLogger.logInfo("The Personal data was written into the file.");
            txtWriter.newLine();
            txtWriter.write("DATA");
            txtWriter.newLine();
            txtWriter.write(dimensionsSize[0].toString());
            txtWriter.newLine();
            txtWriter.write(dimensionsSize[1].toString());
            txtWriter.newLine();
            txtWriter.write(dimensionsSize[2].toString());
            if (isForCtFile) {
                writeCt(person, txtWriter);
            }
        } catch (IOException e) {
            System.err.println("The text part could not be created!");
            e.printStackTrace();
        }
    }
}