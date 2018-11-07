package wefeel;

import emotions.EmotionEntries;

import java.util.Scanner;
//view just the entries with an Outlook lower than 5
public class ViewLowEntriesPage extends EmotionPageBase implements BasePage {

    public ViewLowEntriesPage(EmotionEntries myEmotionEntries){
        super(myEmotionEntries); //pass the entries to this page to be used
    }
    /**
     * Display the page with all the "low" entries
     * @param sc- scanner for "enter to continue"
     */
    public void showPage(Scanner sc){
        //grab just the "low" entries
        String myLowEntries = myEmotionEntries.getLowOutlookEntries().toString();
        if (!myLowEntries.isEmpty()) {
            System.out.println("Here are all of your Emotion entries with an Outlook less than 5:\n");
            System.out.println(myLowEntries);
        }
        else
            System.out.println("Looks like you don't have any entries with an Outlook less than 5.");
        System.out.println("Press Enter to continue:");
        sc.nextLine();
    }
}
