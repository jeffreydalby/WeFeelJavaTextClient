package wefeel;

import emotions.*;
import wefeelIO.DataSyncJSON;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main page to gather Emotion entries
 */

public class EmotionEntryPage extends EmotionPageBase {

    //new entry to add to Emotion entries when complete
    private EmotionEntry newEntry = new EmotionEntry();
    //potential entries to allow us to build a loop for the menu system instead of a lot of if thens.
    private List<Emotion> potentialEmotions = Arrays.asList(new Anger(0), new Joy(0), new Disgust(0),
                                        new Sorrow(0),new Fear(0),new Love(0), new Outlook(0));

    public EmotionEntryPage(EmotionEntries myEmotionEntries){
        //pass the Emotion entries for this session to super so they are available
        super(myEmotionEntries);
    }

    @Override
    public void showPage(Scanner sc){

        //get our emotions for this entry:
        List<Emotion> feltEmotions = getEmotions(sc);
        //add each Emotion using addEmotion
        feltEmotions.forEach(x->newEntry.addEmotion(x));
        //get location, situation, activity and note
        newEntry.setEntryLocation(getLocation(sc));
        newEntry.setEntrySituation(getSituation(sc));
        newEntry.setEntryActivity(getActivity(sc));
        newEntry.setEntryNote(getNote(sc));
        //on the off chance they stepped away and came back we want to update the time to the time the entered the last data
        newEntry.setEntryDate(LocalDateTime.now(Clock.systemUTC()));

        System.out.println("Adding this entry to your Emotion Entries:");
        System.out.println(newEntry.toString());
        System.out.println("\nPress Enter to continue");
        //wait for them to press enter...java won't allow a press any key option without a lot of hoops
        sc.nextLine();
        //update Emotion entries
        myEmotionEntries.addEmotionEntry(newEntry);
        //Sync emotion entry to server
        syncEntry(newEntry);
    }

    private void syncEntry(EmotionEntry newEntry) {
        //create the object with the entry to sync
        DataSyncJSON<EmotionEntry> dataSyncJSON = new DataSyncJSON<>(newEntry);
        //kick off the process and move on so the user can keep using the app while we sync to the server
        dataSyncJSON.run();
    }

    /**
     * Ask user for each Emotion
     * @param sc - pass around the scanner so we only have one scanner running at a time
     * @return - returns a list of emotions the person felt
     */
    private List<Emotion> getEmotions(Scanner sc) {
        System.out.println("For each Emotion presented think about how you are feeling"
                +" and then enter the intensity you feel that Emotion on a scale of 0-10.\n"+
                "0 means you aren't feeling it at all, and 10 is the most intense you ever feel that Emotion.\n ");
        for (Emotion workingEmotion:potentialEmotions
             ) {
            System.out.println(workingEmotion.getEmotionName() +"(" + workingEmotion.getEmotionDescription()+")" + "\n"+
            "On a scale of 0-10:");
            workingEmotion.setIntensity(getIntValue(sc,0,10));

        }
        //we only want to add emotions that were felt, so filter out all the zeros
        //so we filter any emotion with an intensity of 0 except for Outlook which we always was to have
       return potentialEmotions.stream().filter(x->x.getIntensity()>0 || x.getEmotionName() == "Outlook" ).collect(Collectors.toList());
    }

    /**
     * method that allows us to get any int value within a bottom and top limit
     * @param sc - pass Scanner to save tiny bit of memory
     * @param bottomLimit- lowest amount the user may enter
     * @param topLimit- highest amount the user may enter
     * @return - the int the user entered
     */


    /**
     * Get the location of the entry
     * @param sc - pass scanner to save memory
     * @return - return an emotionentry location
     */
    private EmotionEntry.locations getLocation(Scanner sc){
        System.out.println("Where are you located? Choose one of the following: ");
        //this lets us build the menu from the enum...great for refactoring
        for (int i = 1; i <= EmotionEntry.locations.values().length; i++ ){
            System.out.println(i + "." + EmotionEntry.locations.values()[i-1]);
        }
        return EmotionEntry.locations.values()[((getIntValue(sc,1,EmotionEntry.locations.values().length)) -1)];
    }
    /**
     * Get the situation of the entry
     * @param sc - pass scanner to save memory
     * @return - return an emotionentry situation
     */
    private EmotionEntry.situations getSituation(Scanner sc){
        System.out.println("What is your social situation? Choose one of the following: ");
        //this lets us build the menu from the enum...great for refactoring
        for (int i = 1; i <= EmotionEntry.situations.values().length; i++ ){
            System.out.println(i + "." + EmotionEntry.situations.values()[i-1]);
        }
        return EmotionEntry.situations.values()[((getIntValue(sc,1,EmotionEntry.situations.values().length)) -1)];
    }
    /**
     * Get the activity of the entry
     * @param sc - pass scanner to save memory
     * @return - return an emotionentry situation
     */
    private EmotionEntry.activities getActivity(Scanner sc){
        System.out.println("What type of activity are your doing? Choose one of the following: ");
        //this lets us build the menu from the enum...great for refactoring
        for (int i = 1; i <= EmotionEntry.activities.values().length; i++ ){
            System.out.println(i + "." + EmotionEntry.activities.values()[i-1]);
        }
        return EmotionEntry.activities.values()[((getIntValue(sc,1,EmotionEntry.activities.values().length)) -1)];
    }

    /**
     * Get the text note on the entry TODO consider adding a limit to the number of characters
     * @param sc- scanner passed around to save memory
     * @return - text for the note
     */
    private String getNote(Scanner sc){
        System.out.println("Enter a short note to help you remember this entry:");
        return sc.nextLine();
    }


}
