package wefeel;

import emotions.EmotionEntries;
import wefeelIO.StringReadWriter;

import java.io.IOException;
import java.util.Scanner;
//Page to allow user to export low Outlook entries to a file to print or send to therapist

public class ExportLowEntriesPage extends EmotionPageBase implements BasePage {
    public static final String LOW_ENTRIES_FILENAME = "entries_to_discuss.txt";


    public ExportLowEntriesPage(EmotionEntries myEmotionEntries){
        super(myEmotionEntries);
    }

    /**
     * Show the page and write out the file using StringReadWriter
     * @param sc- Scanner used for input Press enter to continue
     */
    public void showPage(Scanner sc){

        System.out.println("Writing entries with an Outlook less than 5 that you may want to discuss with your therapist to "+
        LOW_ENTRIES_FILENAME);
        StringReadWriter writer = new StringReadWriter();
        try {
            //directly call getLoOutlookEntries then To string to get all the entries in one line
            writer.writeToFile("Consider sharing these entries:\n" +
                    myEmotionEntries.getLowOutlookEntries().toString(),LOW_ENTRIES_FILENAME,false);
            System.out.println("Entries successfully written to "+ LOW_ENTRIES_FILENAME);
        }
        catch (IOException ex){
            System.out.println("Failed to write " + LOW_ENTRIES_FILENAME);
            ex.printStackTrace();
        }

        System.out.println("Press Enter to continue");
        sc.nextLine();

    }
}
