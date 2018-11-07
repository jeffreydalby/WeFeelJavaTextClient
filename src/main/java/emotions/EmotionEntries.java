package emotions;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Holds a list of Emotion entries for a specific Account
 */
public class EmotionEntries implements Serializable {

    private List<EmotionEntry> theEmotionEntries  = new LinkedList<>();

    //force entries to be added via this add method only
    public void addEmotionEntry(EmotionEntry theEmotionEntry){
        theEmotionEntries.add(theEmotionEntry);
    }

    public List<EmotionEntry> getEmotionEntries(){
        return theEmotionEntries;
    }

    public EmotionEntries(){

    }
    @JsonIgnore
    public EmotionEntries getLowOutlookEntries(){

        EmotionEntries returnEntries = new EmotionEntries();
        returnEntries.theEmotionEntries =
                theEmotionEntries
                        .stream()
                        .filter(x -> x.getEmotions()
                                .get("Outlook").intensity < 5 )
                        .collect(Collectors.toList());
        return returnEntries;
    }
    @JsonIgnore
    public EmotionEntries getHighOutlookEntries(){

        EmotionEntries returnEntries = new EmotionEntries();
        returnEntries.theEmotionEntries =
                theEmotionEntries
                        .stream()
                        .filter(x -> x.getEmotions()
                                .get("Outlook").intensity > 5 )
                        .collect(Collectors.toList());
        return returnEntries;
    }

    //builds a list of Emotion entries to output to the console using the overridden toString methods of each object.
    @Override
    public String toString(){
        StringBuilder returnString = new StringBuilder();
        for (EmotionEntry theEntry:theEmotionEntries
             ) {
            returnString.append(theEntry).append("\n");
        }

        return returnString.toString();
    }
}
