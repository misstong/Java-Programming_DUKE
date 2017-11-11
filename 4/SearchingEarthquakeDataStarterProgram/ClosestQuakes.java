
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy=new ArrayList<QuakeEntry>(quakeData);
        // TO DO
        for(int i=0;i<howMany;i++)
        {
            double minDistance=-1;
            QuakeEntry closestQua=null;
            for(QuakeEntry qe:copy){
                double distance=qe.getLocation().distanceTo(current);
                if(minDistance==-1){
                    minDistance=distance;
                    closestQua=qe;
                }else if(minDistance>distance){
                    minDistance=distance;
                    closestQua=qe;
                }
            }
            ret.add(closestQua);
            copy.remove(closestQua);
        }
                

        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "D:\\ThunderDownload\\Java programming Coursera\\4\\nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845);

        ArrayList<QuakeEntry> close = getClosest(list,jakarta,3);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
