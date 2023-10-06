package aufgabe1;

import java.time.*;
import java.time.format.*;

/**
 * Interface that defines the needed log methods and the structure of a log String.
 */
public interface ILogger {

    /**
     * Method creates a log String for further actions with a predefined structure and a custom message.
     *
     * @param mt      Represents the predefined log type.
     * @param message String for additional information in the log.
     * @return logString, the created log String to the caller.
     */
    default String createLog(MessageTypes mt, String message) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss");
        LocalDateTime actualDateTime = LocalDateTime.now();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // String with date, time, message type, class name, method name, logger message
        String logString = formatter.format((actualDateTime)) + " " +
                mt + ": " +
                stackTraceElements[3].getClassName() + "." +
                stackTraceElements[3].getMethodName() + ": " +
                message;

        return logString;
    }

    /**
     * The abstract methode is for logging information. example: {@link MyLogger#logInfo(String)}
     *
     * @param message String for additional information in the log.
     */
    void logInfo(String message);

    /**
     * The abstract methode is for logging warnings. example: {@link MyLogger#logWarn(String)}
     *
     * @param message String for additional information in the log.
     */
    void logWarn(String message);

    /**
     * The abstract methode is for error logging. example: {@link MyLogger#logError(String)}
     *
     * @param message String for additional information in the log.
     */
    void logError(String message);

}
