package wefeel;

import emotions.EmotionEntries;

//page base class to ensure all subpages have EmotionEntries to work with
public abstract class EmotionPageBase extends MenuBasePage{

    public EmotionEntries myEmotionEntries;

    EmotionPageBase(){
        this(new EmotionEntries());
    }
    //create the page and assign the EmotionEntries
    EmotionPageBase(EmotionEntries myEmotionEntries){
        this.myEmotionEntries = myEmotionEntries;
    }
}
