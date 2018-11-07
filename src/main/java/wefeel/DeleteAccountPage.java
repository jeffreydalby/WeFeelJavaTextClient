package wefeel;

import wefeelIO.AccountReadWriterDerby;
import wefeelIO.StringReadWriter;

import java.util.Scanner;

public class DeleteAccountPage extends MenuBasePage {

    boolean deleteAccount;
    @Override
    public void showPage(Scanner sc) {
        System.out.println("Are you sure you want to delete the account and emotion entries from this system and then exit? (Yes/No)");
        deleteAccount = requestYesNo(sc);
        if (deleteAccount) {
            cleanSystem();
            System.out.println("Account deleted. Exiting WeFeel.");
            System.exit(0);
        }
        else
        {
            System.out.println("Returning to main menu.");
        }
    }

    /**
     * Removes the default user from the system...
     * This is really just here for testing so you can play around with how a new account is created.
     */
    private void cleanSystem(){
        StringReadWriter sreadWriter = new StringReadWriter();

        sreadWriter.deleteFile(WeFeelMain.defaultAccountFile);
        //we could and maybe should use the ReadWriterNative to delete this
        //but instead using the stringreadwriter we already created since we simply need to delete a file
        sreadWriter.deleteFile(WeFeelMain.emotionsFILE);
        //clean up the database.
        AccountReadWriterDerby readWriterDerby = new AccountReadWriterDerby();
        readWriterDerby.deleteAccount(WeFeelMain.myAccount.getAccountID());
        //delete the connection log file
        sreadWriter.deleteFile(WeFeelMain.defaultLogFile);

    }
}
