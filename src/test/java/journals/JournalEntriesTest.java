package journals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class JournalEntriesTest {

    private JournalEntries testEntries =  new JournalEntries();
    private JournalEntry testEntry1;
    private JournalEntry testEntry2;
    private JournalEntry testEntry3;


    @Before
    public void setUp() {
        testEntry1 =  new JournalEntry("This is entry 1.", LocalDateTime.of(2018,5,26,14,32));
        testEntry2 =  new JournalEntry("This is entry 2.", LocalDateTime.of(2018,5,27,16,12));
        testEntry3 =  new JournalEntry("This is entry 3.", LocalDateTime.of(2018,5,28,8,45));

        //this tests add entry
        testEntries.addEntry(testEntry1);
        testEntries.addEntry(testEntry2);
        testEntries.addEntry(testEntry3);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testToString() {
        String expectedResult = "On 05/26/2018 14:32 you wrote:\n" +
                "This is entry 1.\n" +
                "\n" +
                "On 05/27/2018 16:12 you wrote:\n" +
                "This is entry 2.\n" +
                "\n" +
                "On 05/28/2018 08:45 you wrote:\n" +
                "This is entry 3.\n\n";
        String result =  testEntries.toString();
        System.out.println(result);
        Assert.assertEquals(expectedResult,result);


    }
}