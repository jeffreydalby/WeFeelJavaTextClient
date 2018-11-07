package wefeel;

import emotions.EmotionEntries;
import wefeelIO.ObjectToFileReadWriterNative;

import java.io.IOException;
//Actions to perform when exiting WeFeel

public class OnExit {

    public static void saveEntries(EmotionEntries entriesToSave, String filename){
        //only write to a file if we have entries
        if (!entriesToSave.getEmotionEntries().isEmpty()){
            try{
                ObjectToFileReadWriterNative<EmotionEntries> readWriter = new ObjectToFileReadWriterNative<>();
                readWriter.writeObjectToFile(entriesToSave,filename);

            }

            catch(IOException ex){
                System.out.println("Failed to update Emotion entries file!");
                ex.printStackTrace();
            }

        }

    }

}
