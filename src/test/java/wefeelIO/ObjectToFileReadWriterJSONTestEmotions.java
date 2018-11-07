package wefeelIO;

import emotions.*;
import emotions.EmotionEntry.activities;
import emotions.EmotionEntry.locations;
import emotions.EmotionEntry.situations;
import org.junit.*;
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
public class ObjectToFileReadWriterJSONTestEmotions {

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

    /**
     * tests writing Emotion entries to a JSON file
     */
    @Test
    public void aWriteEmotionEntriesToFile() {
        try {
            //make sure our tests are valid by clearing out the test file(s)

            try {
                File deleteFile = new File("emotionsTest.json");
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
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(myEmotionEntries,"emotionsTest.json");

            //test that the file exists.
            File checkFile = new File("emotionsTest.json");
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
     * Test reading Emotion entries in JSON format from a file and converts to an EmotionEntries object
     */
    @Test
    public void bReadEmotionEntriesFromFile() {
        try{

            System.out.println("**********Converting JSON file to EmotionEntry Object");
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            //we have to pass the EmotionEntries class to the reader so it know what type of object to convert to
            EmotionEntries testEntries = myReadWriter.readObjectFromFile("emotionsTest.json", EmotionEntries.class);
            System.out.println("**********Successfully converted JSON file to EmotionEntry Object:");
            System.out.println(testEntries);

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

        EmotionEntries localTestEntries = new EmotionEntries();
        EmotionEntries persistedTestEntries = new EmotionEntries();
        //start simulated session 1 using data from setup
        //Note this is the same as the aWriteEmotionEntriesToFile which setups up our initial file
        //this is the equivalent of a user creating their first entries

        try {
            //make sure our tests are valid by clearing out the test file(s)

            try {
                File deleteFile = new File("emotionsTest.json");
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
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(myEmotionEntries,"emotionsTest.json");

            //test that the file exists.
            File checkFile = new File("emotionsTest.json");
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

            System.out.println("**********Converting JSON file to EmotionEntry Object**********");
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            localTestEntries = myReadWriter.readObjectFromFile("emotionsTest.json", EmotionEntries.class);
            System.out.println("**********Successfully converted Json file to EmotionEntry Object:**********");
        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

        //create new Emotion entry
        EmotionEntry myEmotionEntry4 = new EmotionEntry();

        myEmotionEntry4.addEmotion(new Sorrow(8));
        myEmotionEntry4.addEmotion(new Outlook(2));
        myEmotionEntry4.setEntrySituation(situations.Alone);
        myEmotionEntry4.setEntryLocation(locations.Work);
        myEmotionEntry4.setEntryActivity(activities.Working);
        myEmotionEntry4.setEntryNote("Why is everything so hard");

        //add new entry to local entries
        System.out.println("**********Adding New Entry**********");
        localTestEntries.addEmotionEntry(myEmotionEntry4);

        //save updated list to file

        try {
            System.out.println("**********Writing updated data to disk**********");
            //write our file to disk.   This only validates that the file is written and not the format
            //the read object from file validates that we can convert the file back into EmotionEntries.
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(localTestEntries,"emotionsTest.json");

            System.out.println("**********File Successfully Written**********");

        }

        catch (Exception ex)
        {
            System.out.println("Updating file failed");
        }

        //validate new file matches old localEntries data by reading in file and comparing

        try{

            System.out.println("**********Converting persisted JSON file to EmotionEntry Object**********");
            ObjectToFileReadWriterJSON<EmotionEntries> myReadWriter = new ObjectToFileReadWriterJSON<>();
            persistedTestEntries = myReadWriter.readObjectFromFile("emotionsTest.json", EmotionEntries.class);
            System.out.println("**********Successfully converted file to EmotionEntry Object:**********");
            System.out.println(persistedTestEntries);

        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

        //if we properly persisted the data the toString methods of the localTestEntries should equal the newly read in persistedTestEntries
        String testString1 = localTestEntries.toString();
        String testString2 = persistedTestEntries.toString();
        Assert.assertEquals(testString1,testString2);

    }

    @After
    public void tearDown() {
    }

}