package aufgabe2;

import interfaces.CtFileConverterInterface;

import java.nio.file.*;

/**
 * Supplies two methods to convert ct,txt and bin files. Its used as an Interface.
 */
public class CtConverter implements CtFileConverterInterface {

    public CtConverter() {
    }

    /**
     * Provides a methode to convert ct files to a text and a bin file.
     * @param ctFilePath is the given absolute ct file path for the file, which should be converted.
     */
    @Override
    public void ctToTxtAndBin(Path ctFilePath,Path saveFolderPath) {
        try {
            CtFileWriter cfw = new CtFileWriter();
            CtFileReader ctr = new CtFileReader();
            cfw.writeTxtAndBinFile(ctr.readCtFile(ctFilePath),saveFolderPath);
        } catch (NullPointerException e) {
            throw new NullPointerException("the file have been corrupt some data could not be read.");
        } catch ( NumberFormatException e){
            throw new NumberFormatException("the file have been corrupt some data in the file was not properly formatted.");
        }
    }

    /**
     *  Provides a methode to convert a text and a bin file to ct file.
     *
     * @param txtFile is the given absolute text file path for the file, which should be converted.
     * @param binFile is the given absolute bin file path for the file, which should be converted.
     */
    @Override
    public void txtAndBinToCt(Path txtFile, Path binFile, Path saveFolderPath) {
        try {
            CtFileWriter cfw = new CtFileWriter();
            CtFileReader ctr = new CtFileReader();
            cfw.writeCtFile(ctr.readCtFile(txtFile, binFile),saveFolderPath);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new NullPointerException("the file have been corrupt some data could not be read.");
        } catch ( NumberFormatException e){
            e.printStackTrace();
            throw new NumberFormatException("the file have been corrupt some data in the file was not properly formatted.");
        }
    }
}
