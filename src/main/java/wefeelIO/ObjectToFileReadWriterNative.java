package wefeelIO;

import java.io.*;

/**
 * object Writer that uses Java's native ObjectOutputStream to save objects to a file
 * @param <T> - Type of object to be written/read
 */

public class ObjectToFileReadWriterNative<T> extends FileIOBase implements ObjectToFileReadWriter<T> {
    @Override
    public void writeObjectToFile(T objectToWrite, String fileName) throws IOException {

        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            outputStream.writeObject(objectToWrite);
        }
    }

    @Override
    public T readObjectFromFile(String fileName, Class<T> myClass) throws IOException  {
        try(FileInputStream myStream = new FileInputStream(fileName);ObjectInputStream inputStream = new ObjectInputStream(myStream)){
            T myObject;
            try {
                myObject = myClass.cast(inputStream.readObject());

            }
            catch (ClassNotFoundException ex){
                System.out.print("Failed to convert object");
                //return null so we can do create a new object later if we want
                myObject = null;
            }
            return myObject;
        }


    }


}
