package control;

import aufgabe2.CtFileReader;
import aufgabe2.CtFileWriter;
import aufgabe2.Person;
import model.CtImageIO;
import view.CtFrame;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import aufgabe1.*;

/**
 * The CtApplication class adds actions to the {@link CtFrame}, {@link view.CtConfigPanel} and the {@link view.CtMenuBar}.
 */
public class CtApplication {

    public MyLogger ctApplicationLogger;
    private final CtFrame gui;
    private DateTimeFormatter dtf;
    private ResourceBundle resourceBundle;
    private Person person;

    /**
     * A CtApplication instance adds actions to the {@link CtFrame}, {@link view.CtConfigPanel} and the {@link view.CtMenuBar}.
     *
     * @param gui The frame to apply the actions to.
     */
    public CtApplication(CtFrame gui) {
        this.gui = gui;
        ctApplicationLogger = new LoggerFactory().getMyLogger();
        setLanguage("en", "US");
        gui.getCtMenuBar().getClose().addActionListener(e -> System.exit(0));
        languageItems();
        load();
        save();
    }

    /**
     * Action listener added to load a ct or a txt and bin file.
     */
    private void load() {
        gui.getCtMenuBar().getLoadFile().addActionListener(e -> loadFile());
        gui.getCtConfigPanel().getLoadButton().addActionListener(e -> loadFile());
    }

    /**
     * Reads the patient and the ct data from a file and displays it in the gui.
     */
    private void loadFile() {
        CtFileReader ctFileReader = new CtFileReader();
        JFileChooser fileChooser = new JFileChooser("CtConverter"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"sampleData");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("CT Data","ct", "txt", "bin");
        fileChooser.addChoosableFileFilter(fileFilter);

        fileChooser.showOpenDialog(fileChooser);
        Path path = fileChooser.getSelectedFile().toPath();

        if (path.toString().endsWith(".ct") || path.toString().endsWith(".txt") || path.toString().endsWith(".bin")) {
            if (path.toString().endsWith(".ct")) {
                person = ctFileReader.readCtFile(path);
                messageDialog("patience", "mightTakeAWhile");
            } else if (path.toString().endsWith(".txt")) {
                messageDialog("missingFile", "loadBin");
                fileChooser.removeChoosableFileFilter(fileFilter);
                fileChooser.setFileFilter(new FileNameExtensionFilter("bin", "bin"));
                fileChooser.addChoosableFileFilter(fileFilter);
                fileChooser.showOpenDialog(fileChooser);
                Path binPath = fileChooser.getSelectedFile().toPath();
                person = ctFileReader.readCtFile(path, binPath);
                messageDialog("patience", "mightTakeAWhile");
            } else if (path.toString().endsWith(".bin")) {
                messageDialog("missingFile", "loadTxt");
                fileChooser.removeChoosableFileFilter(fileFilter);
                fileChooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
                fileChooser.addChoosableFileFilter(fileFilter);
                fileChooser.showOpenDialog(fileChooser);
                Path txtPath = fileChooser.getSelectedFile().toPath();
                person = ctFileReader.readCtFile(txtPath, path);
                messageDialog("patience", "mightTakeAWhile");
            }
            gui.getCtConfigPanel().getNameText().setText(person.getName());
            setBirthday();
            gui.getCtConfigPanel().getWeightText().setText(String.valueOf(person.getWeight()));
            gui.getCtConfigPanel().getHeightText().setText(String.valueOf(person.getHeight()));
            gui.getCtConfigPanel().getIzText().setText(person.getIz());
            gui.getCtConfigPanel().getXCoordinatesText().setText(String.valueOf(person.getDimensionSize()[0]));
            gui.getCtConfigPanel().getXCoordinatesText().setEnabled(false);
            gui.getCtConfigPanel().getYCoordinatesText().setText(String.valueOf(person.getDimensionSize()[1]));
            gui.getCtConfigPanel().getYCoordinatesText().setEnabled(false);
            gui.getCtConfigPanel().getZCoordinatesText().setText(String.valueOf(person.getDimensionSize()[2]));
            gui.getCtConfigPanel().getZCoordinatesText().setEnabled(false);

            CtImageIO ctIO = new CtImageIO(ctApplicationLogger);
            try {
                if(person!=null){
                    ctIO.create2DctPictures(person);
                } else {
                    errorDialog("personIsNull");
                }
            } catch (IOException e) {
                errorDialog("writeImageError");
            }
            updateJSlider();
            messageDialog("confirmation", "successfulLoad");
        } else {
            errorDialog("wrongFileFormat");
        }
    }

