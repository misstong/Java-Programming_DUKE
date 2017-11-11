
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class LargestQuakes {
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany){
        ArrayList<QuakeEntry> copy=new ArrayList<QuakeEntry>(quakeData);
         ArrayList<QuakeEntry> ret=new ArrayList<QuakeEntry>();
        for(int i=0;i<howMany;i++){
            int index=indexOfLargest(copy);
            if(index!=-1){
                ret.add(copy.get(index));
                copy.remove(index);
            }
        }
        return ret;
    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        double maxMag=0;
        int index=-1;
        for(int i=0;i<data.size();i++){
            double mag=data.get(i).getMagnitude();
            if(mag>maxMag){
                maxMag=mag;
                index=i;
            }
        }
        return index;
    }
    public void findLargestQuakes(){
         EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "D:\\ThunderDownload\\Java programming Coursera\\4\\nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        System.out.println(indexOfLargest(list));
        ArrayList<QuakeEntry> largestList=getLargest(list,50);
        for(QuakeEntry qe:largestList){
            System.out.println(qe);
        }
        
    }

}
