package wefeel;

import accounts.Account;
import emotions.EmotionEntries;
import wefeelIO.AccountReadWriterDerby;
import wefeelIO.ObjectToFileReadWriterNative;
import wefeelIO.StringReadWriter;

import java.io.IOException;

//Actions to be taken when launching WeFeel
public class OnLaunch {

    public static EmotionEntries loadEntries(String filename){
        EmotionEntries returnEntries = new EmotionEntries();
        //load up EmotionEntries if they exist
        try {
            ObjectToFileReadWriterNative<EmotionEntries> readWriter = new ObjectToFileReadWriterNative();
            returnEntries = readWriter.readObjectFromFile(filename, EmotionEntries.class);
        }

        catch (IOException ex)
        {
            //do nothing, we just don't have the file created yet
            //TODO consider an error saying the file is corrupt and they have to start over...
        }
        return returnEntries;
    }

    public static Account loadDefaultAccount(String fileName){
        String defaultID = getDefaultAccountID(fileName);
        if (defaultID.isEmpty()) return null; //we don't have a default yet so return null

        AccountReadWriterDerby readWriterDerby = new AccountReadWriterDerby();
        return readWriterDerby.readAccount(defaultID); //will return null if we don't find in the DB
    }
    private static String getDefaultAccountID(String fileName){
        StringReadWriter readWriter = new StringReadWriter();
        try {
            return readWriter.readFromFile(fileName);
        }
        catch (IOException ex){
            //file doesn't exist so just return an empty string
            return "";
        }
    }

}
