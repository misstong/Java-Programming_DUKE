
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordLengths {
    public int indexOfMax(int[] values){
        int ret=0;
        for(int i=1;i<values.length;i++){
            if(values[i]>values[ret]){
                ret=i;
            }
        }
        return ret;
    }
    public void testCountWordLengths(){
        FileResource fr=new FileResource();
        int[] counts=new int[31];
        countWordLengths(fr,counts);
        for(int i=0;i<31;i++){
            System.out.println(counts[i]+" words of length "+i);
        }
        System.out.println(indexOfMax(counts));
    }
    public void countWordLengths(FileResource resource,int[] counts){
        int len;
        for(String word:resource.words()){
            len=word.length();
            if(!Character.isLetter(word.charAt(len-1))){
                len-=1;
            }
            if(!Character.isLetter(word.charAt(0))){
                len-=1;
            }
            if(len>=counts.length){
                counts[counts.length-1]+=1;
            }else if(len<0){
                counts[0]+=1;
            }else{                
                counts[len]+=1;
            }
            //System.out.println(word+" length is "+len);
        }
       
    }
            

}
