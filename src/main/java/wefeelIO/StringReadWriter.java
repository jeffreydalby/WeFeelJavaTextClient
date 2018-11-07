package wefeelIO;

import java.io.*;
import java.util.Scanner;

// This class will be used to write out our final report to a text file
// just getting ahead a bit since I was already writing IO classes and this week dealt with strings.
public class StringReadWriter extends FileIOBase {

    //
    public void writeToFile(String stringToWrite, String fileName, boolean append) throws IOException {

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(fileName, append),"UTF-8")){
            writer.write(stringToWrite);
        }

    }


    public String readFromFile(String fileName) throws IOException {
        StringBuilder returnString = new StringBuilder();
        try(Scanner scanner = new Scanner(new FileInputStream(fileName))){
            while(scanner.hasNext())
            {
                returnString.append(scanner.nextLine());

            }
            return returnString.toString();
        }
    }


}
