
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {
    public void testGetFollows(){
        FileResource fr = new FileResource();
         String st = fr.asString();
         st = st.replace('\n', ' ');
         MarkovOne markov = new MarkovOne();
         markov.setTraining(st);
//         MarkovOne markov = new MarkovOne();
//        markov.setTraining("this is a test yes this is a test.");
        ArrayList<String> list=markov.getFollows("he");
        for(String s:list){
            System.out.println(s);
        }
        System.out.println(list.size());
    }

}
