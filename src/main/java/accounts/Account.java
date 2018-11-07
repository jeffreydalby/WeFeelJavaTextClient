package accounts;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Account class let's us store user specific information. As this project grows more fields may be added to the class
 * givenName- named used to personalize output
 * birthdate- persons birthdate helps us collect age related data
 * accountID- UUID to be used later to correlate user Account with Emotion and journal entries
 */
public class Account implements Serializable {

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    private String givenName;
    private LocalDate birthDate;
    private String accountID;

    public boolean isAdhd() {
        return adhd;
    }

    public void setAdhd(boolean adhd) {
        this.adhd = adhd;
    }

    public boolean isDepression() {
        return depression;
    }

    public void setDepression(boolean depression) {
        this.depression = depression;
    }

    public boolean isAnxiety() {
        return anxiety;
    }

    public void setAnxiety(boolean anxiety) {
        this.anxiety = anxiety;
    }

    public boolean isMood() {
        return mood;
    }

    public void setMood(boolean mood) {
        this.mood = mood;
    }

    private boolean adhd;
    private boolean depression;
    private boolean anxiety;
    private boolean mood;

    public Account() {
        //just create the Account without a name or birthdate
        this("", LocalDate.now());

    }
    // Generate UUID on new Account creation
    public Account(String givenName, LocalDate birthdate) {
        this.givenName = givenName;
        this.birthDate = birthdate;

        //generate a UUID to identify the Account
        this.accountID = java.util.UUID.randomUUID().toString();
    }

    //typically used to read an existing Account in from JSON file or db
    public Account(String givenName, LocalDate birthDate, String accountID) {

        this.givenName = givenName;
        this.birthDate = birthDate;
        this.accountID = accountID;

    }

    @Override
    public String toString() {
        return "GivenName= " + this.givenName + "\n" + "Birthday= " + this.birthDate.toString() + "\n" +
                "Account ID= " + this.accountID + "\n" + "ADHD= " + this.isAdhd() +"\nDepression= " + this.isDepression() +
                "\nAnxiety= " + this.isAnxiety() +"\nMood= " + this.isMood();

    }


}
