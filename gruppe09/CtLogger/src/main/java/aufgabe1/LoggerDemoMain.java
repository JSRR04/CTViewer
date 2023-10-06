package aufgabe1;

/**
 * LoggerDemoMain is the main class for a log.
 */
public class LoggerDemoMain {

    /**
     * Returns an object that reports the logger information of a method.
     * Example code for functions presentation.
     * @param args The command line arguments.
     */
    public static void main(String[] args)  {

        MyLogger logger = new LoggerFactory().getMyLogger();
        logger.logInfo("Programm start");
        logger.logInfo("Programm ist erfolgreich gestartet");
        logger.logWarn("Programm beendet sich in 3 Sekunden.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.logError("Programm wird beendet");
    }
}