package wefeelIO;

import emotions.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class DataSyncJSONTest {
    private EmotionEntry myEmotionEntry1 = new EmotionEntry();

    @Before
    public void setUp() {

        myEmotionEntry1.addEmotion(new Joy(7));
        myEmotionEntry1.addEmotion(new Love(7));
        myEmotionEntry1.addEmotion(new Outlook(9));
        myEmotionEntry1.setEntrySituation(EmotionEntry.situations.Family);
        myEmotionEntry1.setEntryLocation(EmotionEntry.locations.Home);
        myEmotionEntry1.setEntryActivity(EmotionEntry.activities.Playing);
        myEmotionEntry1.setEntryNote("My spouse is awesome!");
    }

    @Test
    public void pushEntry() {

        DataSyncJSON<EmotionEntry> dataSync = new DataSyncJSON<>(myEmotionEntry1);
        dataSync.pushEntry("TestConnectionLog.txt");
        File checkFile = new File("TestConnectionLog.txt");
        Assert.assertTrue(checkFile.exists());
    }
}