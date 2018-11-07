package journals;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Journal Entry stores the date of the journal entry and the entry itself
 * entryDate- date of the entry
 * entryText- users journal entry text
 */

public class JournalEntry implements Serializable {

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryText() {
        return entryText;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    private LocalDateTime entryDate;
    private String entryText;

    public JournalEntry(){
        //create with an empty string and the current timestamp
        this("");
    }

    public JournalEntry(String entryText){
        //store date in UTC time so we can covert it to anything in the future;
        //TODO convert time back to local time when displaying in GUI
        this(entryText,LocalDateTime.now(Clock.systemUTC()));
    }

    public JournalEntry(String entryText, LocalDateTime entryDate){
        this.entryText = entryText;
        this.entryDate = entryDate;
    }

    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        return "On " + this.entryDate.format(dtf) + " you wrote:\n" + this.entryText + "\n";
    }

}
