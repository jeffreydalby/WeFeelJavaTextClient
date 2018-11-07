package wefeelIO;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;

//This class uses generics to allow us to write any object to a JSON file
//at the moment all we have are Emotion entries, but eventually we'll have accounts and journals
//switched from gson to jackson to better handle abstract classes

public class ObjectToFileReadWriterJSON<T> extends FileIOBase implements ObjectToFileReadWriter<T> {


    /**
     * writes any object to a JSON file to store locally
     * @param objectToWrite - any object you want to write to a JSON formatted text file
     * @param fileName - name of the file
     * @throws IOException
     */
    @Override
    public void writeObjectToFile(T objectToWrite, String fileName) throws IOException{

        //"try with resources" statement lets us auto close file, forcing UTF-8 for portability

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8")){
         ObjectMapper mapper = new ObjectMapper();
         //JavaTimeModule helps us convert localdatetime objects
         mapper.configure(SerializationFeature.INDENT_OUTPUT, true).registerModule(new JavaTimeModule());
         mapper.writeValue(writer,objectToWrite);
        }
    }


    /**
     * converts JSON file into any object, for abstracts make sure to use @JsonTypeInfo and @JsonSubTypes to properly
     * @param fileName - name of the file to write
     * @param myClass- have to pass a copy of the class in so we can use that convert back from JSON
     * @return - returns an object of Type T read from the input file
     * @throws IOException - IOException if file not found
     */
    @Override
    public T readObjectFromFile(String fileName, Class<T> myClass) throws IOException {

        try (Reader reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8")){

            //JavaTimeModule helps us convert localdatetime objects
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            T myObject = mapper.readValue(reader, myClass);
            return myObject;
        }
    }



    }


