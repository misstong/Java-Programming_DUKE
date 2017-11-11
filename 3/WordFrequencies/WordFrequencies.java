
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords=new ArrayList<String>();
        myFreqs=new ArrayList<Integer>();
    }    
    public int findIndexOfMax(){
        int max=-1,maxIndex=-1;
        for(int i=0;i<myFreqs.size();i++){
            if(myFreqs.get(i)>max){
                max=myFreqs.get(i);
                maxIndex=i;
            }
        }
        return maxIndex;
    }
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr=new FileResource();
        String lowerWord;
        int index,count;
        for(String word:fr.words()){
            lowerWord=word.toLowerCase();
            index=myWords.indexOf(lowerWord);
            if(index==-1){
                myWords.add(lowerWord);
                myFreqs.add(1);
            }
            else{
                count= myFreqs.get(index);
                myFreqs.set(index,count+1);
            }
        }
    }
    public void tester(){
        findUnique();
        for(int i=0;i<myWords.size();i++){
          //  System.out.println(myFreqs.get(i)+" "+myWords.get(i));
           
        }
        int index=findIndexOfMax();
        System.out.println("size: "+myWords.size());
        System.out.println("The word that occurs most often and its count are:"+myFreqs.get(index)+" "+myWords.get(index));
    }
            
        
       


}
