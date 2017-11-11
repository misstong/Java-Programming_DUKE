
/**
 * Write a description of WebLinks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WebLinks {
    public String link(String w){
        String lw=w.toLowerCase();

        int i=lw.indexOf("youtube.com");
        if(i!=-1){
            int stop=lw.indexOf("\"",i);
            int start=lw.lastIndexOf("\"",i);
            return w.substring(start,stop+1);
        }
        return "";
        
    }
    public void printAllLinks(){
        URLResource ur=new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String w:ur.words()){
            String url=link(w);
            if(url!=""){
                System.out.println(url);
            }
        }
    }

}
