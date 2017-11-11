import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe:quakeData){
            if(qe.getMagnitude()>magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe:quakeData){
           //System.out.println(from.distanceTo(qe.getLocation()));
            if(from.distanceTo(qe.getLocation())<distMax){
                answer.add(qe);
            }
        }

        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double minDepth,double maxDepth){
        ArrayList<QuakeEntry> answer=new ArrayList<QuakeEntry>();
        for(QuakeEntry qe:quakeData){
            if(qe.getDepth()>minDepth&&qe.getDepth()<maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
     public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,String phrase){
         ArrayList<QuakeEntry> answer=new ArrayList<QuakeEntry>();
         for(QuakeEntry qe:quakeData){
             String title=qe.getInfo();
             System.out.println(title);
             if(where.equals("start")){
                  //System.out.println("start");
                 if(title.startsWith(phrase)){
                     answer.add(qe);
                 }
             }
             else if(where.equals("end")){
                  //System.out.println("end");
                  if(title.endsWith(phrase)){
                     answer.add(qe);
                 }
             }else if(where.equals("any")){
                  //System.out.println("any");
                 if(title.indexOf(phrase)!=-1){
                     answer.add(qe);
                 }
             }
         }
         return answer;
        }
      public void quakeByPhrase(){
           EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "D:\\ThunderDownload\\Java programming Coursera\\4\\nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String where="any";
        String phrase="Can";
         ArrayList<QuakeEntry> phraseList=filterByPhrase(list,where,phrase);
         for(QuakeEntry qe:phraseList){
             System.out.println(qe);
         }
          System.out.println("Found "+phraseList.size()+" quakes that match that criteria");
        }
         
                   
    public void quakesOfDepth(){
         EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "D:\\ThunderDownload\\Java programming Coursera\\4\\nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth=-4000.0;
        double maxDepth=-2000.0;
        System.out.println("Find quakes with depth between "+minDepth+" and "+maxDepth);
        ArrayList<QuakeEntry> depthList=filterByDepth(list,minDepth,maxDepth);
       for(QuakeEntry qe:depthList){
           System.out.println(qe);
       }
       System.out.println("Found "+depthList.size()+" quakes that match that criteria");
    }
        
             
        
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "D:\\ThunderDownload\\Java programming Coursera\\4\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> largerMag=filterByMagnitude(list,5.0);
        for(QuakeEntry qe:largerMag){
            System.out.println(qe);
        }
        System.out.println("Found "+largerMag.size()+" quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "D:\\ThunderDownload\\Java programming Coursera\\4\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
       // Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> closeCity=filterByDistanceFrom(list,1000000,city);
        
        for(QuakeEntry qe:closeCity){
            System.out.println(city.distanceTo(qe.getLocation())/1000+" "+qe.getInfo());
        }
        System.out.println(closeCity.size());
        
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "D:\\ThunderDownload\\Java programming Coursera\\4\\nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