    /**
     * Action listener added to save the data in a file.
     */
    private void save() {
        gui.getCtMenuBar().getSafeFile().addActionListener(e -> saveFile());
        gui.getCtConfigPanel().getSaveButton().addActionListener(e -> saveFile());
    }

    /**
     * Writes the patient and the ct data in a ct file.
     */
    private void saveFile() {
        CtFileWriter ctFileWriter = new CtFileWriter();
        JFileChooser fileChooser = new JFileChooser("runtime"+File.separator+"src"+File.separator+"main"+File.separator+"resources");
        fileChooser.showSaveDialog(fileChooser);
        Path path = fileChooser.getCurrentDirectory().toPath();
        try {
            Short[][][] ctData = person.getCtData();
            person = new Person(gui.getCtConfigPanel().getNameText().getText(),
                    LocalDate.parse(gui.getCtConfigPanel().getBirthText().getText(), DateTimeFormatter.ofPattern(resourceBundle.getString("dateFormatter"))),
                    Integer.parseInt(gui.getCtConfigPanel().getWeightText().getText()),
                    Integer.parseInt(gui.getCtConfigPanel().getHeightText().getText()),
                    gui.getCtConfigPanel().getIzText().getText(),
                    Integer.parseInt(gui.getCtConfigPanel().getXCoordinatesText().getText()),
                    Integer.parseInt(gui.getCtConfigPanel().getYCoordinatesText().getText()),
                    Integer.parseInt(gui.getCtConfigPanel().getZCoordinatesText().getText()));
            person.setCtData(ctData);
            ctFileWriter.writeCtFile(person, path);
            messageDialog("confirmation", "successfulSave");
        } catch (DateTimeParseException illegalDate){
            errorDialog("illegalDate");
        } catch (NullPointerException nullPointerException){
            errorDialog("emptyTextField");
        }
    }

    /**
     * gathers the different JSliders and adds a ChangeListener to it.
     * The ChangeListener will call a methode to update the ct pictures.
     * {@link #updateImage(JSlider, Path,JLabel, String)}
     */
    private void updateJSlider() {
        JLabel frontalLabel = gui.getCtConfigPanel().getFrontalCtImageLabel();
        JSlider frontal = gui.getCtConfigPanel().getFrontalSlider();
        frontal.setMinimum(0);
        frontal.setMaximum(person.getDimensionSize()[1] - 1);           // y axis
        updateImage(frontal, Path.of("CtImageFolder" + File.separator + "CtFrontal"), frontalLabel, "frontal");
        frontal.addChangeListener(e ->
                updateImage(frontal, Path.of("CtImageFolder" + File.separator + "CtFrontal"), frontalLabel, "frontal")
        );
        JLabel horizontalLabel = gui.getCtConfigPanel().getHorizontalCtImageLabel();
        JSlider horizontalSlider = gui.getCtConfigPanel().getHorizontalSlider();
        horizontalSlider.setMinimum(0);
        horizontalSlider.setMaximum(person.getDimensionSize()[2] - 1);  // z axis
        updateImage(horizontalSlider, Path.of("CtImageFolder" + File.separator + "CtHorizontal"), horizontalLabel, "horizontal");
        horizontalSlider.addChangeListener(e ->
                updateImage(horizontalSlider, Path.of("CtImageFolder" + File.separator + "CtHorizontal"), horizontalLabel, "horizontal")
        );
        JLabel sagittalLabel = gui.getCtConfigPanel().getSagittalCtImageLabel();
        JSlider sagittalSlider = gui.getCtConfigPanel().getSagittalSlider();
        sagittalSlider.setMinimum(0);
        sagittalSlider.setMaximum(person.getDimensionSize()[0] - 1);    // x axis
        updateImage(sagittalSlider, Path.of("CtImageFolder" + File.separator + "CtSagittal"), sagittalLabel, "sagittal");
        sagittalSlider.addChangeListener(e ->
                updateImage(sagittalSlider, Path.of("CtImageFolder" + File.separator + "CtSagittal"), sagittalLabel, "sagittal")
        );
        ctApplicationLogger.logInfo("All images were loaded successfully");
    }

