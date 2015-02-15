package main.java.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.java.com.algorithms.*;
import main.java.com.io.FileIO;
import main.java.com.io.FileParser;

/**
 * 
 * @author Richard Coan
 */
public class Scheduler {   
    public static final int FCFS = 1; //First Come First Served.
    public static final int SJF  = 2; //Shorted Job First.
    public static final int RR3  = 3; //Round Robin Timeslice 3.
    public static final int RR5  = 4; //Round Robin Timeslice 5.
        
    public static Map<Integer, Job> schedule(int type, Map<Integer, Job> jobs)
    {
        Map<Integer, Job> tasks = null;
        
        switch(type)
        {
            case FCFS:
                tasks = Fifo.run(jobs);
                break;
            case SJF:
                tasks = ShortestJobFirst.run(jobs);
                break;
            case RR3:
                tasks = RoundRobin.run(jobs, 3);
                break;
            case RR5:
                tasks = RoundRobin.run(jobs, 5);
                break;
            default:
                //Do Nothing.. Invalid type.
        }
        
        return tasks;
    }
    
    public static void main(String args[])
    {
        List<Map<Integer,Job>> work = new ArrayList();
        
        if(args.length > 0)
        {
            //TODO: Handle custom files.
        } else {
            String[] paths = {
                "main/resources/testdata1.txt",
                "main/resources/testdata2.txt",
                "main/resources/testdata3.txt"
            };
        
            for(String path : paths)
            {
                String contents = FileIO.ReadFile(path);
                work.add(FileParser.getJobs(contents));
            }
        }
        
        //[File [Algorithm id [Job ID, Job Object]]]
        Map<String, Map<Integer,Job>> completed = new HashMap();
        
        int i = 1;
        for(Map<Integer,Job> map : work)
        {
            completed.put(i+"-"+FCFS, schedule(FCFS, map));
            completed.put(i+"-"+FCFS, schedule(SJF, map));
            completed.put(i+"-"+FCFS, schedule(RR3, map));
            completed.put(i+"-"+FCFS, schedule(RR5, map));
            i++;
        }
        
//        System.out.println(completed.toString());
        
        
    }
}
