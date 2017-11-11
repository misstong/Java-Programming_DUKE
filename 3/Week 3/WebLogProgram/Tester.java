
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la=new LogAnalyzer();
        //la.readFile("short-test_log");
        la.readFile("weblog2_log");
       // la.printAll();
       // System.out.println("The num of unique ip is:"+la.countUniqueIPs());
        // System.out.println(la.countUniqueIPsInRange(400,499));
        //la.printAllHigherThanNum(400);
        //ArrayList<String> list0=la.uniqueIPVisitsOnDay("Sep 24");
        //System.out.println("uniqueIPVisitsOnDay: "+list0.size());
        
        HashMap<String,Integer> myMap=la.countVisitsPerIP();
        for(String s:myMap.keySet()){
            //System.out.println(s+" "+myMap.get(s));
        }
        
        //System.out.println(" mostNumberVisitsByIP: "+la. mostNumberVisitsByIP(myMap));
        ArrayList<String> list=la.iPsMostVisits(myMap);
        for(String s:list){
            System.out.println(s);
        }
       HashMap<String,ArrayList<String>> myMap2=la.iPsForDays();
       for(String key:myMap2.keySet()){
         //  System.out.println(key+" "+myMap2.get(key).size());
        }
        System.out.println(la.dayWithMostIPVisits(myMap2));
        ArrayList<String> list2=la.iPsWithMostVisitsOnDay(myMap2,"Sep 30");
        for(String s:list2){
           System.out.println(s);
        }
        
    }
}
