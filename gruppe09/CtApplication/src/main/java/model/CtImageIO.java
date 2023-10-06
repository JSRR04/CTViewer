package model;

import aufgabe1.LoggerFactory;
import aufgabe1.MyLogger;
import aufgabe2.Person;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * creates the specific ct images for a {@link Person}.
 * Which was loaded in the ui.
 */
public class CtImageIO {

    private int[] pictureContrast;
    private MyLogger  ctApplicationLogger;

    public CtImageIO(MyLogger ctApplicationLogger) {
        this.ctApplicationLogger = ctApplicationLogger;
    }

    /**
     * defines the contrast of the Picture. Can not be higher than 255 or less than 0;
     *
     * @param minContrast sets the minimum value of the displayable grey-color.
     * @param maxContrast sets the maximum value of the displayable grey-color.
     */
    public void setPictureContrast(int minContrast, int maxContrast) {
        if (minContrast >= 0 && maxContrast <= 255) {
            this.pictureContrast = new int[]{minContrast, maxContrast};
        } else {
            if (pictureContrast == null) {
                setPictureContrast(0, 255);
                System.out.println("the contrast was set to default.");
            }
        }
    }

    /**
     * Gathers the ct information from the given person and writes three sets of images.
     * the sets can display the frontal, sagittal and horizontal view.
     *
     * @param ctImagePerson is the instance of the person which will be used to gather the specific data.
     */
    public void create2DctPictures(Person ctImagePerson)  throws IOException{
        ctApplicationLogger = new LoggerFactory().getMyLogger();
        setPictureContrast(0, 255);

        Short[][][] ctArray = ctImagePerson.getCtData();
        Integer[] ctDimension = ctImagePerson.getDimensionSize();

        // default Transversalebene/Horizontalebene (von oben)
        int zDimension = ctDimension[2]; // 2 = z-AXis
        int yDimension = ctDimension[1]; // 1 = y-Axis
        int xDimension = ctDimension[0]; // 0 = x-Axis

        // Folder creation
        Path ctImagesFolder = Path.of("CtImageFolder");
        if (new File(ctImagesFolder.toUri()).mkdir()) {
            ctApplicationLogger.logInfo("created successfully folder CtImageFolder");
        }

        File ctFrontal = new File(ctImagesFolder + File.separator + "CtFrontal");
        if (new File(ctFrontal.toURI()).mkdir()) {
            ctApplicationLogger.logInfo("created successfully folder CtFrontal.");
        }

        File ctSagittal = new File(ctImagesFolder + File.separator + "CtSagittal");
        if (new File(ctSagittal.toURI()).mkdir()) {
            ctApplicationLogger.logInfo("created successfully folder CtSagittal.");
        }

        File ctHorizontal = new File(ctImagesFolder + File.separator + "CtHorizontal");
        if (new File(ctHorizontal.toURI()).mkdir()) {
            ctApplicationLogger.logInfo("created successfully folder CtHorizontal.");
        }

        // frontal
        zDimension = ctDimension[2]; // z-axis size
        xDimension = ctDimension[0]; // x-axis size
        for (int layer = 0; layer < ctDimension[1]; layer++) {
            BufferedImage frontalImageSet = new BufferedImage(xDimension, zDimension, BufferedImage.TYPE_USHORT_GRAY);
            File frontal = new File(ctFrontal + File.separator + "frontalImage(" + layer + ").png");

            for (int b = 0; b < zDimension; b++) {                                                                  // b is the y point of the picture
                for (int a = 0; a < xDimension; a++) {                                                              // a is the x point of the picture
                    if (ctArray[a][layer][b] >= pictureContrast[1]) {
                        ctArray[a][layer][b] = (short) pictureContrast[1];                                          // adjusts the grey values to the possible of the setrgb methode
                    }
                    if (ctArray[a][layer][b] <= pictureContrast[0]) {
                        ctArray[a][layer][b] = (short) pictureContrast[0];                                          // adjusts the grey values to the possible of the setrgb methode
                    }
                    frontalImageSet.setRGB(a, (zDimension-1)-b, ctArray[a][layer][b]);                           // (zDimension-1)-b turns the z axis.
                }
            }

            // Hole das Graphics-Objekt zum Zeichnen
            Graphics graphics = frontalImageSet.getGraphics();

            // Setze die Schriftart und Farbe für den Text
            Font font = new Font("Arial", Font.BOLD, 12); // Schriftart und Größe anpassen
            graphics.setFont(font);
            graphics.setColor(Color.WHITE); // Farbe des Textes anpassen

            // Zeichne den Layer-Wert als Text auf das Bild
            String layerText = "Layer: " + (layer); // Adding 1 to layer to make it human-readable (layers start from 0)
            int textX = 5; // x-Position des Textes
            int textY = 10; // y-Position des Textes
            graphics.drawString(layerText, textX, textY);

            // Dispose des Graphics-Objekts, um Ressourcen freizugeben
            graphics.dispose();

                ImageIO.write(frontalImageSet, "png", frontal);
                System.out.println(frontal.getAbsolutePath());
        }
        ctApplicationLogger.logInfo("frontal image set was created");

        // sagittale
        zDimension = ctDimension[2]; // z-axis size
        yDimension = ctDimension[1]; // y-axis size
        for (int layer = 0; layer < ctDimension[0]; layer++) {
            BufferedImage sagittalImageSet = new BufferedImage(yDimension, zDimension, BufferedImage.TYPE_USHORT_GRAY);
            File sagittal = new File(ctSagittal + File.separator + "sagittalImage(" + layer + ").png");
            for (int b = 0; b < zDimension; b++) {                                                                  // b is the y point of the picture
                for (int a = 0; a < yDimension; a++) {                                                              // a is the x point of the picture
                    if (ctArray[layer][a][b] >= pictureContrast[1]) {
                        ctArray[layer][a][b] = (short) pictureContrast[1];                                          // adjusts the grey values to the possible of the setrgb methode
                    }
                    if (ctArray[layer][a][b] <= pictureContrast[0]) {
                        ctArray[layer][a][b] = (short) pictureContrast[0];                                          // adjusts the grey values to the possible of the setrgb methode
                    }
                    sagittalImageSet.setRGB((yDimension - 1) - a, (zDimension - 1) - b, ctArray[layer][a][b]);// (zDimension-1)-b turns the z axis. (yDimension-1)-a turns the x axis.
                }
            }

            // Hole das Graphics-Objekt zum Zeichnen
            Graphics graphics = sagittalImageSet.getGraphics();

            // Setze die Schriftart und Farbe für den Text
            Font font = new Font("Arial", Font.BOLD, 12); // Schriftart und Größe anpassen
            graphics.setFont(font);
            graphics.setColor(Color.WHITE); // Farbe des Textes anpassen

            // Zeichne den Layer-Wert als Text auf das Bild
            String layerText = "Layer: " + (layer); // Adding 1 to layer to make it human-readable (layers start from 0)
            int textX = 5; // x-Position des Textes
            int textY = 10; // y-Position des Textes
            graphics.drawString(layerText, textX, textY);

            // Dispose des Graphics-Objekts, um Ressourcen freizugeben
            graphics.dispose();

                ImageIO.write(sagittalImageSet, "png", sagittal);
                System.out.println(sagittal.getAbsolutePath());
        }
        ctApplicationLogger.logInfo("sagittal image set was created");

        // Transversal/Horizontal
        yDimension = ctDimension[1]; // y
        xDimension = ctDimension[0]; // x
        for (int layer = 0; layer < ctDimension[2]; layer++) {
            BufferedImage horizontalImageSet = new BufferedImage(xDimension, yDimension, BufferedImage.TYPE_USHORT_GRAY);
            File horizontal = new File(ctHorizontal + File.separator + "horizontalImage(" + layer + ").png");
            for (int b = 0; b < yDimension; b++) {                                                                  // b is the y point of the picture
                for (int a = 0; a < xDimension; a++) {                                                              // a is the x point of the picture
                    if (ctArray[a][b][layer] >= pictureContrast[1]) {
                        ctArray[a][b][layer] = (short) pictureContrast[1];                                          // adjusts the grey values to the possible of the setrgb methode
                    }
                    if (ctArray[a][b][layer] <= pictureContrast[0]) {
                        ctArray[a][b][layer] = (short) pictureContrast[0];                                          // adjusts the grey values to the possible of the setrgb methode
                    }
                    horizontalImageSet.setRGB((xDimension-1)-a, b, ctArray[a][b][layer]);                        // (xDimension-1)-a turns the x axis.
                }
            }

            // Hole das Graphics-Objekt zum Zeichnen
            Graphics graphics = horizontalImageSet.getGraphics();

            // Setze die Schriftart und Farbe für den Text
            Font font = new Font("Arial", Font.BOLD, 12); // Schriftart und Größe anpassen
            graphics.setFont(font);
            graphics.setColor(Color.WHITE); // Farbe des Textes anpassen

            // Zeichne den Layer-Wert als Text auf das Bild
            String layerText = "Layer: " + (layer); // Adding 1 to layer to make it human-readable (layers start from 0)
            int textX = 5; // x-Position des Textes
            int textY = 10; // y-Position des Textes
            graphics.drawString(layerText, textX, textY);

            // Dispose des Graphics-Objekts, um Ressourcen freizugeben
            graphics.dispose();

                ImageIO.write(horizontalImageSet, "png", horizontal);
                System.out.println(horizontal.getAbsolutePath());
        }
        ctApplicationLogger.logInfo("horizontal image set was created");

    }
}