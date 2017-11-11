
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class MarkovFour extends AbstractMarkovModel{
     
	
// 	public MarkovFour() {
// 		myRandom = new Random();
// 	}
	
// 	public void setRandom(int seed){
// 		myRandom = new Random(seed);
// 	}
	
// 	public void setTraining(String s){
// 		myText = s.trim();
// 	}
	
	        
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-4);
		String pre=myText.substring(index,index+4);
			sb.append(pre);
			
		for(int k=0; k < numChars-1; k++){
		    ArrayList<String> list=getFollows(pre);
		    if(list.size()==0){
		        break;
		      }
			 index = myRandom.nextInt(list.size());
			String next=list.get(index);
			sb.append(next);
			pre=pre.substring(1)+next;
			
		}
		
		return sb.toString();
	}
	
	public String toString(){
	    return "MarkovModel of order 4";
	   }

}
