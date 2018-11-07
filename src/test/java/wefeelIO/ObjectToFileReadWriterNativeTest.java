package wefeelIO;

import emotions.*;
import emotions.EmotionEntry.activities;
import emotions.EmotionEntry.locations;
import emotions.EmotionEntry.situations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// while forcing an order of testing isn't ideal, when dealing with testing fileIO we either have to
// have one test with multiple Asserts (which is bad) or force the order to ensure files are created
// which is also bad.  Choosing to force order as the lesser of two evils. Because of this method names
// are prefaced with a,b,c... to determine order they are run.

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ObjectToFileReadWriterNativeTest {
    private EmotionEntries myEmotionEntries;
    private EmotionEntry myEmotionEntry1 = new EmotionEntry();
    private EmotionEntry myEmotionEntry2 = new EmotionEntry();
    private EmotionEntry myEmotionEntry3 = new EmotionEntry();
    LocalDateTime entryDate = LocalDateTime.now(Clock.systemUTC());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    @Before
    public void setUp() {

        myEmotionEntries = new EmotionEntries();

        //build out our Emotion entries list
        myEmotionEntry1.addEmotion(new Joy(6));
        myEmotionEntry1.addEmotion(new Love(2));
        myEmotionEntry1.addEmotion(new Outlook(7));
        myEmotionEntry1.setEntrySituation(EmotionEntry.situations.Family);
        myEmotionEntry1.setEntryLocation(EmotionEntry.locations.Home);
        myEmotionEntry1.setEntryActivity(EmotionEntry.activities.Playing);
        myEmotionEntry1.setEntryNote("My spouse is awesome!");

        myEmotionEntry2.addEmotion(new Fear(8));
        myEmotionEntry2.addEmotion(new Sorrow(3));
        myEmotionEntry2.addEmotion(new Outlook(3));
        myEmotionEntry2.setEntrySituation(situations.Crowd);
        myEmotionEntry2.setEntryLocation(locations.Public);
        myEmotionEntry2.setEntryActivity(activities.Socializing);
        myEmotionEntry2.setEntryNote("Ahhh! Way too many people!");

        myEmotionEntry3.addEmotion(new Disgust(2));
        myEmotionEntry3.addEmotion(new Outlook(4));
        myEmotionEntry3.setEntrySituation(situations.Alone);
        myEmotionEntry3.setEntryLocation(locations.Work);
        myEmotionEntry3.setEntryActivity(activities.Working);
        myEmotionEntry3.setEntryNote("I'm so bored here at work!");

        myEmotionEntries.addEmotionEntry(myEmotionEntry1);
        myEmotionEntries.addEmotionEntry(myEmotionEntry2);
        myEmotionEntries.addEmotionEntry(myEmotionEntry3);

    }

    @Test
    public void aWriteObjectToFile() {
        try {

            ///make sure tests are valid by deleting testfile first
            try {
                File deleteFile = new File("emotionsTest.dat");
                deleteFile.delete();
                System.out.println("Deleted Test File");

            }
            catch (Exception ex)
            {
                //do nothing, file just wasn't there to delete
            }

            System.out.println("**********Writing file to disk**********");
            //write our file to disk.   This only validates that the file is written and not the format
            //the read object from file validates that we can convert the file back into EmotionEntries.
            ObjectToFileReadWriterNative<EmotionEntries> myReadWriter = new ObjectToFileReadWriterNative<>();
            myReadWriter.writeObjectToFile(myEmotionEntries,"emotionsTest.dat");

            //test that the file exists.
            File checkFile = new File("emotionsTest.dat");
            Assert.assertTrue(checkFile.exists());
            System.out.println("**********File Successfully Written**********");
        }
        catch (Exception ex)
        {
            System.out.println("Failed to write test file");
            System.out.println(ex);
        }
    }

    @Test
    public void bReadObjectFromFile() {
        try{

            System.out.println("**********Reading file into EmotionEntries Object");
            ObjectToFileReadWriterNative<EmotionEntries> myReadWriter = new ObjectToFileReadWriterNative<>();
            //we have to pass the EmotionEntries class to the reader so it know what type of object to convert to
            EmotionEntries testEntries = myReadWriter.readObjectFromFile("emotionsTest.dat", EmotionEntries.class);
            System.out.println("**********Successfully converted file to EmotionEntries Object:");
            System.out.println(testEntries);

        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }
    }
}