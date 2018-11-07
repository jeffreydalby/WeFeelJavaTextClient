package wefeel;

import emotions.EmotionEntries;
import wefeelIO.ObjectToFileReadWriterJSON;

import java.io.IOException;
import java.util.Scanner;

//page to allow user to export all entries to JSON
public class ExportAllToJSONPage extends EmotionPageBase implements BasePage {
    public static final String JSON_FILENAME = "emotions_export.json";

    //pass the "global" Emotion entries to the page to be worked with
    ExportAllToJSONPage(EmotionEntries myEmotionEntries){
        super(myEmotionEntries);
    }

    /**
     * Export using ObjectToFileReadWriterJSON to file
     * @param sc- Scanner, used for press enter to continue
     */
    public void showPage(Scanner sc){
        System.out.println("Exporting all Emotion entries to file " + JSON_FILENAME);
        ObjectToFileReadWriterJSON readWriterJSON = new ObjectToFileReadWriterJSON();
        try {
            readWriterJSON.writeObjectToFile(myEmotionEntries, JSON_FILENAME);
        }
        catch (IOException ex){
            System.out.println("Failed to write " + JSON_FILENAME);
            ex.printStackTrace();
        }
        System.out.println("Press Enter to continue");
        sc.nextLine();
    }

}
