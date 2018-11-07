package wefeelIO;

import accounts.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.time.LocalDate;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ObjectToFileReadWriterJSONTestAccount {

    private Account testAccount;

    @Before
    public void setUp() {
            testAccount = new Account("John Doe",LocalDate.of(1975,5,20));
    }

    @Test
    public void aWriteAccountToFile(){
        try {
            //make sure our tests are valid by clearing out the test file(s)

            try {
                File deleteFile = new File("AccountTest.json");
                deleteFile.delete();
                System.out.println("Deleted Test File");

            } catch (Exception ex) {
                //do nothing, file just wasn't there to delete
            }
            System.out.println("**********Writing file to disk**********");
            //write our file to disk.   This only validates that the file is written and not the format
            //the read object from file validates that we can convert the file back into EmotionEntries.
            ObjectToFileReadWriterJSON<Account> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(testAccount,"AccountTest.json");

            //test that the file exists.
            File checkFile = new File("AccountTest.json");
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
    public void bReadAccountFromFile() {
        try{

            System.out.println("**********Converting JSON file to Account Object");
            ObjectToFileReadWriterJSON<Account> myReadWriter = new ObjectToFileReadWriterJSON<>();
            //we have to pass the EmotionEntries class to the reader so it know what type of object to convert to
            Account testAccount = myReadWriter.readObjectFromFile("AccountTest.json", Account.class);
            System.out.println("**********Successfully converted Json file to AccountObject:");
            System.out.println(testAccount);

        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

    }

    /**
     * Test to show that we are able to persist Account objects between sessions
     * instead of having to run two separate test we are using a "virtual session"
     * where we first create a new file, close it, read it back in, add data to the object read in
     * and then write that file back out to disk.  To ensure the data persisted we then read the file one last time
     * and compare it to the stored localTestEntries to show that the data has persisted.
     */
    @Test
    public void cPersistenceTest(){


        Account persistedTestAccount = new Account();
        //start simulated session 1 using data from setup
        //Note this is the same as the aWriteAccountToFile which sets up our initial file
        //this is the equivalent of a user creating their first entries

        try {
            //make sure our tests are valid by clearing out the test file(s)

            try {
                File deleteFile = new File("AccountTest.json");
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
            ObjectToFileReadWriterJSON<Account> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(testAccount,"AccountTest.json");

            //test that the file exists.
            File checkFile = new File("AccountTest.json");
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
            ObjectToFileReadWriterJSON<Account> myReadWriter = new ObjectToFileReadWriterJSON<>();
            testAccount = myReadWriter.readObjectFromFile("AccountTest.json", Account.class);
            System.out.println("**********Successfully converted JSON file to Account Object:**********");
        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

        //updating Account
        testAccount.setGivenName("Jane Doe");

        //add new entry to local entries
        System.out.println("**********Updating Account Entry**********");
        //save updated Account to file

        try {
            System.out.println("**********Writing updated Account data to disk**********");
            //write our file to disk.   This only validates that the file is written and not the format
            //the read object from file validates that we can convert the file back into EmotionEntries.
            ObjectToFileReadWriterJSON<Account> myReadWriter = new ObjectToFileReadWriterJSON<>();
            myReadWriter.writeObjectToFile(testAccount,"AccountTest.json");

            System.out.println("**********File Successfully Written**********");

        }

        catch (Exception ex)
        {
            System.out.println("Updating file failed");
        }

        //validate new file matches old localEntries data by reading in file and comparing

        try{

            System.out.println("**********Converting persisted JSON file to Account Object**********");
            ObjectToFileReadWriterJSON<Account> myReadWriter = new ObjectToFileReadWriterJSON<>();
            persistedTestAccount = myReadWriter.readObjectFromFile("AccountTest.json", Account.class);
            System.out.println("**********Successfully converted file to EmotionEntry Object:**********");
            System.out.println(persistedTestAccount);

        }
        catch (Exception ex)
        {
            System.out.println("**********Failed to read test file**********");
            System.out.println(ex);
        }

        //if we properly persisted the data the toString methods of the localTestEntries should equal the newly read in persistedTestEntries
        String testString1 = testAccount.toString();
        String testString2 = persistedTestAccount.toString();
        Assert.assertEquals(testString1,testString2);

    }


}
