package wefeel;

import accounts.Account;
import wefeelIO.AccountReadWriterDerby;
import wefeelIO.StringReadWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class CreateAccountPage extends MenuBasePage {

    private Account tempAccount = new Account();

    @Override
    public void showPage(Scanner sc) {
        System.out.println("Welcome to WeFeel, it looks like you need a new account. " +
                "Please answer the following questions to tell us a little bit about yourself.\n");
        //get info required for the account
        tempAccount.setGivenName(requestGivenName(sc));
        tempAccount.setBirthDate(LocalDate.of(requestBirthYear(sc), requestBirthMonth(sc), requestBirthDay(sc)));
        tempAccount.setAdhd(requestADHD(sc));
        tempAccount.setDepression(requestDepression(sc));
        tempAccount.setAnxiety(requestAnxiety(sc));
        tempAccount.setMood(requestMood(sc));
        //create the new account
        createNewAccount();
    }

    /**
     * Create new account and write account ID cache to disk
     */
    private void createNewAccount() {
        System.out.println("Creating your account....");

        //Add the account to the database
        AccountReadWriterDerby readWriter = new AccountReadWriterDerby();
        readWriter.writeAccount(tempAccount);
        //Cache the accountID to the local system to give us a default account
        StringReadWriter writeString = new StringReadWriter();
        try {
            writeString.writeToFile(tempAccount.getAccountID(), "defaultAccount",false);
        } catch (IOException ex) {
            System.out.println("Failed to cache account information");
            ex.printStackTrace();
        }
        System.out.println("Account successfully created.");
    }

    /**
     * Get the users given name
     * @param sc Scanner to pass around
     * @return users given name
     */
    private String requestGivenName(Scanner sc) {
        System.out.printf("What should we call you?");
        return sc.nextLine();
    }

    /**
     * Get the birth month
     * @param sc Scanner to pass around
     * @return birth month
     */
    private int requestBirthMonth(Scanner sc) {
        System.out.println("What month of the year were you born (1-12)");
        return getIntValue(sc, 0, 12);
    }

    /**
     * get the users birth year
     * @param sc Scanner to pass around
     * @return year of birth
     */
    private int requestBirthYear(Scanner sc) {
        System.out.println("What year were you born? (1900-2018)");
        return getIntValue(sc, 1900, 2018);
    }

    /**
     * Get users birth day
     * @param sc Scanner to pass around
     * @return day of birth
     */
    private int requestBirthDay(Scanner sc) {
        System.out.println("What day of the month were you born? (1-31)");
        return getIntValue(sc, 1, 31);
    }

    /**
     * Find out if user has ADHD
     * @param sc Scanner to pass around
     * @return boolean for ADHA
     */
    private boolean requestADHD(Scanner sc) {
        System.out.println("Have you ever been diagnosed with ADHD? (Yes/No)");
        return requestYesNo(sc);
    }

    /**
     * Find out if user has depression
     * @param sc Scanner to pass around
     * @return boolean for has depression
     */
    private boolean requestDepression(Scanner sc) {
        System.out.println("Have you ever been diagnosed with depression? (Yes/No)");
        return requestYesNo(sc);
    }

    /**
     * Find out if uesr diagnosed with Anxiety
     * @param sc Scanner to pass around
     * @return boolean has anxiety
     */
    private boolean requestAnxiety(Scanner sc) {
        System.out.println("Have you ever been diagnosed with anxiety? (Yes/No)");
        return requestYesNo(sc);
    }

    /**
     * Ask if user has a  mood disorder
     * @param sc Scanner to pass around
     * @return boolean has mood
     */
    private boolean requestMood(Scanner sc) {
        System.out.println("Have you ever been diagnosed with a mood disorder? (Yes/No)");
        return requestYesNo(sc);
    }





}
