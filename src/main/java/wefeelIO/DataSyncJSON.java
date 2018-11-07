package wefeelIO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import wefeel.WeFeelMain;

import java.io.IOException;
import java.time.LocalDateTime;

//Class to simulate pushing JSON formatted data to a backend service
public class DataSyncJSON<T> implements Runnable {

    private static final String SERVER_URL = "https://service.wefeel.com";
    private static final String SERVER_KEY = "FakeServerKeyWhichYouReallyShouldNeverStoreInYourApp";
    private String connectionLog = "";
       private T DataToSync;

    public DataSyncJSON(T dataToSync){
        DataToSync = dataToSync;
    }

    @Override
    public void run() {
        pushEntry(WeFeelMain.defaultLogFile);
    }

    //Simulate syncing entries to a backend system.
    public void pushEntry(String logFileName) {

        //simulate opening a connection, writing the data and then closing it out
        //we do this by updating a "Connection Log" file.
        // yes in the real world the simulateConnectionOpen would return an actual connection object
        //which we would then use for the upload etc.
        //what this does do well is show concurrency because the log file is being written in the background while
        //the user can continue doing whatever.
        simulatateConnectionOpen(SERVER_URL,SERVER_KEY);
        simulateUpload();
        simulateConnectionCloseAndWrite(logFileName);

    }

    private void simulatateConnectionOpen(String url, String serverKey){
        //write our fake connection to our connection Log string
        connectionLog+="Connected to " + SERVER_URL + " at " + LocalDateTime.now().toString() + "\n";
    }

    private void simulateUpload(){
        //using Jackson to map the object out to a simple string in this case
        ObjectMapper mapper = new ObjectMapper();
        String uploadData = "";
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true).registerModule(new JavaTimeModule());
        try {
            //convert to JSON string.
             uploadData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.DataToSync);
        }
        catch (JsonProcessingException ex){
            //couldn't covert so write that out
            uploadData = "Failed to convert object to JSON";
        }

        connectionLog+= "Sending the following entry: " + uploadData + "\n";
    }

    private void simulateConnectionCloseAndWrite(String logFileName){
        connectionLog += "Upload Succeeded\n\n";
        StringReadWriter readWriter = new StringReadWriter();
        try{
            readWriter.writeToFile(connectionLog,logFileName, true);
        }
        catch (IOException ex){
            System.out.println("Failed to write to the connection log!");
        }
    }


}
