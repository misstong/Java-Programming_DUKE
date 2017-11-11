
/**
 * Write a description of MovieRunnerWithFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MovieRunnerWithFilter {
    public void printAverageRatingsByDirectorsAndMinutes(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        ThirdRatings sr=new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+sr.getRaterSize());
        AllFilters f=new AllFilters();
        f.addFilter(new MinutesFilter(90,180));
        f.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> a=sr.getAverageRatingsByFilter(3,f);
        Collections.sort(a);
        System.out.println("rated movies:"+a.size());
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            //System.out.println(r.getValue()+" "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
           // System.out.println(MovieDatabase.getDirector(r.getItem()));
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        ThirdRatings sr=new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+sr.getRaterSize());
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
    public void printAverageRatingsByDirectors(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        ThirdRatings sr=new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+sr.getRaterSize());
        ArrayList<Rating> a=sr.getAverageRatingsByFilter(4,new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        Collections.sort(a);
        System.out.println("rated movies:"+a.size());
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            //System.out.println(r.getValue()+" "+MovieDatabase.getDirector(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByMinutes(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        ThirdRatings sr=new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+sr.getRaterSize());
        ArrayList<Rating> a=sr.getAverageRatingsByFilter(5,new MinutesFilter(105,135));
        Collections.sort(a);
        System.out.println("rated movies:"+a.size());
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            //System.out.println(r.getValue()+" "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByYear(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        ThirdRatings sr=new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+sr.getRaterSize());
        ArrayList<Rating> a=sr.getAverageRatingsByFilter(20,new YearAfterFilter(2000));
        Collections.sort(a);
        System.out.println("rated movies:"+a.size());
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            //System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    public void printAverageRatingsByGenre(){
         String moviefile="ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        ThirdRatings sr=new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+sr.getRaterSize());
        ArrayList<Rating> a=sr.getAverageRatingsByFilter(20,new GenreFilter("Comedy"));
        Collections.sort(a);
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        System.out.println("rated movies:"+a.size());
        for(Rating r:a){
            //System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem())+" "+MovieDatabase.getGenres(r.getItem()));
        }
    }
     public void printAverageRatings(){
        String moviefile="ratedmoviesfull.csv";
        String raterfile="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
        ThirdRatings sr=new ThirdRatings(raterfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("movie size:"+MovieDatabase.size()+" rater size:"+sr.getRaterSize());
        ArrayList<Rating> a=sr.getAverageByID(35);
        System.out.println("rated movies:"+a.size());
        Collections.sort(a);
        //System.out.println(sr.getTitle(a.get(0).getItem()));
        for(Rating r:a){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
}
