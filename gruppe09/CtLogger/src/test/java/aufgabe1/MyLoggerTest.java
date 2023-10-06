package aufgabe1;

import org.junit.jupiter.api.*;

import java.io.*;
import java.time.*;
import java.time.format.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MyLoggerTest is a test class for {@link MyLogger}. additional information: if the test runs closely to a minute change
 * it could fail, because the runtime of the test could differ from the set log-time.
 */
class MyLoggerTest {
    private static MyLogger mltest;
    private static File testLogFile;
    private static String date;

    /**
     * Setup for all tests.
     * Creates a new {@link MyLogger} object and sets the formatters for the date and time.
     */
    @BeforeAll
    static void setup() {
        mltest = new LoggerFactory().getMyLogger();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        LocalDateTime actualDateTime = LocalDateTime.now();
        date = formatter.format((actualDateTime));
    }

    /**
     * Checks a String of an Info log.
     */
    @Test
    void logInfo() {
        mltest.logInfo("Test Info log");
        String testLog = mltest.getLogString();
        String expectedString = date + " INFO: aufgabe1.MyLoggerTest.logInfo: Test Info log";
        assertEquals(expectedString, testLog);
    }

    /**
     * Checks a String of a Warn log.
     */
    @Test
    void logWarn() {
        mltest.logWarn("Test Warn log");
        String testLog = mltest.getLogString();
        String expectedString = date + " WARN: aufgabe1.MyLoggerTest.logWarn: Test Warn log";
        assertEquals(expectedString, testLog);
    }

    /**
     * Checks a String of an Error log.
     */
    @Test
    void logError() {
        mltest.logError("Test Error log");
        String testLog = mltest.getLogString();
        String expectedString = date + " ERROR: aufgabe1.MyLoggerTest.logError: Test Error log";
        assertEquals(expectedString, testLog);
    }

    /**
     * Checks if a logger file exists and creates one, if it doesn't.
     */
    @Test
    void createLogFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = formatter.format(LocalDateTime.now());
        String filePath = date + "-loggerTest.txt";

        if (testLogFile == null || (!testLogFile.getName().equals(filePath))) {
            testLogFile = new File(filePath);
        }

        assertNotNull(testLogFile);
        assertEquals(testLogFile.getName(), date + "-loggerTest.txt");
    }
}