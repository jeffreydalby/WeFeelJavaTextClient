package wefeelIO;

import org.junit.Test;

public class FileIOBaseTest {

    @Test
    public void deleteFile() {
        StringReadWriter readWriter = new StringReadWriter();
        readWriter.deleteFile("a6a5bfef-02e8-4e37-b138-ec4074cc59aa-emotions.dat");
    }
}