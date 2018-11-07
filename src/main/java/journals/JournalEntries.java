package journals;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Holds a list of journal entries for an Account
 */
public class JournalEntries implements Serializable {

    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }
    //only way to add a new entry is via this method, later we may want to add some validation
    public void addEntry(JournalEntry newEntry){
        this.journalEntries.add(newEntry);
    }

    private List<JournalEntry>  journalEntries = new LinkedList<>();

    public JournalEntries(){
    }

    @Override
    public String toString(){

        StringBuilder returnString = new StringBuilder();
        for (JournalEntry entry:journalEntries
             ) {

            returnString.append(entry).append("\n");
        }
        return returnString.toString();
    }
}
