package interfaces;


import java.nio.file.*;

/**
 * Defines the possible conversions of ct files.
 */
public interface CtFileConverterInterface {

    /**
     * Abstract methode to define the methode which should convert a ct to a txt and bin file.
     *
     * @param ctFilePath is the given absolute ct file path for the file, which should be converted.
     */
    void ctToTxtAndBin(Path ctFilePath, Path folderPath);

    /**
     * Abstract methode to define the methode which should convert a txt and bin to a ct file.
     *
     * @param txtFile is the given absolute text file path for the file, which should be converted.
     * @param binFile is the given absolute bin file path for the file, which should be converted.
     */
    void txtAndBinToCt(Path txtFile, Path binFile,Path folderPath);
}
