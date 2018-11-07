package emotions;

import java.io.Serializable;

//extension of Emotion to handle coreEmotions forcing them to use enums to avoid magic strings.
public abstract class CoreEmotion extends Emotion implements Serializable {
    //enums to avoid strings for names where possible
    public enum emotionNames {Joy, Sorrow, Fear, Anger, Love, Disgust}


    protected emotionNames emotionName; //name of the Emotion

    //force creation using the enum, makes refactoring easier later
    public CoreEmotion(emotionNames emotionName, String description){
        super(emotionName.toString(),description);

    }

}
