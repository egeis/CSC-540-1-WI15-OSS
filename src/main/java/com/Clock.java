/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com;

/**
 *
 * @author Richard Coan
 */
public class Clock {
    private static long counter = 0;
    
    /**
     * System Clock
     */
    public static void doTick(Job job) {
        job.doTick(counter);
        counter++;
    }

    public static long getCounter() {
        return counter;
    }    
    
    /**
     * Reset Scheduler Simulator for simulating another collection of Jobs.
     */
    public static void reset()
    {
        counter = 0;
    }
      
}
