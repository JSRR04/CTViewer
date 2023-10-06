package aufgabe1;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * MyLogger is the implementation of interface {@link ILogger}. It provides methods for logging different log types.
 * It will use the defined log String from default method in {@link ILogger#createLog(MessageTypes, String)}.
 * console output and logging into file will be implemented here.
 */
public class MyLogger implements ILogger {

    private static File logFile;
    private String logString;

    /**
     * Constructor will initialize a log file, with method {@link #createLogFile()}.
     */
    public MyLogger() {
        createLogFile();
    }

    /**
     * Method writes the defined information log with custom message in a file and creates an output on the console.
     *
     * @param message String for additional information in the log.
     */
    @Override
    public void logInfo(String message) {
        logString = createLog(MessageTypes.INFO, message);
        logConsoleOutput();
        writeLogFile();
    }

    /**
     * Method writes the defined warning log with custom message in a file and creates an output on the console.
     *
     * @param message String for additional information in the log.
     */
    @Override
    public void logWarn(String message) {
        logString = createLog(MessageTypes.WARN, message);
        logConsoleOutput();
        writeLogFile();

    }

    /**
     * Method writes the defined error log with custom message in a file and creates an output on the console.
     *
     * @param message String for additional information in the log.
     */
    @Override
    public void logError(String message) {
        logString = createLog(MessageTypes.ERROR, message);
        logConsoleOutput();
        writeLogFile();
    }

    /**
     * Creates a text file named with the actual date.
     * To avoid duplicates, a singleton pattern was used.
     */
    private void createLogFile() {
        if (new File("Logs").mkdir()) {
            System.out.println("created successfully folder Logs at Root directory.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date = formatter.format(LocalDateTime.now());
        String folderPath = System.getProperty("user.dir") + File.separator + "Logs" + File.separator;
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        String filePath = folderPath + date + "_" + stacktrace[stacktrace.length-1].getClassName() + "-logger.txt";

        if (logFile == null || (!logFile.getName().equals(filePath))) {
            logFile = new File(filePath);
        }
    }

    /**
     * Will write the string with the log into the previously created file.
     */
    private void writeLogFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, true))) {

            bufferedWriter.write(logString);
            bufferedWriter.newLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Outputs the log into the console.
     */
    private void logConsoleOutput() {
        System.out.println(logString);
    }

    /**
     * Getter method for data field {@link #logFile}
     *
     * @return the text file which saves the logs.
     */
    public File getLogFile() {
        return logFile;
    }

    /**
     * Method for returning the last log.
     *
     * @return the string which holds the last log. {@link #logString}
     */
    public String getLogString() {
        return logString;
    }
}
