package wefeel;

import emotions.EmotionEntries;

import java.util.InputMismatchException;
import java.util.Scanner;

//WeFeel's Main Menu
public class MainMenuPage {



        private EmotionEntries myEmotionEntries; //entries to pass to subpages

        public MainMenuPage(EmotionEntries myEmotionEntries){
            this.myEmotionEntries = myEmotionEntries; //get these from main() so we are working with the same set of EmotionEntries throughout
        }
        //static final to make it easier to edit menu entries later
        private static final String MENU_TEXT = "Welcome to WeFeel,"+WeFeelMain.myAccount.getGivenName()+ ". Please Select an option from the Menu:\n" +
                "1. Add a new Emotion entry.\n" +
                "2. View all Emotion entries.\n"+
                "3. View entries with a low Outlook.\n"+
                "4. View entries with a high Outlook.\n"+
                "5. Export low entries to a text file to share with your therapist.\n"+
                "6. Export entries to JSON file to import into another system. \n"+
                "7. Delete account and entry data. \n"+
                "8. Exit.";

        /**
         * Show our menu and then grab a selection
         */

        public void showMainMenu(Scanner sc){
                int selection = 0;

                //tagged menuLoop to allow us to dump out from within the switch statement.
                menuLoop: while(true){
                        System.out.println(MENU_TEXT);
                        try{
                                selection = sc.nextInt();
                                if (selection < 1 || selection > 8){
                                        //next line to consume the carriage return
                                        System.out.println("Please choose an option between 1 and 8.");
                                        sc.nextLine();
                                        continue;
                                }
                                sc.nextLine();
                                //go to proper page based on input
                                switch(selection){
                                        case 1:
                                                EmotionEntryPage emotionPage = new EmotionEntryPage(myEmotionEntries);
                                                emotionPage.showPage(sc);
                                                break;
                                        case 2:
                                                ViewEntriesPage entriesPage = new ViewEntriesPage(myEmotionEntries);
                                                entriesPage.showPage(sc);
                                                break;
                                        case 3:
                                                ViewLowEntriesPage lowEntriesPage = new ViewLowEntriesPage(myEmotionEntries);
                                                lowEntriesPage.showPage(sc);
                                                break;
                                        case 4:
                                                ViewHighEntriesPage highEntriesPage = new ViewHighEntriesPage(myEmotionEntries);
                                                highEntriesPage.showPage(sc);
                                                break;
                                        case 5:
                                                ExportLowEntriesPage exportLowEntries = new ExportLowEntriesPage(myEmotionEntries);
                                                exportLowEntries.showPage(sc);
                                                break;
                                        case 6:
                                                ExportAllToJSONPage exportAllToJSON = new ExportAllToJSONPage(myEmotionEntries);
                                                exportAllToJSON.showPage(sc);
                                                break;
                                        case 7:
                                                DeleteAccountPage deleteAccountPage =  new DeleteAccountPage();
                                                deleteAccountPage.showPage(sc);
                                                break;
                                        case 8:
                                                break menuLoop;
                                }


                        }
                        //make sure they enter an int
                        catch (InputMismatchException ex)
                        {
                                //next line to consume the carriage return
                                sc.nextLine();
                                System.out.print("Please enter an integer between 1-8.");
                        }
                }

        }

}
