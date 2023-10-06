package view;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * The class CtMenuBar holds the items for the menu bar.
 */
public class CtMenuBar extends JMenuBar {

    private final JMenuItem close;
    private final JMenuItem english = new JMenuItem("English");
    private final JMenuItem german = new JMenuItem("Deutsch");
    private final JMenuItem safeFile;
    private final JMenuItem loadFile;
    private final JMenu menu;
    private final JMenu file;
    private final JMenu changeLanguage;

    /**
     * A CtMenuBar instance holds different items for the menu bar.
     */
    public CtMenuBar(){
        menu = new JMenu();
        menu.add(close = new JMenuItem());
        this.add(menu);

        file = new JMenu();
        file.add(safeFile = new JMenuItem());
        file.add(loadFile = new JMenuItem());
        this.add(file);

        changeLanguage = new JMenu();
        changeLanguage.add(english);
        changeLanguage.add(german);
        this.add(changeLanguage);
    }

    /**
     * The method defines the texts according to the given language.
     *
     * @param resourceBundle The {@link ResourceBundle} that holds the properties of the wanted language.
     */
    public void setMenuBarLanguage(ResourceBundle resourceBundle){
        close.setText(resourceBundle.getString("exit"));
        safeFile.setText(resourceBundle.getString("save"));
        loadFile.setText(resourceBundle.getString("load"));
        menu.setText(resourceBundle.getString("menu"));
        file.setText(resourceBundle.getString("file"));
        changeLanguage.setText(resourceBundle.getString("language"));
    }

    public JMenuItem getClose(){
        return close;
    }

    public JMenuItem getEnglish() {
        return english;
    }

    public JMenuItem getGerman() {
        return german;
    }

    public JMenuItem getSafeFile() {
        return safeFile;
    }

    public JMenuItem getLoadFile() {
        return loadFile;
    }
}