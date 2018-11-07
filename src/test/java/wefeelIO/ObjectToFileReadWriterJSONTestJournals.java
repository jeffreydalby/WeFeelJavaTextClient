package wefeelIO;

import journals.JournalEntries;
import journals.JournalEntry;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ObjectToFileReadWriterJSONTestJournals {

    private JournalEntries testJournalEntries =  new JournalEntries();
    private JournalEntry testJournalEntry1 = new JournalEntry();
    private JournalEntry testJournalEntry2 = new JournalEntry();
    private JournalEntry testJournalEntry3 = new JournalEntry();
    LocalDateTime entryDate = LocalDateTime.now(Clock.systemUTC());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    @Before
    public void setUp() {
        testJournalEntry1 =  new JournalEntry("This is entry 1.", LocalDateTime.of(2018,5,25,14,32));
        testJournalEntry2 =  new JournalEntry("This is entry 2.", LocalDateTime.of(2018,5,26,16,12));
        testJournalEntry3 =  new JournalEntry("This is entry 3.", LocalDateTime.of(2018,5,27,8,45));

        //this tests add entry
        testJournalEntries.addEntry(testJournalEntry1);
        testJournalEntries.addEntry(testJournalEntry2);
        testJournalEntries.addEntry(testJournalEntry3);
    }

    /**
     * tests writing journal entries to a JSON file
     */
    @Test
    public void aWriteJournalEntriesToFile() {
        try {
            //make sure our tests are valid by clearing out the test file(s)

            try {
                File deleteFile = new File("journalsTest.json");
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
            ObjectToFileReadWriterJSON<JournalEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(testJournalEntries,"journalsTest.json");

            //test that the file exists.
            File checkFile = new File("journalsTest.json");
            Assert.assertTrue(checkFile.exists());
            System.out.println("**********File Successfully Written**********");
        }
        catch (Exception ex)
        {
            System.out.println("Failed to write test file");
            System.out.println(ex);
        }


    }

    /**
     * Test reading journal entries in JSON format from a file and converts to an JournalEntries object
     */
    @Test
    public void bReadEmotionEntriesFromFile() {
        try{

            System.out.println("**********Converting JSON file to JournalEntries Object");
            ObjectToFileReadWriterJSON<JournalEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            //we have to pass the JournalEntries class to the reader so it know what type of object to convert to
            testJournalEntries = myReadWriter.readObjectFromFile("journalsTest.json", JournalEntries.class);
            System.out.println("**********Successfully converted JSON file to JournalEntries Object:");
            System.out.println(testJournalEntries);

        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

    }

    /**
     * Test to show that we are able to persist objects between sessions
     * instead of having to run two separate test we are using a "virtual session"
     * where we first create a new file, close it, read it back in, add data to the object read in
     * and then write that file back out to disk.  To ensure the data persisted we then read the file one last time
     * and compare it to the stored localTestEntries to show that the data has persisted.
     */
    @Test
    public void cPersistenceTest(){

        JournalEntries persistedTestEntries = new JournalEntries();
        //start simulated session 1 using data from setup
        //Note this is the same as the aWriteJournalEntriesToFile which setups up our initial file
        //this is the equivalent of a user creating their first entries

        try {
            //make sure our tests are valid by clearing out the test file

            try {
                File deleteFile = new File("journalsTest.json");
                deleteFile.delete();
                System.out.println("**********Deleted Existing Test File**********");

            }
            catch (Exception ex)
            {
                //do nothing, file just wasn't there to delete
            }

            System.out.println("**********Beginning initial Session and writing data to file**********");
            System.out.println("**********Writing file to disk**********");
            //write our file to disk.   This only validates that the file is written and not the format
            //the read object from file validates that we can convert the file back into EmotionEntries.
            ObjectToFileReadWriterJSON<JournalEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(testJournalEntries,"journalsTest.json");

            //test that the file exists.
            File checkFile = new File("journalsTest.json");
            System.out.println("**********File Successfully Written**********");
        }
        catch (Exception ex)
        {
            System.out.println("Failed to write test file");
            System.out.println(ex);
        }

        //begin Session 2 by reading file from session one
        System.out.println("**********Beginning simulated second session by reading in data**********");

        try{

            System.out.println("**********Converting JSON file to JournalEntries Object**********");
            ObjectToFileReadWriterJSON<JournalEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            testJournalEntries = myReadWriter.readObjectFromFile("journalsTest.json", JournalEntries.class);
            System.out.println("**********Successfully converted JSON file to JournalEntries Object:**********");
        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

        //create new journal entry
        JournalEntry testJournalEntry4 = new JournalEntry("This is Entry 4.", LocalDateTime.of(2018,5,28,19,6));


        //add new entry to local entries
        System.out.println("**********Adding New Entry**********");
        testJournalEntries.addEntry(testJournalEntry4);

        //save updated list to file

        try {
            System.out.println("**********Writing updated data to disk**********");
            //write our file to disk.   This only validates that the file is written and not the format
            //the read object from file validates that we can convert the file back into JournalEntries.
            ObjectToFileReadWriterJSON<JournalEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(testJournalEntries,"journalsTest.json");

            System.out.println("**********File Successfully Written**********");

        }

        catch (Exception ex)
        {
            System.out.println("Updating file failed");
        }

        //validate new file matches old localEntries data by reading in file and comparing

        try{

            System.out.println("**********Converting persisted JSON file to JournalEntries Object**********");
            ObjectToFileReadWriterJSON<JournalEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            persistedTestEntries = myReadWriter.readObjectFromFile("journalsTest.json", JournalEntries.class);
            System.out.println("**********Successfully converted file to JournalEntries Object:**********");
            System.out.println(persistedTestEntries);

        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

        //if we properly persisted the data the toString methods of the localTestEntries should equal the newly read in persistedTestEntries
        String testString1 = testJournalEntries.toString();
        String testString2 = persistedTestEntries.toString();
        Assert.assertEquals(testString1,testString2);

    }

    @After
    public void tearDown() {
    }

}
