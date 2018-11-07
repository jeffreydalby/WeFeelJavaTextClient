package emotions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// core Emotion of Sorrow
public class Sorrow extends CoreEmotion implements Serializable {
    //description to help people translate a core Emotion into the more broad spectrum of emotions.
    private static String sorrowDescription = "Sadness, Depression, Despair, Unhappy, Grief, Misery, Disappointment, Dismay, Displeasure, Embarrassment, Humiliation, Insecurity";

    public Sorrow(){
        this(0);
    }

    @JsonCreator
    public Sorrow(@JsonProperty("intensity") double intensity){
        super(emotionNames.Sorrow,sorrowDescription);
        this.intensity = intensity;
    }
    //currently these overrides have the same response, but in the future we will want to refine things and have different responses for each Emotion
    @Override
    public String toString() {
        return "Your " + this.getEmotionName() + " level was: " + intensity;
    }
}
