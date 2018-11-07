package emotions;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import java.io.Serializable;

/**
 * Base abstract class to build our emotions from, never should be instantiated on its own
 */
@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")

@JsonSubTypes({
        @Type(value = Joy.class),
        @Type(value = Fear.class),
        @Type(value = Anger.class),
        @Type(value = Love.class),
        @Type(value = Disgust.class),
        @Type(value = Sorrow.class),
        @Type(value = Outlook.class),
        @Type(value = CustomEmotion.class),
})

public abstract class Emotion implements Serializable {



    protected transient String emotionDescription; //a list of synonyms to display to help people understand
    protected double intensity;  //how strongly the person felt the Emotion
    protected String name;

    public Emotion(){
        this("",0.0,"");
    }

    public Emotion(String emotionName, String description){
        this(emotionName, 0.0, description);
    }

    public Emotion(String emotionName, double intensity, String description){
        this.name = emotionName;
        this.intensity = intensity;
        this.emotionDescription = description;
    }

    public String getEmotionName(){return name;}
    public String getEmotionDescription(){return emotionDescription;}
    public double getIntensity() {return intensity;}

    public void setIntensity(double intensity){this.intensity = intensity;}

    public abstract String toString();

}
