package view;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * The view for the {@link control.CtApplication} with a {@link CtConfigPanel} and a {@link CtMenuBar}
 */
public class CtFrame extends JFrame {

    private CtMenuBar ctMenuBar = new CtMenuBar();
    private CtConfigPanel ctConfigPanel = new CtConfigPanel();
    private static int screenWidth = 1000;
    private static int screenHeight = 1000;

    /**
     * The CtFrame creates a {@link JFrame} with a {@link CtConfigPanel} and a {@link CtMenuBar}
     */
    public CtFrame(){
        this.setContentPane(ctConfigPanel);
        this.setJMenuBar(ctMenuBar);

        this.setLayout(null);
        this.setSize(screenWidth, screenHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(123, 213, 253));

        this.setVisible(true);
    }

    /**
     * The method sets the language for the {@link CtFrame}, {@link CtConfigPanel} and the {@link CtMenuBar}.
     * @param resourceBundle The {@link ResourceBundle} for the properties of the given language.
     */
    public void setFrameLanguage(ResourceBundle resourceBundle){
        this.setTitle(resourceBundle.getString("ctViewer"));
        ctConfigPanel.setPanelLanguage(resourceBundle);
        ctMenuBar.setMenuBarLanguage(resourceBundle);
    }

    public CtMenuBar getCtMenuBar() {
        return ctMenuBar;
    }

    public CtConfigPanel getCtConfigPanel() {
        return ctConfigPanel;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenHeight() {
        return screenHeight;
    }
}