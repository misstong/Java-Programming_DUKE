import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> ret=new ArrayList<Movie>();
        FileResource f=new FileResource(filename);
        CSVParser parser=f.getCSVParser(false);
        for(CSVRecord r:parser){
            if(parser.getCurrentLineNumber()==1){
                continue;
            }
            ret.add(new Movie(r.get(0),r.get(1),r.get(2),r.get(4),r.get(5),r.get(3),r.get(7),Integer.parseInt(r.get(6))));
        }
        return ret;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        ArrayList<Rater> ret=new ArrayList<Rater>();
        FileResource f=new FileResource(filename);
        CSVParser parser=f.getCSVParser(false);
        for(CSVRecord r:parser){
             if(parser.getCurrentLineNumber()==1){
                continue;
            }
            //Rating rating=new Rating(r.get(1),Double.parseDouble(r.get(2)));
            if(ret.size()==0){
                Rater rater=new EfficientRater(r.get(0));
                rater.addRating(r.get(1),Double.parseDouble(r.get(2)));
                ret.add(rater);
            }
            else{
                int added=0;
                for(Rater rater:ret){
                    if(rater.getID().equals(r.get(0))){
                        rater.addRating(r.get(1),Double.parseDouble(r.get(2)));
                        added=1;
                        break;
                    }
                    
                        
                }
                if(added==0){
                    Rater rater=new EfficientRater(r.get(0));
                    rater.addRating(r.get(1),Double.parseDouble(r.get(2)));
                    ret.add(rater);
                }
            }
            
        }
        return ret;
    }
    
    public void testLoadRaters(){
        String fn="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratings.csv";
       
        ArrayList<Rater> a=loadRaters(fn);
        System.out.println("number of raters: "+a.size());
        String raterid="193";
        int maxRating=0;
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        for(Rater r:a){
            if(r.getID().equals(raterid))
                System.out.println("rater id: "+r.getID()+" with "+r.numRatings()+" rating ");
            if(r.numRatings()>maxRating){
                maxRating=r.numRatings();
            }
            ArrayList<String> item=r.getItemsRated();
            for(String i:item){
               //System.out.println("movie id: "+i+" rating "+r.getRating(i));
               if(map.containsKey(i)){
                   map.put(i,map.get(i)+1);
                }
                else{
                    map.put(i,1);
                }
            }
            
        }
        
        for(Rater r:a){
            if(r.numRatings()==maxRating){
                System.out.println("rater id : "+r.getID()+" with "+maxRating+" rating ");
            }
        }
        System.out.println(map.get("1798709"));
        System.out.println("number of different movies: "+map.size());
    }
    public void testLoadMovies(){
        String fn="D:\\ThunderDownload\\DUKE Java programming Coursera\\5\\data\\"+"ratedmoviesfull.csv";
       
        ArrayList<Movie> a=loadMovies(fn);
        System.out.println("number of movies: "+a.size());
        int comedyMovies=0,longMovies=0;
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        for(Movie m:a){
        //    System.out.println(m.toString());
            String genre=m.getGenres();
            if(genre.indexOf("Comedy")!=-1)
                comedyMovies++;
            if(m.getMinutes()>150)
                longMovies++;
            
            String[] directors=m.getDirector().split(",");
            for(String dir:directors){
                if(map.containsKey(dir))
                    map.put(dir,map.get(dir)+1);
                else
                    map.put(dir,1);
            }
            
        }
        
        System.out.println("comedy movies: "+comedyMovies);
        System.out.println("long movies: "+longMovies);
        
        int movieByDirector=0;
        for(String s:map.keySet()){
            if(movieByDirector<map.get(s)){
                movieByDirector=map.get(s);
            }
        }
        for(String s:map.keySet()){
            if(movieByDirector==map.get(s)){
                System.out.println("productive director :"+s+" with "+movieByDirector+" movie ");
            }
        }
        
    }

}
