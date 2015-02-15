package main.java.com.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import main.java.com.Clock;
import main.java.com.Job;

/**
 * First In First out Scheduler.
 * @author Richard Coan
 */
public class Fifo {
    
    public static Map<Integer, Job> run(Map<Integer, Job> jobs)
    {
        Map<Integer, Job> tasks = new HashMap<Integer, Job>(jobs); //Clone jobs.
        
        for(Entry<Integer, Job> entry : tasks.entrySet())
        {
            Job job = entry.getValue();
            while(!job.isCompleted())
            {
                Clock.doTick(job);
            }            
        }
        
        return tasks;
    }
}