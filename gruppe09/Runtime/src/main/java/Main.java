import com.formdev.flatlaf.FlatIntelliJLaf;
import control.CtApplication;
import view.CtFrame;

/**
 * The Main class creates a frame in which a patients data can be loaded from a ct file or a txt and bin file,
 * changed, visualised and then again saved in a file.
 */
public class Main {

    public static void main(String[] args) {
        FlatIntelliJLaf.setup();
        CtFrame gui = new CtFrame();
        CtApplication controller = new CtApplication(gui);
    }
}