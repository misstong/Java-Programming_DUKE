
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class FourthRatings {
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> similarRaters=getSimilarities(id);
        ArrayList<Rating> chosenRaters=null;
        
        if(numSimilarRaters>=similarRaters.size()){
            chosenRaters=similarRaters;
        }
        else{
            chosenRaters=new ArrayList<Rating>();
            for(int i=0;i<numSimilarRaters;i++){
                chosenRaters.add(similarRaters.get(i));
            }
        }
         ArrayList<String> movies=MovieDatabase.filterBy(filterCriteria);   
         ArrayList<Rating> ret=new ArrayList<Rating>();
         for(String movie:movies){
             if(enoughRatings(movie,chosenRaters,minimalRaters)){
                 ret.add(getSimilarRating(movie,chosenRaters));
             }
         }
         Collections.sort(ret,Collections.reverseOrder());
          return ret;  
    }
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters,new TrueFilter());
    }
    private Rating getSimilarRating(String item,ArrayList<Rating> raters){
        int count=0;
        double sum=0;
        for(Rating r:raters){
            if(RaterDatabase.getRater(r.getItem()).hasRating(item)){
                count++;
                sum+=r.getValue()*RaterDatabase.getRater(r.getItem()).getRating(item);
            }
        }
        return new Rating(item,sum/count);
        
    }
    private boolean enoughRatings(String id,ArrayList<Rating> raters,int minimalRaters){
        int count=0;
        for(Rating r:raters){
            if(RaterDatabase.getRater(r.getItem()).hasRating(id)){
                count++;
            }
        }
        return count>=minimalRaters;
    }
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rater> raters=RaterDatabase.getRaters();
        ArrayList<Rating> ratings=new ArrayList<Rating>();
        for(Rater r:raters){
            if(!r.getID().equals(id)){
                double sim=dotProduct(r,RaterDatabase.getRater(id));
                if(sim>0){
                    ratings.add(new Rating(r.getID(),sim));
                }
            }
        }
        Collections.sort(ratings,Collections.reverseOrder());
        return ratings;
        
       
    }
    private double dotProduct(Rater me,Rater r){
        double ret=0;
        double sum=0;
        ArrayList<String> items=me.getItemsRated();
        for(String i:items){
            if(r.hasRating(i)){
                sum+=(me.getRating(i)-5)*(r.getRating(i)-5);
            }
        }
        return sum;
    }
     private double getAverageByID(String id,int minimalRaters){
        int count=0;
        double sum=0;
        for(Rater rater:RaterDatabase.getRaters()){
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
