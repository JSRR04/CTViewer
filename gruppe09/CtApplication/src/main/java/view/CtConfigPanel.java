package view;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

/**
 * The CtConfigPanel holds four main panels to show the patients data including the frontal-, sagittal- and horizontal-view of a ct from {@link aufgabe2.Person}.
 */
public class CtConfigPanel extends JPanel {

    private JPanel patientPanel, patientDataPanel, buttonPanel, frontalPanel, sagittalPanel, horizontalPanel;
    private JLabel patientDataLabel, nameLabel, birthLabel, weightLabel, heightLabel, izLabel,
            xCoordinatesLabel, yCoordinatesLabel, zCoordinatesLabel, frontalCtImageLabel, horizontalCtImageLabel, sagittalCtImageLabel;
    private JTextField nameText, birthText, weightText, heightText, izText, xCoordinatesText, yCoordinatesText, zCoordinatesText;
    private JButton saveButton, loadButton;
    private JSlider frontalSlider, sagittalSlider, horizontalSlider;

    /**
     * The CtConfigPanel instance creates a new set of panels for the frame.
     */
    public CtConfigPanel() {
        patientDataPanel();
        setFrontalPanel();
        setSagittalPanel();
        setHorizontalPanel();
    }

    /**
     * The method sets the {@link JPanel} for a patients data saved in an instance of {@link aufgabe2.Person}.
     * The center includes {@link JLabel} and {@link JTextField} for the patients
     * name, birthdate, weight, height, id as well as the x-y-z coordinates of a ct.
     * The south holds two {@link Button}, one to load a file and one to save it again.
     */
    private void patientDataPanel(){
        patientPanel = new JPanel(new BorderLayout());
        setPanelSize(0.04, 0.03, 0.4, 0.887 , patientPanel);
    // North: title
        patientPanel.add(patientDataLabel = new JLabel(), BorderLayout.NORTH);
    // Center: patient data
        patientDataPanel = new JPanel(new GridLayout(8,2));
        patientDataPanel.add(nameLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(nameText = new JTextField());
        patientDataPanel.add(birthLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(birthText = new JTextField());
        patientDataPanel.add(weightLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(weightText = new JTextField());
        patientDataPanel.add(heightLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(heightText = new JTextField());
        patientDataPanel.add(izLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(izText = new JTextField());
        patientDataPanel.add(xCoordinatesLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(xCoordinatesText = new JTextField());
        patientDataPanel.add(yCoordinatesLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(yCoordinatesText = new JTextField());
        patientDataPanel.add(zCoordinatesLabel = new JLabel("", SwingConstants.RIGHT));
        patientDataPanel.add(zCoordinatesText = new JTextField());
        patientPanel.add(patientDataPanel, BorderLayout.CENTER);
    // South: save & load
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loadButton = new JButton());
        buttonPanel.add(saveButton = new JButton());
        patientPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(patientPanel);
    }

    /**
     * The method creates a {@link JPanel} that shows a frontal (x-z) view of a ct.
     */
    private void setFrontalPanel(){
        frontalPanel = new JPanel();
        frontalSlider = new JSlider();
        frontalCtImageLabel = new JLabel();
        setPanelSize(0.47, 0.03, 0.28, 0.28, frontalPanel);
        setCtPanel(frontalPanel,frontalSlider,frontalCtImageLabel);
    }

    /**
     * The method creates a {@link JPanel} that shows a sagittal (y-z) view of a ct.
     */
    private void setSagittalPanel(){
        sagittalPanel = new JPanel();
        sagittalSlider = new JSlider();
        sagittalCtImageLabel = new JLabel();
        setPanelSize(0.67, 0.335, 0.28, 0.28, sagittalPanel);
        setCtPanel(sagittalPanel, sagittalSlider, sagittalCtImageLabel);
    }

    /**
     * The method creates a {@link JPanel} that shows a horizontal (x-y) view of a ct.
     */
    private void setHorizontalPanel(){
        horizontalPanel = new JPanel();
        horizontalSlider = new JSlider();
        horizontalCtImageLabel = new JLabel();
        setPanelSize(0.47, 0.64, 0.28, 0.28, horizontalPanel);
        setCtPanel(horizontalPanel, horizontalSlider, horizontalCtImageLabel);
    }

    /**
     * The method sets the coordinates for a {@link JPanel} in the {@link CtFrame}
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width width of the panel
     * @param height height of the panel
     * @param panel The according panel
     */
    private void setPanelSize(double x, double y, double width, double height, JPanel panel){
        int lp_x = (int) Math.round(CtFrame.getScreenWidth() * x);
        int lp_y = (int) Math.round(CtFrame.getScreenHeight() * y);
        int lp_width = (int) Math.round(CtFrame.getScreenWidth() * width);
        int lp_height = (int) Math.round(CtFrame.getScreenHeight() * height);
        panel.setBounds(lp_x, lp_y, lp_width, lp_height);
    }

    /**
     * The method sets the panels for the ct.
     *
     * @param ctPanel The {@link JPanel} you want to work with
     * @param slider The {@link JSlider} in the panel
     * @param ctImageLabel The {@link JLabel} that shows the ct
     */
    private void setCtPanel(JPanel ctPanel, JSlider slider, JLabel ctImageLabel){
        ctPanel.setLayout(new BorderLayout());
        slider.setValue(0);
        ctPanel.add(slider,BorderLayout.NORTH);
        ctImageLabel.setSize(ctPanel.getWidth()-50,ctPanel.getHeight()-50);
        ctPanel.add(ctImageLabel,BorderLayout.CENTER);
        this.add(ctPanel);
    }

    /**
     * The method sets texts according to the language given by the resource bundle.
     *
     * @param resourceBundle The ResourceBundle for the language.
     */
    public void setPanelLanguage(ResourceBundle resourceBundle){
        patientDataLabel.setText("                          "+resourceBundle.getString("patientData"));
        nameLabel.setText(resourceBundle.getString("name")+" : ");
        birthLabel.setText(resourceBundle.getString("birthday")+" : ");
        weightLabel.setText(resourceBundle.getString("weight")+" : ");
        heightLabel.setText(resourceBundle.getString("height")+" : ");
        izLabel.setText(resourceBundle.getString("iz")+" : ");
        xCoordinatesLabel.setText(resourceBundle.getString("xLabel")+" : ");
        yCoordinatesLabel.setText(resourceBundle.getString("yLabel")+" : ");
        zCoordinatesLabel.setText(resourceBundle.getString("zLabel")+" : ");

        frontalPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("frontalPanel")));
        sagittalPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("sagittalPanel")));
        horizontalPanel.setBorder(BorderFactory.createTitledBorder(resourceBundle.getString("horizontalPanel")));

        saveButton.setText(resourceBundle.getString("save")+":");
        loadButton.setText(resourceBundle.getString("load")+":");

        setPanelColor(patientPanel, resourceBundle);
        setPanelColor(patientDataPanel, resourceBundle);
        setPanelColor(buttonPanel, resourceBundle);
    }

    /**
     * Changes the background color of a {@link JPanel}.
     *
     * @param panel The according panel
     * @param resourceBundle The {@link ResourceBundle} in which the color properties are.
     */
    private void setPanelColor(JPanel panel, ResourceBundle resourceBundle){
        panel.setBackground(new Color(Integer.parseInt(resourceBundle.getString("red")),
                Integer.parseInt(resourceBundle.getString("green")),
                Integer.parseInt(resourceBundle.getString("blue"))));
    }

    public JTextField getNameText() {
        return nameText;
    }

    public JTextField getBirthText() { return birthText; }

    public JTextField getWeightText() { return weightText; }

    public JTextField getHeightText() { return heightText; }

    public JTextField getIzText() { return izText; }

    public JTextField getXCoordinatesText() { return xCoordinatesText; }

    public JTextField getYCoordinatesText() { return yCoordinatesText; }

    public JTextField getZCoordinatesText() { return zCoordinatesText; }

    public JButton getSaveButton() { return saveButton; }

    public JButton getLoadButton() { return loadButton; }

    public JSlider getFrontalSlider() { return frontalSlider; }

    public JSlider getSagittalSlider() { return sagittalSlider; }

    public JSlider getHorizontalSlider() { return horizontalSlider; }
    public JLabel getFrontalCtImageLabel() {
        return frontalCtImageLabel;
    }

    public JLabel getHorizontalCtImageLabel() {
        return horizontalCtImageLabel;
    }

    public JLabel getSagittalCtImageLabel() {
        return sagittalCtImageLabel;
    }
}