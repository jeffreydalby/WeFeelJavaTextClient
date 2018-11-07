package emotions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class EmotionEntryTest {

    private EmotionEntry myEmotionEntry;
    private Emotion joy;
    private Emotion sorrow;
    private Emotion fear;
    private Emotion love;
    private Emotion disgust;
    private Emotion anger;
    private Emotion outlook;

    @Before
    public void setUp() {
        joy = new Joy(){};
        sorrow = new Sorrow();
        fear = new Fear();
        love = new Love();
        disgust = new Disgust();
        anger = new Anger();
        outlook = new Outlook();
        myEmotionEntry = new EmotionEntry();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddEmotion() {
        myEmotionEntry.addEmotion(joy);
        myEmotionEntry.addEmotion(outlook);
        myEmotionEntry.addEmotion(fear);
        myEmotionEntry.setEntryLocation(EmotionEntry.locations.Home);
        myEmotionEntry.setEntryActivity(EmotionEntry.activities.Relaxing);
        myEmotionEntry.setEntrySituation(EmotionEntry.situations.Family);

        Map<String, Emotion> myEmotions = myEmotionEntry.getEmotions();
        //validate Outlook Emotion as added to the list and can be found
        Assert.assertSame(myEmotions.get("Outlook"),outlook );
    }

    /**
     * setup an Emotion entry and make sure we can output it to the console to verify it was created properly.
     */
    @Test
    public void testToString() {
        joy.setIntensity(8.0);
        outlook.setIntensity(9.0);
        fear.setIntensity(1.0);
        myEmotionEntry.addEmotion(joy);
        myEmotionEntry.addEmotion(outlook);
        myEmotionEntry.addEmotion(fear);
        myEmotionEntry.setEntryLocation(EmotionEntry.locations.Home);
        myEmotionEntry.setEntryActivity(EmotionEntry.activities.Relaxing);
        myEmotionEntry.setEntrySituation(EmotionEntry.situations.Family);
        myEmotionEntry.setEntryNote("Everything is Awesome");

        LocalDateTime entryDate = LocalDateTime.now(Clock.systemUTC());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        String outString = myEmotionEntry.toString();
        Assert.assertEquals("On " + entryDate.format(dtf) + ", you entered that you felt that\n"+
                "Your Fear level was: 1.0\n" +
                "Your Joy level was: 8.0\n" +
                "Your Outlook level was: 9.0\n" +
                "Your Location was: Home\n" +
                "You were: Relaxing\n" +
                "Your Social Situation was: Family\n" +
                "You noted that: Everything is Awesome" + "\n",outString);
        System.out.println(myEmotionEntry);

      }
}