/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Reads and writes files to a specified path.
 * @author Richard Coan
 */
public class FileIO {
    
    /*public static String WriteFile()
    {
        
    }*/
    
    public static String ReadFile(String PathToFile)
    {
        File file = new File(PathToFile);
        StringBuffer contents = new StringBuffer();
        BufferedReader reader = null;
        
        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            
            // repeat until all lines is read
            while ((text = reader.readLine()) != null) {
                contents.append(text).append(
                        System.getProperty("line.separator"));
            }
            
            if(reader != null) {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return contents.toString();
    }
    
    public static void main(String[] args) {
        String path = "T:\\NetBeansProjects\\CSC-540-1-WI15-OSS\\input\\testdata3.txt";
        String contents = ReadFile(path);
        System.out.println(contents);
        
        System.out.println("Jobs: ");
        List<Job> Jobs = FileParser.getJobs(contents);
        System.out.println("Length: "+Jobs.size());
        System.out.println(Jobs.toString());
    }

}
