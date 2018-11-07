package emotions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

// core Emotion of Anger
public class Joy extends CoreEmotion implements Serializable {
    //description to help people translate a core Emotion into the more broad spectrum of emotions.
    private static String joyDescription = "Contentment, Cheerfulness, Bliss, Joviality, Delight, Happiness, Satisfaction, Ecstasy, Euphoria, Enthusiasm, Excitement, Exhilaration, Optimism, Hope";


    public Joy(){
        this(0);
    }

    @JsonCreator
    public Joy(@JsonProperty("intensity") double intensity){
        super(emotionNames.Joy,joyDescription);
        this.intensity = intensity;

    }
    //currently these overrides have the same response, but in the future we will want to refine things and have different responses for each Emotion
    @Override
    public String toString() {
        return "Your " + this.getEmotionName() + " level was: " + this.intensity;
    }
}
