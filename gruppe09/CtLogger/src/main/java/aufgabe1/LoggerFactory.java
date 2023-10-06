package aufgabe1;

/**
 * LoggerFactory creates a new object of {@link MyLogger}.
 */
public class LoggerFactory {

    /**
     * Method creates a new {@link MyLogger} object.
     *
     * @return new {@link MyLogger} object.
     */
    public MyLogger getMyLogger() {
        return new MyLogger();
    }
}