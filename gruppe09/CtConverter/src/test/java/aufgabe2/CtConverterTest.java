package aufgabe2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the {@link CtFileReader} and {@link CtFileWriter} class.
 * The tests will provide that the read and written files have the same information as the original file.
 */

class CtConverterTest {
    private static Person personReadCt;
    private static Person personWriteCt;
    private static Person personWriteTxtBin;
    private static Short[][][] originDimensions;
    private static Integer[] readDimensionsSize;
    private static Integer[] originDimSize;
    private static HashMap<Integer, String> originComments;

    /**
     * Sets up multiple person objects, for testing their read values.
     * Test folder and test data will be created.
     * have to be used with the specific test file testDataViewer. ct in the sampleData folder.
     */
    @BeforeAll
    static void setupPerson() {
        //todo ContextClassLoader + TempDir implementieren
        CtFileWriter cfw = new CtFileWriter();
        CtFileReader cfr = new CtFileReader();

        final Path testDataViewerCt = getResourceFromClasspath("testData"+File.separator+"testDataViewer.ct");
        assertNotNull(testDataViewerCt);

        personReadCt = cfr.readCtFile(testDataViewerCt);

        final Path testDataFolder = getResourceFromClasspath("testData");
        assertNotNull(testDataViewerCt);

        cfw.writeTxtAndBinFile(personReadCt,testDataFolder.toAbsolutePath());
        cfw.writeCtFile(personReadCt,testDataFolder.toAbsolutePath());

        final Path dataViewerCt = getResourceFromClasspath("testData"+File.separator+"ConvertedFiles"+File.separator+"dataViewer.ct");
        assertNotNull(testDataViewerCt);
        final Path dataViewerTxt = getResourceFromClasspath("testData"+File.separator+"ConvertedFiles"+File.separator+"dataViewer.txt");
        assertNotNull(testDataViewerCt);
        final Path dataViewerBin = getResourceFromClasspath("testData"+File.separator+"ConvertedFiles"+File.separator+"dataViewer.bin");
        assertNotNull(testDataViewerCt);

        personWriteCt = cfr.readCtFile(dataViewerCt);
        personWriteTxtBin = cfr.readCtFile(dataViewerTxt,dataViewerBin);

        originDimSize = new Integer[]{3, 2, 2};
        originDimensions = new Short[3][2][2];

        originDimensions[0][0][0] = 1;
        originDimensions[1][0][0] = 2;
        originDimensions[2][0][0] = 3;

        originDimensions[0][1][0] = 0;
        originDimensions[1][1][0] = 67;
        originDimensions[2][1][0] = 0;

        originDimensions[0][0][1] = 1;
        originDimensions[1][0][1] = 2;
        originDimensions[2][0][1] = 3;

        originDimensions[0][1][1] = 0;
        originDimensions[1][1][1] = 67;
        originDimensions[2][1][1] = 0;

        originComments = new HashMap<>();
        originComments.put(3, "Test comment in Row 3");
        originComments.put(6, "Test comment in Row 6");


    }

    /**
     * Tests if each point of the ct-Data is correctly read from the ct file.
     */
    @Test
    void writeCtDimensions() {

        Short[][][] readCtData = personReadCt.getCtData();
        for (int z = 0; z < originDimSize[2]; z++) {
            for (int y = 0; y < originDimSize[1]; y++) {
                for (int x = 0; x < originDimSize[0]; x++) {
                    assertEquals(readCtData[x][y][z], originDimensions[x][y][z]);
                }
            }
        }
    }

    /**
     * Compares the personal data from the created file with the read ct file.
     */
    @Test
    void writeCtPersonData() {
        assertEquals(personReadCt.getName(), personWriteCt.getName());
        assertEquals(personReadCt.getWeight(), personWriteCt.getWeight());
        assertEquals(personReadCt.getHeight(), personWriteCt.getHeight());
        assertEquals(personReadCt.getBirth(), personWriteCt.getBirth());
        assertEquals(personReadCt.getComments(), originComments);
        assertEquals(personReadCt.getImagePath(), personWriteCt.getImagePath());
        assertEquals(personReadCt.getIz(), personWriteCt.getIz());

    }

    /**
     * compares the read ct-file dimensions sizes.
     */
    @Test
    void writeCtDimensionSize() {
        readDimensionsSize = personReadCt.getDimensionSize();
        assertEquals(readDimensionsSize[0], originDimSize[0]);
        assertEquals(readDimensionsSize[1], originDimSize[1]);
        assertEquals(readDimensionsSize[2], originDimSize[2]);
    }

    /**
     * Tests if each point of the ct-Data is correctly read from bin file.
     */
    @Test
    void writeTxtBinCtDimensions() {
        Short[][][] readCtData = personWriteTxtBin.getCtData();
        for (int z = 0; z < originDimSize[2]; z++) {
            for (int y = 0; y < originDimSize[1]; y++) {
                for (int x = 0; x < originDimSize[0]; x++) {
                    assertEquals(readCtData[x][y][z], originDimensions[x][y][z]);
                }
            }
        }

    }

    /**
     * Compares the personal data from the created file with the read txt file.
     */
    @Test
    void writeTxtBinPersonData() {
        assertEquals(personReadCt.getName(), personWriteTxtBin.getName());
        assertEquals(personReadCt.getWeight(), personWriteTxtBin.getWeight());
        assertEquals(personReadCt.getHeight(), personWriteTxtBin.getHeight());
        assertEquals(personReadCt.getBirth(), personWriteTxtBin.getBirth());
        assertEquals(personReadCt.getComments(), originComments);
        assertEquals(personReadCt.getImagePath(), personWriteTxtBin.getImagePath());
        assertEquals(personReadCt.getIz(), personWriteTxtBin.getIz());
    }

    /**
     * compares the read txt-file dimension sizes.
     */
    @Test
    void writeTxtBinDimensionSize() {
        readDimensionsSize = personWriteTxtBin.getDimensionSize();
        assertEquals(readDimensionsSize[0], originDimSize[0]);
        assertEquals(readDimensionsSize[1], originDimSize[1]);
        assertEquals(readDimensionsSize[2], originDimSize[2]);
    }

    private static Path getResourceFromClasspath(@TempDir String resource) {
        final URL url = Thread.currentThread().getContextClassLoader().getResource(resource);

        if (url == null) {
            throw new RuntimeException("Could not find resource with name: " + resource);
        } else {
            try {
                return Path.of(url.toURI()).toAbsolutePath();
            } catch (URISyntaxException e) {
                throw new RuntimeException("Could not get URI from URL: " + url);
            }
        }
    }
}