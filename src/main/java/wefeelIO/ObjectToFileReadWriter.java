package wefeelIO;

import java.io.IOException;

public interface ObjectToFileReadWriter<T> {

    void writeObjectToFile(T objectToWrite, String fileName)throws IOException;
    T readObjectFromFile(String fileName, Class<T> myClass)throws IOException;
}
