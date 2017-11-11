
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings(){
        String moviefile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        SecondRatings sr=new SecondRatings(moviefile,raterfile);
        //System.out.println("movie size:"+sr.getMovieSize()+" rater size:"+sr.getRaterSize());
        ArrayList<Rating> a=sr.getAverageByID(20);
        Collections.sort(a);
        System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            //System.out.println(r.getValue()+" "+sr.getTitle(r.getItem()));
        }
    }
    public void getAverageRatingOneMovie(){
        String moviefile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        SecondRatings sr=new SecondRatings(moviefile,raterfile);
        String title="Vacation";
        ArrayList<Rating> a=sr.getAverageByID(0);
        for(Rating r:a){
            if(sr.getID(title).equals(r.getItem())){
                System.out.println(r.getValue());
            }
        }
    }
    
}
