package wefeel;

import accounts.Account;
import emotions.EmotionEntries;

import java.util.Scanner;

//main method for WeFeel responsible for prework, displaying menupage and postwork
public class WeFeelMain {

    //main holder for all Emotion entries..is passed around to different pages as needed.
    public static EmotionEntries myEmotionEntries = new EmotionEntries();
    public static Account myAccount = new Account();
    public static String emotionsFILE = "";
    public static String defaultAccountFile="defaultAccount";
    public static String defaultLogFile="ConnectionLog.txt";

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        //load up the default account
        myAccount = OnLaunch.loadDefaultAccount(defaultAccountFile);
        //If we have no default account go to the create account page first
        if (myAccount == null)
        {
            CreateAccountPage createAccountPage = new CreateAccountPage();
            createAccountPage.showPage(sc);
            //make sure we can now load the default account
            myAccount = OnLaunch.loadDefaultAccount(defaultAccountFile);

        }
        //only load the main menu and emotion entries if we have an account
       if(myAccount != null) {
            emotionsFILE = myAccount.getAccountID() + "-emotions.dat";

           //load up any existing entries
           myEmotionEntries = OnLaunch.loadEntries(emotionsFILE);

           //create and display our main menu
           MainMenuPage menu = new MainMenuPage(myEmotionEntries);
           menu.showMainMenu(sc);

           //save Emotion entries on exit
           OnExit.saveEntries(myEmotionEntries, emotionsFILE);
       }
       else
       {
           System.out.println("Something went wrong and we were unable to create an account!");
       }
    }

}
