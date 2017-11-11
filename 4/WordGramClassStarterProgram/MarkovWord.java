
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder=order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private int indexOf(String[] words,WordGram target,int start){
        for(int i=start;i<=words.length-target.length();i++){
            WordGram tmp=new WordGram(words,i,target.length());
            if(tmp.equals(target)){
                return i;
               }
           }
           return -1;
       }
//        public void testIndexOf(){
//            String s="this is just a test yes this is a simple test";
//           myText=s.split(" ");
//            ArrayList<String> list=getFollows("test");
//            System.out.println(list);
//        }
               
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText,index,myOrder);
        sb.append(key.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
           // System.out.println(key+" "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram  kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int i=indexOf(myText,kGram,0);
        while(i!=-1){
            if(i<myText.length-kGram.length()){
                follows.add(myText[i+kGram.length()]);
            }
            i=indexOf(myText,kGram,i+1);
           }
        return follows;
    }

}
