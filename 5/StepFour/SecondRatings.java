
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile,String ratingsfile){
        FirstRatings fr=new FirstRatings();
        myMovies=fr.loadMovies(moviefile);
        myRaters=fr.loadRaters(ratingsfile);
    }
    public int getMovieSize(){
        return myMovies.size();
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
    public ArrayList<Rating> getAverageByID(int minimalRaters){
        ArrayList<Rating> a=new ArrayList<Rating>();
        for(Movie m:myMovies){
            double r=getAverageByID(m.getID(),minimalRaters);
            if(r!=0){
                a.add(new Rating(m.getID(),r));
            }
        }
        return a;
    }
    public String getTitle(String id){
        for(Movie m:myMovies){
            if(m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "movie "+id+" not found";
        
    }
    public String getID(String title){
        for(Movie m:myMovies){
            if(m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
}