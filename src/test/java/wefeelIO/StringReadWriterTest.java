package wefeelIO;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class StringReadWriterTest {

    @Test
    public void writeToFile() {
        StringReadWriter writer = new StringReadWriter();
        try {
            writer.writeToFile("this is a test", "StringReadWriterTest.txt",false);
            File checkFile = new File("StringReadWriterTest.txt");
            Assert.assertTrue(checkFile.exists());
        }
        catch (Exception ex){
            System.out.println("Failed to write test file");
            ex.printStackTrace();
        }
    }
}