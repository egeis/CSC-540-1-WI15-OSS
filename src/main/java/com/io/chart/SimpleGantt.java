package main.java.com.io.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import main.java.com.Job;
import main.java.com.Scheduler;
import main.java.com.io.FileIO;

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
        Map<String, String> results = new HashMap();
        
        for(Entry<String, Map<Integer,Job>> entry : work.entrySet())
        {
            int type = Integer.parseInt(entry.getKey().split("-")[1]);
            String name = getName(type);
            Map<Integer,Job> jobs = entry.getValue();
            
            List<DataEntry> col = new ArrayList();
            int total = 0;
            int c = 1;
            double average;
            
            int size = 0;
            
            for(Entry<Integer,Job> child : jobs.entrySet())
            {
                Job j = child.getValue();
                size += j.getTicks();
            }
            
//            System.out.println("Size:"+size);
            
            DataEntry.header_length_large = size;
            int run = 0;
            
            /*Used to Calculate Turn Around Time*/
            for(Entry<Integer,Job> child : jobs.entrySet())
            {
                Job j = child.getValue();
                Integer id = child.getKey();
                DataEntry dEntry = new DataEntry();
                
                dEntry.id = id;
                dEntry.start = j.getStart();
                dEntry.end = j.getFinish();
                dEntry.wait = j.getWaitTime();
                dEntry.running = run + (j.getFinish() - j.getStart());
                
                run = dEntry.running;
                
                total += (j.getFinish() - j.getStart());
                c++;
                
                dEntry.turnaround = total;
                dEntry.chartEntry.setLength(size);

                Integer key = 0;
                Integer val = 0;
                
                try {
                    for(Entry<Long, Integer> workers : j.getWork().entrySet())
                    {
                        key = workers.getKey().intValue();
                        val = workers.getValue();

                        dEntry.chartEntry.replace(key, key+1, "*");
                    }
                } catch(java.lang.StringIndexOutOfBoundsException ex) {
                    System.out.println(key + " " + key.intValue());
                    System.out.println(dEntry.chartEntry.length());
                }
                
                col.add(dEntry);
            }
            
            average = ((1.0 * total) / c);   
            
            results.put(entry.getKey(),
                    DataEntry.getHeader()+
                    col.toString().replace("[","").replace("]","").replace(", ","")+
                    "Average Turn-Around Time:"+average+
                    System.getProperty("line.separator")
            );
        }
        
        FileIO.WriteFile(results.values().toString().replace("[","").replace("]","").replace(", ",""));
    }
    
    private static class DataEntry {
        public StringBuilder chartEntry = new StringBuilder();
        
        public int start;
        public int end;
        public int running;
        public int wait;
        
        public int turnaround;
        public int id;
        
        public static int header_length_small = 5;
        public static int header_length_large = 30;
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            
            sb.append(String.format("%1$"+header_length_small+ "s",id));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",wait));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",start));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",end));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",running));
            sb.append("|"  +String.format("%1$"+header_length_small+ "s",turnaround));
            sb.append("|"  +chartEntry.toString());
            //sb.append("|"  +chartEntry.toString().replace(" ", "="));

            return sb.toString()+System.getProperty("line.separator");
        }
        
        public static String getHeader()
        {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-"+header_length_small+ "s","JobId"));
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
            
            return top+System.getProperty("line.separator")+sb.toString()+System.getProperty("line.separator");
        }
        
    }
}
