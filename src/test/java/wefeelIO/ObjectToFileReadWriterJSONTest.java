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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ObjectToFileReadWriterJSONTest {

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
        myEmotionEntry1.addEmotion(new Joy(7));
        myEmotionEntry1.addEmotion(new Love(7));
        myEmotionEntry1.addEmotion(new Outlook(9));
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
            //make sure our tests are valid by clearing out the test file(s)
            try {
                File deleteFile = new File("readwriterJSONTest.json");
                if(deleteFile.delete())
                    System.out.println("Deleted Test File");
                else
                    System.out.println("Failed to delete file");

            }
            catch (Exception ex)
            {
                //do nothing, file just wasn't there to delete
            }


            System.out.println("**********Writing file to disk**********");
            //write our file to disk.   This only validates that the file is written and not the format
            //the read object from file validates that we can convert the file back into EmotionEntries.
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(myEmotionEntries,"readwriterJSONTest.json");

            //test that the file exists.
            File checkFile = new File("readwriterJSONTest.json");
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
            System.out.println("**********Converting JSON file to EmotionEntry Object");
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            //we have to pass the EmotionEntries class to the reader so it know what type of object to convert to
            EmotionEntries testEntries = myReadWriter.readObjectFromFile("readwriterJSONTest.json", EmotionEntries.class);
            System.out.println("**********Successfully converted JSON file to EmotionEntry Object:");
            System.out.println(testEntries);

        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }
    }
}