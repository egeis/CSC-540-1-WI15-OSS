package main.java.com.io;

/**
 * Simulated Process Job.
 * @author Richard Coan
 */
public class Job {
    private int id;
    private String name;
    private int waitTime;
    private boolean completed;

    public Job(int id, String name, int waitTime)
    {
        this.id = id;
        this.name = name;
        this.waitTime = waitTime;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWaitTime() {
        return waitTime;
    }
    
    public boolean isCompleted() {
        return completed;
    }
        
    public String toString() {
        return "Job "+this.id+": name="+this.name+" waitTime="+this.waitTime+" completed="+this.completed;
    }
}