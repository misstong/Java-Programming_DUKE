
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1,QuakeEntry q2){
        if(lastWord(q1.getInfo()).compareTo(lastWord(q2.getInfo()))<0){
            return -1;
        }else if(lastWord(q1.getInfo()).compareTo(lastWord(q2.getInfo()))>0){
            return 1;
        }
     
        return Double.compare(q1.getMagnitude(),q2.getMagnitude());
    }
    private String lastWord(String title){
       // int index=title.lastIndexOf(",",title.length()-1);
        //return title.substring(index+1,title.length());
         String[] s=title.split(",|，|\\s+");
       return s[s.length-1];
    }

}
