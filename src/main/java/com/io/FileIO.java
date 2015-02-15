package main.java.com.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reads and writes files to a specified path.
 * @author Richard Coan
 */
public class FileIO {    
   
    /**
     * Writes a file from the contents.
     * @param contents of file to be written.
     * @return success.
     */
    public static boolean WriteFile(String contents)
    {
        boolean success = false;
        
        
        return success;
    }
    
    /**
     * Reads a file and returns a string.
     * @param path to the file.
     * @return string contents of the file.
     */
    public static String ReadFile(String path)
    {
        Logger.getLogger(FileIO.class.getName()).log(Level.INFO, "Loading File: "+path);
        
        ClassLoader classLoader = FileIO.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream(path);
        StringBuilder contents = new StringBuilder();

        try {            
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String text = null;
            
            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text).append(
                System.getProperty("line.separator"));
            }
            
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return contents.toString();
    }
    
    /**
     * Test for File Reader and Parser.
     * @param args ignored.
     */
    public static void main(String[] args) {
        /** Loading File Test, using Test Data #3 **/
        String path = "main/resources/testdata3.txt";
        System.out.println("Reading File: "+path);
        String contents = ReadFile(path);
        System.out.println(contents);
        
        /** Test for Parsing the File **/
        System.out.println("Parsing Jobs");
        List<Job> Jobs = FileParser.getJobs(contents);
        System.out.println("List Length: "+Jobs.size());
        System.out.println("List: "+Jobs.toString());
        
        /** Outputting a file **/
        System.out.println("Writing a file.");
    }

}
