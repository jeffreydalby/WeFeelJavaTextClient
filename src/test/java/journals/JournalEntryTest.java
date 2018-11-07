package journals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.format.DateTimeFormatter;

public class JournalEntryTest {

    private JournalEntry testEntry;

    @Before
    public void setUp() {
       testEntry = new JournalEntry("Today I felt like writing in my journal even though I have nothing to say");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        String expectedResult = "On " + testEntry.getEntryDate().format(dtf) + " you wrote:\n" + testEntry.getEntryText() + "\n";
        String result = testEntry.toString();
        System.out.println(testEntry);
        Assert.assertEquals(result,expectedResult);


    }
}