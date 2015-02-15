package main.java.com.io;

import main.java.com.Job;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Richard Coan
 */
public class FileParser {
    
    public static Map<Integer,Job> getJobs(String contents)
    {
        Map<Integer,Job> Jobs = new HashMap<Integer,Job>();
        
        String[] contents_array = contents
                .split(System.getProperty("line.separator"));
        
        for(int i = 0; i < contents_array.length; i += 2) 
        {
           Jobs.put(Integer.parseInt(contents_array[i].replace("Job", "")), 
                   new Job(Integer.parseInt(contents_array[i+1]), 0L));
        }
        
        return Jobs;
    }
    
}
