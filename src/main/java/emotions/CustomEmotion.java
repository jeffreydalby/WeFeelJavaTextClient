package emotions;

import java.io.Serializable;

//Custom Emotion extension of cor emotions to allow us to let a user create their own Emotion definition, and set a start point
//also used to track Outlook on life
public abstract class CustomEmotion extends Emotion implements Serializable {

    public double getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(double startPoint) {
        this.startPoint = startPoint;
    }

    public String getName() {
        return name;
    }

    private double startPoint; //regular emotions start on a scale of zero, this allows us to change that start point.
   //private String name; //can hold any name a person wants to use for an item to track so no enums here

    public CustomEmotion() {

    }

    /**
     * Constructor that lets us pass in any Emotion name or description
     * @param emotionName - name of item you want to track
     * @param emotionDescription- description of item
     * @param startPoint- where you want the start point to be display for when there is a GUI using this on a slider.
     */
    public CustomEmotion(String emotionName, String emotionDescription, double startPoint) {
        super(emotionName, 0.0, emotionDescription);
        this.name = emotionName;
        this.startPoint = startPoint;

    }
}
