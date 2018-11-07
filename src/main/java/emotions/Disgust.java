package emotions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// core Emotion of Disgust
public class Disgust extends CoreEmotion implements Serializable {
    //description to help people translate a core Emotion into the more broad spectrum of emotions.
    private static String disgustDescription = "Boredom, Distaste, Revulsion, Repugnance, Aversion, Nausea, Abhorrence";

    public Disgust(){
        this(0);
    }

    @JsonCreator
    public Disgust(@JsonProperty("intensity") double intensity){
        super(emotionNames.Disgust,disgustDescription);
        this.intensity = intensity;
    }


    //currently these overrides have the same response, but in the future we will want to refine things and have different responses for each Emotion
    @Override
    public String toString() {
        return "Your " + this.getEmotionName() + " level was: " + this.intensity;
    }
}
