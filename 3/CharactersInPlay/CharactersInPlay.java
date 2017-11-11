
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CharactersInPlay {
    private ArrayList<String> myChar;
    private ArrayList<Integer> myFreqs;
    public CharactersInPlay(){
        myChar=new ArrayList<String>();
        myFreqs=new ArrayList<Integer>();
    }
    public void charactersWithNumParts(int num1,int num2){
        for(int i=0;i<myFreqs.size();i++){
            if(myFreqs.get(i)>=num1&&myFreqs.get(i)<=num2){
                System.out.println(myChar.get(i)+" "+myFreqs.get(i));
            }
        }
    }
    public void tester(){
        findAllCharacters();
        charactersWithNumParts(10,15);
        int index=findIndexOfMax();
        System.out.println("the most: "+myChar.get(index)+" "+myFreqs.get(index));
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
    public void findAllCharacters(){
        FileResource fr=new FileResource();
        String person;
        for(String line:fr.lines()){
            int index=line.indexOf(".");
            if(index!=-1){
                person=line.substring(0,index);
                update(person);
            }
        }
    }
                
    public void update(String person){
        int index=myChar.indexOf(person);
        if(index==-1){
            myChar.add(person);
            myFreqs.add(1);
        }
        else{
            myFreqs.set(index,myFreqs.get(index)+1);
        }
    }
    

}
