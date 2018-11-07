package emotions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// core Emotion of Fear
public class Fear extends CoreEmotion implements Serializable {
    //description to help people translate a core Emotion into the more broad spectrum of emotions.
    private static String fearDescription = "Nervousness, Anxiety, Uneasiness, Apprehension, Worry, Distress, Dread, Alarm, Shock, Terror, Panic";

    public Fear(){
        this(0);
    }
    @JsonCreator
    public Fear(@JsonProperty("intensity") double intensity){
        super(emotionNames.Fear,fearDescription);
        this.intensity = intensity;
    }
    //currently these overrides have the same response, but in the future we will want to refine things and have different responses for each Emotion
    @Override
    public String toString() {
        return "Your " + this.getEmotionName() + " level was: " + this.intensity;
    }
}
