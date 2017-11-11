
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile){
        FirstRatings fr=new FirstRatings();
        
        myRaters=fr.loadRaters(ratingsfile);
    }
   
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id,int minimalRaters){
        int count=0;
        double sum=0;
        for(Rater rater:myRaters){
            double r=rater.getRating(id);
            if(r!=-1){
                count++;
                sum+=r;
            }
        }
        if(count>=minimalRaters){
            return (double)sum/count;
        }
        return 0.0;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> a=new ArrayList<Rating>();
        ArrayList<String> movies=MovieDatabase.filterBy(filterCriteria);
         for(String id:movies){
            double r=getAverageByID(id,minimalRaters);
            if(r!=0){
                a.add(new Rating(id,r));
            }
        }
        return a;
    }
    public ArrayList<Rating> getAverageByID(int minimalRaters){
        ArrayList<Rating> a=new ArrayList<Rating>();
        ArrayList<String> movies=MovieDatabase.filterBy(new TrueFilter());
        for(String id:movies){
            double r=getAverageByID(id,minimalRaters);
            if(r!=0){
                a.add(new Rating(id,r));
            }
        }
        return a;
    }
    
    
    
}
