package wefeel;

import emotions.EmotionEntries;

import java.util.Scanner;
//view just the entries with an Outlook greater than 5
public class ViewHighEntriesPage extends EmotionPageBase implements BasePage {

    public ViewHighEntriesPage(EmotionEntries myEmotionEntries){
        super(myEmotionEntries); //pass the entries to this page to be used
    }

    /**
     * Display the page with all the "high" entries
     * @param sc- scanner for "enter to continue"
     */
    public void showPage(Scanner sc){
        //grab just the "high" entries
        String myHighEntries = myEmotionEntries.getHighOutlookEntries().toString();
        if (!myHighEntries.isEmpty()) {
            System.out.println("Here are all of your Emotion entries with an Outlook greater than 5:\n");
            System.out.println(myHighEntries);
        }
        else
            System.out.println("Looks like you don't have any entries with an Outlook greater than 5.");
        System.out.println("Press Enter to continue:");
        sc.nextLine();

    }
}
