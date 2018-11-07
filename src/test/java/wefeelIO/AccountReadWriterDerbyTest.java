package wefeelIO;

import accounts.Account;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class AccountReadWriterDerbyTest {
    Account testAccount;

    @Before
    public void setUp() throws Exception {
        testAccount = new Account("John Doe", LocalDate.of(1975,5,20));
        testAccount.setAdhd(true);
        testAccount.setAnxiety(true);
    }

    @Test
    public void writeAccount() {
        //add the account to the db
        AccountReadWriterDerby accountWriter = new AccountReadWriterDerby();
        accountWriter.writeAccount(testAccount);
        //the fact that we can delete the account will mean it works
        accountWriter.deleteAccount(testAccount.getAccountID());
    }

    @Test
    public void readAccount() {
        //first we have to add an account to read
        AccountReadWriterDerby accountWriter = new AccountReadWriterDerby();
        accountWriter.writeAccount(testAccount);

        Account myAccount = new Account();
        myAccount = accountWriter.readAccount(testAccount.getAccountID());
        System.out.println(myAccount.toString());
        //now delete the account so it doesn't clog up the db
        accountWriter.deleteAccount(testAccount.getAccountID());

    }

    @Test
    public void getAccountsByBirthDate() {

        //no real effective way to automate this test so we'll depend on a person reading the output

        AccountReadWriterDerby accountWriter = new AccountReadWriterDerby();
        List<Account> accounts  = accountWriter.getAccountsByBirthDate();
        for (Account account:accounts
             ) {
            System.out.printf(account.toString()+"\n\n");

        }
    }

    @Test
    public void deleteAccount() {
        AccountReadWriterDerby accountWriter = new AccountReadWriterDerby();
        //have to write an account to be deleted.
        accountWriter.writeAccount(testAccount);
        //now delete it
        accountWriter.deleteAccount(testAccount.getAccountID());


    }
}