    /**
     * loads the specific image for the position of the given JSlider.The picture will be customized.
     *
     * @param actualSlider JSlider which provides the actual position of itself.
     * @param imageFolder path of the image-folder.
     * @param actualLabel JLabel is the container for the image.
     * @param slider this String defines which Slider was handed to the methode.
     */
    private void updateImage(JSlider actualSlider, Path imageFolder, JLabel actualLabel, String slider) {
        BufferedImage bufferedCtImage = null;

        switch (slider) {
            case "frontal":
                try {
                    bufferedCtImage = ImageIO.read(new File(imageFolder + File.separator + "frontalImage(" + actualSlider.getValue() + ").png"));
                } catch (IOException e) {
                    errorDialog("ImageIOError");
                }
                break;
            case "sagittal":
                try {
                    bufferedCtImage = ImageIO.read(new File(imageFolder + File.separator + "sagittalImage(" + actualSlider.getValue() + ").png"));
                } catch (IOException e) {
                    errorDialog("ImageIOError");
                }
                break;
            case "horizontal":
                try {
                    bufferedCtImage = ImageIO.read(new File(imageFolder + File.separator + "horizontalImage(" + actualSlider.getValue() + ").png"));
                } catch (IOException e) {
                    errorDialog("ImageIOError");
                }
                break;
        }
        if (bufferedCtImage != null) {
            Image scaledImage = new ImageIcon(bufferedCtImage).getImage();
            ImageIcon ctImageResized = new ImageIcon(scaledImage.getScaledInstance(actualLabel.getWidth(), actualLabel.getHeight(), Image.SCALE_SMOOTH));
            actualLabel.setIcon(ctImageResized);
            actualLabel.repaint();
        } else {
            errorDialog("noPictureFound");
        }
    }

    /**
     * The method changes the language of the gui accordingly.
     */
    private void languageItems() {
        gui.getCtMenuBar().getEnglish().addActionListener(e -> setLanguage("en", "US"));
        gui.getCtMenuBar().getGerman().addActionListener(e -> setLanguage("de", "DE"));
    }

    /**
     * The method sets the language for the gui.
     *
     * @param language The wanted language
     * @param country The according country
     */
    private void setLanguage(String language, String country) {
        Locale locale = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle("MessageBundle", locale);
        dtf = DateTimeFormatter.ofPattern(resourceBundle.getString("dateFormatter"), locale);
        gui.setFrameLanguage(resourceBundle);
        setBirthday();
        ctApplicationLogger.logInfo("The language of the gui was changed to "+language);
    }

    /**
     * Sets the birthday to a string with the right format.
     *
     * @NotNull person
     */
    private void setBirthday(){
        try{
            if(this.person!=null) {
                LocalDate birthday = person.getBirth();
                String birthDate = birthday.format(dtf);
                gui.getCtConfigPanel().getBirthText().setText(birthDate);
            }
        } catch (DateTimeParseException illegalDate){
            errorDialog("illegalDate");
        }
    }

    /**
     * A plain message dialog.
     *
     * @param title the property for the title of the message
     * @param message the property for the message
     */
    private void messageDialog(String title, String message){
        JOptionPane.showMessageDialog(gui, resourceBundle.getString(message), resourceBundle.getString(title), JOptionPane.PLAIN_MESSAGE);
        ctApplicationLogger.logInfo(resourceBundle.getString(message));
    }

    /**
     * A message dialog for errors.
     *
     * @param error the property for an error message.
     */
    private void errorDialog(String error){
        JOptionPane.showMessageDialog(gui, resourceBundle.getString(error), resourceBundle.getString("error"), JOptionPane.ERROR_MESSAGE);
        ctApplicationLogger.logError(resourceBundle.getString(error));
    }
}