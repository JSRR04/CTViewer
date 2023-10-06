package aufgabe2;



import java.nio.file.*;

/**
 * Example implementation of the CtConverter projekt. Insert the absolute path for the files.
 */
public class CtConverterDemoMain {
    public static void main(String[] args) {
        CtConverter cv = new CtConverter();
        Path saveFolder = Path.of("C:\\Users\\johan\\IdeaProjects\\gruppe09");
        cv.ctToTxtAndBin(Path.of("C:\\Users\\johan\\Desktop\\sampleData\\dataViewer2.ct"),saveFolder );
        cv.txtAndBinToCt(Path.of("C:\\Users\\johan\\Desktop\\sampleData\\dataViewer2.txt"), Path.of("C:\\Users\\johan\\Desktop\\sampleData\\dataViewer2.bin"), saveFolder);
    }
}
