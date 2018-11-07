package emotions;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Primary means of storing entries for a user, EmotionEntry has the ability to
 * store as many emotions as needing by utilizing a hashmap
 */
public class EmotionEntry implements Serializable {

    public enum locations {Home, School , Work, Public, Private }
    public enum situations { Alone, Family, Friends, Acquaintance, Strangers, Crowd}
    public enum activities {Relaxing,Working, Playing, Socializing}

    //accepts any of the Emotion child objects both core emotions and custom emotions
    //allowing us to keep all in one map and quickly retrieve them via the hashed name of the Emotion
    public Map<String, Emotion> getEmotions() {
        return emotions;
    }

    public locations getEntryLocation() {
        return this.entryLocation;
    }

    public void setEntryLocation(locations entryLocation) {
        this.entryLocation = entryLocation;
    }

    public situations getEntrySituation() {
        return this.entrySituation;
    }

    public void setEntrySituation(situations entrySituation) {
        this.entrySituation = entrySituation;
    }

    public activities getEntryActivity() {
        return this.entryActivity;
    }

    public void setEntryActivity(activities entryActivity) {
        this.entryActivity = entryActivity;
    }

    public String getEntryNote() {
        return this.entryNote;
    }

    public void setEntryNote(String entryNote) {
        this.entryNote = entryNote;
    }

    public LocalDateTime getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * Allows us to easily build the emotions map from a series of questions
     * @param theEmotion- the particular Emotion that we want to add to this entry
     */
    public void addEmotion(Emotion theEmotion){
        emotions.put(theEmotion.name,theEmotion);
    }

    //Hashmap will store any emotions a user associates with this entry

    private Map<String, Emotion> emotions = new HashMap<>();
    private locations entryLocation;
    private situations entrySituation;
    private activities entryActivity;
    private String entryNote;
    private LocalDateTime entryDate;

    public EmotionEntry(){
        //store date in UTC time so we can covert it to anything in the future;
        //TODO convert time back to local time when displaying in GUI
        this.entryDate = LocalDateTime.now(Clock.systemUTC());
    }

    //create the output for test console apps to make sure we are building these right.
    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        StringBuilder returnString = new StringBuilder("On " + entryDate.format(dtf) + ", you entered that you felt that\n");
        for (Map.Entry<String, Emotion> myEmotion:emotions.entrySet()
             ) {
            Emotion thisEmotion = myEmotion.getValue();
            returnString.append(thisEmotion).append("\n");
        }
        returnString.append("Your Location was: ").append(this.getEntryLocation()).append("\n");
        returnString.append("You were: ").append(this.getEntryActivity()).append("\n");
        returnString.append("Your Social Situation was: ").append(this.getEntrySituation());
        returnString.append("\nYou noted that: ").append(this.entryNote).append("\n");

        return returnString.toString();
    }

}
