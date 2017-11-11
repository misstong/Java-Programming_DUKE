
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printSimilarRatingsByYearAfterAndMinutes(){
         String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        FourthRatings sr=new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        AllFilters f=new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(70,200));
        ArrayList<Rating> a=sr.getSimilarRatingsByFilter("314",10,5,f);
        System.out.println(MovieDatabase.getTitle(a.get(0).getItem())+" "+a.get(0).getValue());
        System.out.println(" "+MovieDatabase.getDirector(a.get(0).getItem())+" "+MovieDatabase.getMinutes(a.get(0).getItem()));
    
    }
    public void printSimilarRatingsByGenreAndMinutes(){
         String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        FourthRatings sr=new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        AllFilters f=new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));
        ArrayList<Rating> a=sr.getSimilarRatingsByFilter("168",10,3,f);
        System.out.println(MovieDatabase.getTitle(a.get(0).getItem())+" "+a.get(0).getValue());
        System.out.println(" "+MovieDatabase.getDirector(a.get(0).getItem())+" "+MovieDatabase.getMinutes(a.get(0).getItem()));
    }
    public void printSimilarRatingsByDirector(){
         String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        FourthRatings sr=new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        ArrayList<Rating> a=sr.getSimilarRatingsByFilter("120",10,2,new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        System.out.println(MovieDatabase.getTitle(a.get(0).getItem())+" "+a.get(0).getValue());
        System.out.println(" "+MovieDatabase.getDirector(a.get(0).getItem()));
    }
    public void printSimilarRatingsByGenre(){
         String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        FourthRatings sr=new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        ArrayList<Rating> a=sr.getSimilarRatingsByFilter("964",20,5,new GenreFilter("Mystery"));
        System.out.println(MovieDatabase.getTitle(a.get(0).getItem())+" "+a.get(0).getValue());
        System.out.println(" "+MovieDatabase.getGenres(a.get(0).getItem()));
    }
    public void printSimilarRatings(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        FourthRatings sr=new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        ArrayList<Rating> a=sr.getSimilarRatings("71",20,5);
        System.out.println(MovieDatabase.getTitle(a.get(0).getItem())+" "+a.get(0).getValue());
    }
     public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        FourthRatings sr=new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+RaterDatabase.size());
        AllFilters f=new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new YearAfterFilter(1990));
        ArrayList<Rating> a=sr.getAverageRatingsByFilter(8,f);
        Collections.sort(a);
        System.out.println("rated movies:"+a.size());
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            //System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
           // System.out.println(MovieDatabase.getGenres(r.getItem()));
        }
    }
    public void printAverageRatings(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="ratings.csv";
        FourthRatings sr=new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+RaterDatabase.size());
        ArrayList<Rating> a=sr.getAverageByID(35);
        System.out.println("rated movies:"+a.size());
        Collections.sort(a);
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
}
