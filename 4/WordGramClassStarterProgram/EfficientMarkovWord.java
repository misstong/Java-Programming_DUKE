
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovWord implements IMarkovModel{
     private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> myMap;
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder=order;
        myMap=new HashMap<WordGram,ArrayList<String>>();
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
        buildMap();
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
    
    public void buildMap(){
        for(int i=0;i<=myText.length-myOrder;i++){
            WordGram wg=new WordGram(myText,i,myOrder);
            if(myMap.containsKey(wg)){
                if(i<myText.length-myOrder){
                    myMap.get(wg).add(myText[i+myOrder]);
                }
            }
            else{
                ArrayList<String> list=new ArrayList<String>();
                if(i<myText.length-myOrder){
                    list.add(myText[i+myOrder]);
                }
                myMap.put(wg,list);
            }
        }
    }
              
      public void printHashMapInfo(){
           for(WordGram s:myMap.keySet()){
            System.out.println(""+s+myMap.get(s));
            
        }
             System.out.println("It has "+myMap.size()+"  keys in the HashMap");
        int maxSize=0;
        for(WordGram s:myMap.keySet()){
            //System.out.println(s+mMap.get(s));
            ArrayList<String> list=myMap.get(s);
            if(list.size()>maxSize){
                maxSize=list.size();
            }
        }
        System.out.println("The maximum number of keys following a key is "+maxSize);
        ArrayList<WordGram> li=new ArrayList<WordGram>();
        for(WordGram s:myMap.keySet()){
            ArrayList<String> list=myMap.get(s);
            if(list.size()==maxSize){
                li.add(s);
            }
        }
        System.out.println(li);
    }
    private ArrayList<String> getFollows(WordGram  kGram) {      
        return myMap.get(kGram);
    }

}
