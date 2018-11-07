package emotions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// Outlook is stored as a custom Emotion because it needs to be able to have a start point in the center of the range.
public class Outlook extends CustomEmotion implements Serializable {
    //description text to help people understand what we mean by Outlook
    private static String outlookDescription = "Rate how positive you feel about your future.";
    private static String outlookName= "Outlook";

    public Outlook(){
        //Outlook starts on in the middle of a scale from 0 - 10 so on a GUI slider bar it appears in the middle.
        this(0);
    }

    @JsonCreator
    public Outlook(@JsonProperty("intensity") double intensity){
        //Outlook starts on in the middle of a scale from 0 - 10 so on a GUI slider bar it appears in the middle.
        super(outlookName,outlookDescription, 5.0);
        this.intensity = intensity;
    }
    //currently these overrides have the same response, but in the future we will want to refine things and have different responses for each Emotion
    @Override
    public String toString() {
        return "Your " + this.getName() + " level was: " + intensity;
    }
}
