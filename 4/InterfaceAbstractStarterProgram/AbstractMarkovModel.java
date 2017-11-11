
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void setRandom(int seed){
		myRandom = new Random(seed);
	}
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
	    ArrayList<String> list=new ArrayList<String>();
	    int index=0;
	    while((index=myText.indexOf(key,index))!=-1){
	        if(index+key.length()==myText.length()){
	            break;
	           }
	            
	        list.add(myText.substring(index+key.length(),index+key.length()+1));
	        index+=1;
	           
	       }
	       return list;
	   }

}
