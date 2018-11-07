package wefeel;

import emotions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import emotions.EmotionEntry.activities;
import emotions.EmotionEntry.locations;
import emotions.EmotionEntry.situations;

import java.io.File;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


//Test to ensure that OnExit code writes to file as needed
public class OnExitTest {

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
        myEmotionEntry1.addEmotion(new Joy(5));
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
    public void saveEntries() {
        try {
            //make sure our tests are valid by clearing out the test file(s)

            try {
                File deleteFile = new File("testExitEntries.dat");
                deleteFile.delete();
                System.out.println("Deleted Test File");

            } catch (Exception ex) {
                //do nothing, file just wasn't there to delete
            }

            OnExit.saveEntries(myEmotionEntries, "testExitEntries.dat");
            File checkFile = new File("testExitEntries.dat");
            Assert.assertTrue(checkFile.exists());
            System.out.println("**********File Successfully Written**********");
        }
        catch (Exception ex)
        {
            System.out.println("Failed to write test file");
            System.out.println(ex);
        }

    }
}