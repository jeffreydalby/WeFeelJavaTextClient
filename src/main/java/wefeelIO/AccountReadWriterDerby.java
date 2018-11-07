package wefeelIO;

import accounts.Account;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccountReadWriterDerby {

    public static final String WRITE_ACCOUNT_STATEMENT = "INSERT INTO accounts (accountID, givenName, birthDate)" +
            "VALUES(?,?,?)";
    public static final String WRITE_ACCOUNT_DISORDERS_STATEMENT = "INSERT INTO disorders (accountID, adhd, depression, anxiety, mood)" +
            "VALUES(?,?,?,?,?)";


    private PreparedStatement pstatement;
    private ResultSet rs;

    /**
     * Write an account to derby database
     *
     * @param account- the account to write
     */
    public void writeAccount(Account account) {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:db/wefeeldb;create=true");
             Statement statement = conn.createStatement()
        ) {
            //make sure we have the tables
            try {
                statement.execute("CREATE TABLE accounts" +
                        " (accountID VARCHAR(255) , givenName VARCHAR(255) , birthDate DATE, PRIMARY KEY (accountID))");
            } catch (SQLException sqle) {
                ///derby is dumb and doesn't support IF NOT EXISTS So we catch and ignore
            }
            //keep separate so we can catch if either one doesn't exist...and then ignore it.
            try {
                statement.execute("CREATE TABLE disorders" +
                        " (accountID VARCHAR(255) REFERENCES ACCOUNTS(ACCOUNTID), adhd BOOLEAN, depression BOOLEAN, anxiety BOOLEAN, mood BOOLEAN)");
            } catch (SQLException sqle) {
                ///derby is dumb and doesn't support IF NOT EXISTS So we catch and ignore
            }


            PreparedStatement pstatement = conn.prepareStatement(WRITE_ACCOUNT_STATEMENT);
            pstatement.setString(1, account.getAccountID());
            pstatement.setString(2, account.getGivenName());
            pstatement.setDate(3, Date.valueOf(account.getBirthDate()));
            pstatement.executeUpdate();
            pstatement.close();
            pstatement = conn.prepareStatement(WRITE_ACCOUNT_DISORDERS_STATEMENT);
            pstatement.setString(1, account.getAccountID());
            pstatement.setBoolean(2, account.isAdhd());
            pstatement.setBoolean(3, account.isDepression());
            pstatement.setBoolean(4, account.isAnxiety());
            pstatement.setBoolean(5, account.isMood());
            pstatement.executeUpdate();
            pstatement.close();


        } catch (SQLException sqle) {
            printSQLException(sqle);
        }

    }

    /**
     * Read an account from the derby database
     * @param accountID the accountID to find
     * @return the account object that matches the ID.
     */
    public Account readAccount(String accountID) {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:db/wefeeldb;create=true");
             Statement statement = conn.createStatement()
        ) {
            //need to join the two tables to make sure we have the disorders along with the account
            rs = statement.executeQuery("SELECT ACCOUNTS.ACCOUNTID,ACCOUNTS.BIRTHDATE, ACCOUNTS.GIVENNAME,DISORDERS.ADHD,DISORDERS.ANXIETY,DISORDERS.MOOD,DISORDERS.DEPRESSION" +
                    " FROM accounts JOIN disorders ON ACCOUNTS.ACCOUNTID = DISORDERS.ACCOUNTID WHERE ACCOUNTS.ACCOUNTID = '" + accountID + "'");
            if (!rs.next()) return null; // we didn't have any accounts matching that ID so return null
            //build out our account.
            Account returnAccount = new Account(rs.getString("givenName"), rs.getDate("birthDate").toLocalDate(), rs.getString("accountID"));
            returnAccount.setMood(rs.getBoolean("mood"));
            returnAccount.setAnxiety(rs.getBoolean("anxiety"));
            returnAccount.setDepression(rs.getBoolean("depression"));
            returnAccount.setAdhd(rs.getBoolean("adhd"));
            return returnAccount;
        } catch (SQLException sqle) {
            ///couldn't find it return null
            return null;
        }

    }

    /**
     * Adding the ability to select multiple account and ordering them by birthday
     * This will allow us to have multiple users in the future.
     *
     * @return list of accounts ordered by birthday
     */

    public List<Account> getAccountsByBirthDate() {
        List<Account> returnAccounts = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:derby:db/wefeeldb;create=true");
             Statement statement = conn.createStatement()
        ) {
            //need to get the accounts with their disorders so a JOIN is needed
            rs = statement.executeQuery("SELECT ACCOUNTS.ACCOUNTID,ACCOUNTS.BIRTHDATE, ACCOUNTS.GIVENNAME,DISORDERS.ADHD,DISORDERS.ANXIETY,DISORDERS.MOOD,DISORDERS.DEPRESSION" +
                    " FROM accounts JOIN disorders ON ACCOUNTS.ACCOUNTID = DISORDERS.ACCOUNTID ORDER BY BIRTHDATE");
            //loop through all of the accounts we have
            while (rs.next()) {
                //build out the account object
                Account returnAccount = new Account(rs.getString("givenName"), rs.getDate("birthDate").toLocalDate(), rs.getString("accountID"));
                returnAccount.setMood(rs.getBoolean("mood"));
                returnAccount.setAnxiety(rs.getBoolean("anxiety"));
                returnAccount.setDepression(rs.getBoolean("depression"));
                returnAccount.setAdhd(rs.getBoolean("adhd"));
                //add the object to the return list
                returnAccounts.add(returnAccount);
            }

            return returnAccounts;

        } catch (SQLException sqle) {
            ///couldn't find anything return null
            return null;
        }
    }

    /**
     * Delete account record from DB
     *
     * @param accountID - the account to delete
     */
    public void deleteAccount(String accountID) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:db/wefeeldb;create=true");
             Statement statement = conn.createStatement()
        ) {
            statement.execute("DELETE FROM DISORDERS WHERE ACCOUNTID = '" + accountID + "'");
            statement.execute("DELETE FROM ACCOUNTS WHERE ACCOUNTID = '" + accountID + "'");


        } catch (SQLException sqle) {
            ///couldn't find so don't worry
            printSQLException(sqle);
        }
    }

    public static void printSQLException(SQLException e) {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null) {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }


}
