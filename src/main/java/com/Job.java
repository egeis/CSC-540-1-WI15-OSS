package main.java.com;

import java.util.HashMap;
import java.util.Map;



/**
 * Simulated Process Job.
 * @author Richard Coan
 */
public class Job {
    private int waitTime;
    private int ticks;
    private boolean completed;
    
    /** 
     * work progress used for gantt chart, 
     * 0 = Accepted at counter time.
     * 1 or more represents the time frame ie. 1 = from time 0 to 1.
     */
    private Map<Long, Integer> work = new HashMap();

    public Job(int waitTime)
    {
        this.waitTime = waitTime;
        this.completed = false;
        this.ticks = 0;
        work.put(0L,0);             
    }
 
    public boolean isCompleted() {
        return completed;
    }

    public Map<Long, Integer> getWork() {
        return work;
    }

    /**
     * Increments Simulated Process Clock Work Progress.
     * @param counter 
     */
    public void doTick(long counter)
    {
        if(++ticks == waitTime)
            completed = true;
        
        work.put(counter,ticks);
    }
        
    public String toString() {
        return "[Job: waitTime="+this.waitTime+" completed="+this.completed+" progress="+work.toString()+"]";
    }
}