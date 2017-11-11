
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    public void test(){
        ArrayList<String> r=getItemsToRate();
        System.out.println(MovieDatabase.getTitle(r.get(0)));
    }
    public ArrayList<String> getItemsToRate(){
       ArrayList<String> l=MovieDatabase.filterBy(new TrueFilter());
       ArrayList<String> ret;
       if(l.size()<=10){
           return l;
        }
        else{
            ret=new ArrayList<String>();
            for(int i=0;i<10;i++){
                ret.add(l.get(i));
            }
            return ret;
        }
        
    }
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings fr=new FourthRatings();
        ArrayList<Rating> ret=fr.getSimilarRatings(webRaterID,10,3);
        if(ret.size()==0){
            System.out.println("No movie recomendation");
        }
        else{
            int count;
            if(ret.size()>5){
                count=5;
                
            }else{
                count=ret.size();
            }
            System.out.println("<table>");  
            for(int i=0;i<count;i++){
                System.out.println("<tr>");
                System.out.println("<td>");
                System.out.print("<img src=\"");
                String poster=MovieDatabase.getPoster(ret.get(i).getItem()).substring(7);
                
                System.out.print("data/"+poster);
                System.out.print("\" width=\"50%\"/>");
                System.out.println("</td>");
                System.out.println("<td>");
                System.out.println("Title:"+MovieDatabase.getTitle(ret.get(i).getItem())+"<br>Minutes:"+MovieDatabase.getMinutes(ret.get(i).getItem()));
                //System.out.println(MovieDatabase.getPoster(ret.get(i).getItem()));
                System.out.println("</td>");
                System.out.println("</tr>");
            }
            System.out.println("</table>");
        }
        
    }

}
