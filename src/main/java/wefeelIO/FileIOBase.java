package wefeelIO;

import java.io.File;

public class FileIOBase {

    public void deleteFile(String filename) {
        try {
            File deleteFile = new File(filename);
            deleteFile.deleteOnExit();
        } catch (Exception ex) {
            //do nothing, file just wasn't there to delete
            ex.printStackTrace();
        }
    }

}
