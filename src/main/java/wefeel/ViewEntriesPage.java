package wefeel;

import emotions.EmotionEntries;

import java.util.Scanner;
//Page that allows viewing of all Emotion Entries
public class ViewEntriesPage extends EmotionPageBase implements BasePage {

    public ViewEntriesPage(EmotionEntries myEmotionEntries){
        super(myEmotionEntries); //pass the entries to the page
    }

    /**
     * Print all entries to the console
     * @param sc- Scanner to pass around for "enter to continue"
     */
    public void showPage(Scanner sc){
        System.out.println("Here are all of your Emotion entries:\n");
        System.out.println(myEmotionEntries.toString());
        System.out.println("Press Enter to continue:");
        sc.nextLine();
    }
}
