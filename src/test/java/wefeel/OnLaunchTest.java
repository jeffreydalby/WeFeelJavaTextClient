package wefeel;

import emotions.EmotionEntries;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//Test to make sure we ca read in entries
public class OnLaunchTest {

    EmotionEntries myEntries = new EmotionEntries();
    @Before
    public void setUp() {
    }

    //NOTE: This test is dependent on the file already existing!
    @Test
    public void loadEntries() {
        myEntries = OnLaunch.loadEntries("testExitEntries.dat");
        Assert.assertNotNull(myEntries.toString());
    }
}