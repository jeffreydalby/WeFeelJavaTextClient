package emotions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmotionEntriesTest {

    private EmotionEntries myEmotionEntries;
    private EmotionEntry myEmotionEntry1 = new EmotionEntry();
    private EmotionEntry myEmotionEntry2 = new EmotionEntry();
    private EmotionEntry myEmotionEntry3 = new EmotionEntry();
    private EmotionEntry myEmotionEntry4 = new EmotionEntry();
    private EmotionEntry myEmotionEntry5 = new EmotionEntry();

    LocalDateTime entryDate = LocalDateTime.now(Clock.systemUTC());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");


    @Before
    public void setUp() {

        myEmotionEntries = new EmotionEntries();

        myEmotionEntry1.addEmotion(new Joy(8));
        myEmotionEntry1.addEmotion(new Love(7));
        myEmotionEntry1.addEmotion(new Outlook(9));
        myEmotionEntry1.setEntrySituation(EmotionEntry.situations.Family);
        myEmotionEntry1.setEntryLocation(EmotionEntry.locations.Home);
        myEmotionEntry1.setEntryActivity(EmotionEntry.activities.Playing);
        myEmotionEntry1.setEntryNote("My spouse is awesome!");

        myEmotionEntry2.addEmotion(new Fear(8));
        myEmotionEntry2.addEmotion(new Sorrow(3));
        myEmotionEntry2.addEmotion(new Outlook(3));
        myEmotionEntry2.setEntrySituation(EmotionEntry.situations.Crowd);
        myEmotionEntry2.setEntryLocation(EmotionEntry.locations.Public);
        myEmotionEntry2.setEntryActivity(EmotionEntry.activities.Socializing);
        myEmotionEntry2.setEntryNote("Ahhh! Way too many people!");

        myEmotionEntry3.addEmotion(new Disgust(2));
        myEmotionEntry3.addEmotion(new Outlook(4));
        myEmotionEntry3.setEntrySituation(EmotionEntry.situations.Alone);
        myEmotionEntry3.setEntryLocation(EmotionEntry.locations.Work);
        myEmotionEntry3.setEntryActivity(EmotionEntry.activities.Working);
        myEmotionEntry3.setEntryNote("I'm so bored here at work!");

        myEmotionEntry4.addEmotion(new Sorrow(8));
        myEmotionEntry4.addEmotion(new Outlook(2));
        myEmotionEntry4.setEntrySituation(EmotionEntry.situations.Alone);
        myEmotionEntry4.setEntryLocation(EmotionEntry.locations.Work);
        myEmotionEntry4.setEntryActivity(EmotionEntry.activities.Working);
        myEmotionEntry4.setEntryNote("Everything sucks today!");

        myEmotionEntry5.addEmotion(new Joy(8));
        myEmotionEntry5.addEmotion(new Outlook(5));
        myEmotionEntry5.setEntrySituation(EmotionEntry.situations.Alone);
        myEmotionEntry5.setEntryLocation(EmotionEntry.locations.Work);
        myEmotionEntry5.setEntryActivity(EmotionEntry.activities.Working);
        myEmotionEntry5.setEntryNote("Finally an okay day!");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addEmotionEntry() {
        myEmotionEntries.addEmotionEntry(myEmotionEntry1);
        myEmotionEntries.addEmotionEntry(myEmotionEntry2);
        myEmotionEntries.addEmotionEntry(myEmotionEntry3);

       String theEntries = myEmotionEntries.toString();

        String expectedResult = "On " + entryDate.format(dtf) + ", you entered that you felt that\n" +
                "Your Joy level was: 8.0\n" +
                "Your Outlook level was: 9.0\n" +
                "Your Love level was: 7.0\n" +
                "Your Location was: Home\n" +
                "You were: Playing\n" +
                "Your Social Situation was: Family\n" +
                "You noted that: My spouse is awesome!\n" +
                "\n" +
                "On " + entryDate.format(dtf) + ", you entered that you felt that\n" +
                "Your Fear level was: 8.0\n" +
                "Your Outlook level was: 3.0\n" +
                "Your Sorrow level was: 3.0\n" +
                "Your Location was: Public\n" +
                "You were: Socializing\n" +
                "Your Social Situation was: Crowd\n" +
                "You noted that: Ahhh! Way too many people!\n" +
                "\n" +
                "On " + entryDate.format(dtf) + ", you entered that you felt that\n" +
                "Your Disgust level was: 2.0\n" +
                "Your Outlook level was: 4.0\n" +
                "Your Location was: Work\n" +
                "You were: Working\n" +
                "Your Social Situation was: Alone\n" +
                "You noted that: I'm so bored here at work!\n\n";

        Assert.assertEquals(theEntries,expectedResult);

        System.out.println(myEmotionEntries);
    }

    @Test
    public void getLowOutlookEntires() {
        myEmotionEntries.addEmotionEntry(myEmotionEntry1);
        myEmotionEntries.addEmotionEntry(myEmotionEntry2);
        myEmotionEntries.addEmotionEntry(myEmotionEntry3);
        myEmotionEntries.addEmotionEntry(myEmotionEntry4);
        myEmotionEntries.addEmotionEntry(myEmotionEntry5);

        EmotionEntries lowEntries = myEmotionEntries.getLowOutlookEntries();
        String expectedResult = "On " + entryDate.format(dtf) + ", you entered that you felt that\n" +
                "Your Fear level was: 8.0\n" +
                "Your Outlook level was: 3.0\n" +
                "Your Sorrow level was: 3.0\n" +
                "Your Location was: Public\n" +
                "You were: Socializing\n" +
                "Your Social Situation was: Crowd\n" +
                "You noted that: Ahhh! Way too many people!\n" +
                "\n" +
                "On " + entryDate.format(dtf) + ", you entered that you felt that\n" +
                "Your Disgust level was: 2.0\n" +
                "Your Outlook level was: 4.0\n" +
                "Your Location was: Work\n" +
                "You were: Working\n" +
                "Your Social Situation was: Alone\n" +
                "You noted that: I'm so bored here at work!\n" +
                "\n" +
                "On " + entryDate.format(dtf) + ", you entered that you felt that\n" +
                "Your Outlook level was: 2.0\n" +
                "Your Sorrow level was: 8.0\n" +
                "Your Location was: Work\n" +
                "You were: Working\n" +
                "Your Social Situation was: Alone\n" +
                "You noted that: Everything sucks today!\n\n";
        String result = lowEntries.toString();

        System.out.println(result);

        Assert.assertEquals(expectedResult,result);

    }

    @Test
    public void getHighOutlookEntires() {
        myEmotionEntries.addEmotionEntry(myEmotionEntry1);
        myEmotionEntries.addEmotionEntry(myEmotionEntry2);
        myEmotionEntries.addEmotionEntry(myEmotionEntry3);
        myEmotionEntries.addEmotionEntry(myEmotionEntry4);
        myEmotionEntries.addEmotionEntry(myEmotionEntry5);

        EmotionEntries lowEntries = myEmotionEntries.getHighOutlookEntries();
        String expectedResult = "On " + entryDate.format(dtf) + ", you entered that you felt that\n" +
                "Your Joy level was: 8.0\n" +
                "Your Outlook level was: 9.0\n" +
                "Your Love level was: 7.0\n" +
                "Your Location was: Home\n" +
                "You were: Playing\n" +
                "Your Social Situation was: Family\n" +
                "You noted that: My spouse is awesome!\n\n";
        String result = lowEntries.toString();

        System.out.println(result);

        Assert.assertEquals(expectedResult,result);

    }
}