package main.java.com.io.chart;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import main.java.com.Job;
import main.java.com.Scheduler;

/**
 *
 * @author Richard Coan
 */
public class SimpleGantt {
    
    private static String getName(int type)
    {
        String name = "";
        
        switch(type)
        {
            case Scheduler.FCFS:
                name = Scheduler.FCFS_NAME;
                break;
            case Scheduler.SJF:
                name = Scheduler.SJF_NAME;
                break;
            case Scheduler.RR3:
                name = Scheduler.RR3_NAME;
                break;
            case Scheduler.RR5:
                name = Scheduler.RR5_NAME;
                break;
            default:
                name = "Unknown";
        }
        
        return name;
    }
    
    public static void createCharts(Map<String, Map<Integer,Job>> work)
    {
        for(Entry<String, Map<Integer,Job>> entry : work.entrySet())
        {
            int type = Integer.parseInt(entry.getKey().split("-")[1]);
            String name = getName(type);
            Map<Integer,Job> jobs = entry.getValue();
            
            //Finished, JobID#
            Map<Integer, Integer> turnaround = new HashMap();
            
            //Used to Calculate Turn Around Time
            for(Entry<Integer,Job> child : jobs.entrySet())
            {
                Job j = child.getValue();
                Integer id = child.getKey();
                turnaround.put(j.getFinish(), id);
            }
            
            
            
            
        }
    }
    
    private static class DataEntry {
        public char[] chartEntry;
        
        public int start;
        public int end;
        public int running;
        public int wait;
        public int arrival;
        
        public int turnaround;
        public int id;
        
        public int header_length_small = 5;
        public int header_length_large = 30;
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            sb.append("Job"+String.format("%1$"+header_length_small+ "s",id));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s", arrival));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",wait));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",start));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",end));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",running));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",turnaround));
            sb.append("|"  +chartEntry.toString().replace(" ", "="));

            return sb.toString();
        }
        
        public String getHeader()
        {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-"+header_length_small+ "s","JobId"));
            sb.append("|"  +String.format("%-"+header_length_small+ "s","Acpt."));
            sb.append("|"  +String.format("%-"+header_length_small+ "s","Wait"));
            sb.append("|"  +String.format("%-"+header_length_small+ "s","Start"));
            sb.append("|"  +String.format("%-"+header_length_small+ "s","End"));
            sb.append("|"  +String.format("%-"+header_length_small+ "s","Run"));
            sb.append("|"  +String.format("%-"+header_length_small+ "s","TrnRo"));
            
            String top = String.format("%1$"+(sb.length()+"|Processes".length())+"s", "|Processes");
            
            for(int i = 0; i < header_length_large; i = i + 10)
            {
                sb.append(String.format("%-"+10+ "s","|"+i));
            }
            sb.append("|");
            
            return top+System.getProperty("line.separator")+sb.toString();
        }
        
    }
}
