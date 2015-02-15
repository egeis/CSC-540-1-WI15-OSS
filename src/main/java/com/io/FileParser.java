/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.com.io;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author D14
 */
public class FileParser {
    
    public static List<Job> getJobs(String contents)
    {
        List<Job> Jobs = new ArrayList<Job>();
        int id = 0;
        
        String[] contents_array = contents.split(System.getProperty("line.separator"));
        
        for(int i = 0; i < contents_array.length; i += 2) 
        {
           Jobs.add(new Job(id, contents_array[i],Integer.parseInt(contents_array[i+1])));
           id++;
        }
        
        return Jobs;
    }
    
}
