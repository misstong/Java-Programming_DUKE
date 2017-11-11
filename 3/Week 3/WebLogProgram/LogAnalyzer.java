
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;

     
     public LogAnalyzer() {
         // complete constructor
         records=new ArrayList<LogEntry>();
         
     }
    
    public int countUniqueIPsInRange(int low,int high){
        ArrayList<String> list=new ArrayList<String>();
        for(LogEntry le:records){
            int status=le.getStatusCode();
            if(status>=low&&status<=high){
                String ip=le.getIpAddress();
                if(!list.contains(ip)){
                    list.add(ip);
                }
            }
        }
        return list.size();
    }
                             
     public void printAllHigherThanNum(int num){
         for(LogEntry le:records){
             int status=le.getStatusCode();
             if(status>num){
                 System.out.println(le);
                }
         }
     }
      public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> list=new ArrayList<String>();
         String ip;
         for(LogEntry le:records){
             String str=le.getAccessTime().toString();
             if(str.indexOf(someday)!=-1){
                 ip=le.getIpAddress();
                 if(!list.contains(ip)){
                     list.add(ip);
                 }
             }
            }
                 
         return list;    
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> myMap,String day){
         ArrayList<String> list=myMap.get(day);
         HashMap<String,Integer> myMap2=new HashMap<String,Integer>();
       
         for(String s:list){
             if(myMap2.containsKey(s)){
                 myMap2.put(s,myMap2.get(s)+1);
                }else{
                    myMap2.put(s,1);
                }
            }
            return iPsMostVisits(myMap2);
        }
            
     public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> myMap){
         int max=0;
         String s=null;
         for(String key:myMap.keySet()){
             if(myMap.get(key).size()>max){
                 max=myMap.get(key).size();
                 s=key;
                }
            }
            return s;
        }
         
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> myMap=new HashMap<String,ArrayList<String>>();
         String ip;
         for(LogEntry le:records){
             String str=le.getAccessTime().toString().substring(4,10);
              ip=le.getIpAddress();
             if(myMap.containsKey(str)){
                 myMap.get(str).add(ip);
                }
                else{
                    ArrayList<String> list=new ArrayList<String>();
                    list.add(ip);
                    myMap.put(str,list);
                }
            }
            return myMap;
                 
         
        }
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> myMap){
         ArrayList<String> list=new ArrayList<String>();
         int max=mostNumberVisitsByIP(myMap);
          for(String key:myMap.keySet()){
             if(myMap.get(key)==max){
                 list.add(key);
                }
            }
            return list;
        }
     public int mostNumberVisitsByIP(HashMap<String,Integer> myMap){
         int max=0;
         for(String key:myMap.keySet()){
             if(myMap.get(key)>max){
                 max=myMap.get(key);
                }
            }
         return max;
        }
     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> myMap=new HashMap<String,Integer>();
          String ip;
         for(LogEntry le:records){
             ip=le.getIpAddress();
             if(!myMap.containsKey(ip)){
                 myMap.put(ip,1);
            }
            else{
                    myMap.put(ip,myMap.get(ip)+1);
            }
        }
        return myMap;
    }
     public int countUniqueIPs(){
       HashMap<String,Integer> uniqueIPs=countVisitsPerIP();
         return uniqueIPs.size();
     }
                 
     public void readFile(String filename) {
         // complete method
         FileResource fr=new FileResource(filename);
         for(String line:fr.lines()){
             LogEntry le=WebLogParser.parseEntry(line);
             records.add(le);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
