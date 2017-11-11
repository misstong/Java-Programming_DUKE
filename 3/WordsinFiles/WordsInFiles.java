
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> myMap;
    public WordsInFiles(){
        myMap=new HashMap<String,ArrayList<String>>();
    }
    public void tester(){
        buildWordFileMap();
        int max=maxNumber();
        System.out.println("The greatest number of files a word appears in is "+max);
        ArrayList<String> list=wordsInNumFiles(4);
        System.out.println(list.size());
        printFilesIn("tree");
        for(String s:list){
           // System.out.println(s);
        }
        for(String s:list){
           // System.out.println("\""+s+"\""+" appears in the files:");
           // printFilesIn(s);
        }
    }
        
    public void printFilesIn(String word){
        for(String s:myMap.get(word)){
            System.out.print(s+" ;");
        }
        System.out.println();
    }
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> list=new ArrayList<String>();
         for(String s:myMap.keySet()){
            if(myMap.get(s).size()==number){
                list.add(s);
            }
        }
        return list;
    }
                
    public int maxNumber(){
        int i=0;
        for(String s:myMap.keySet()){
            if(myMap.get(s).size()>i){
                i=myMap.get(s).size();
            }
        }
        return i;
    }
    public void buildWordFileMap(){
        myMap.clear();
        DirectoryResource dr=new DirectoryResource();
        for(File f : dr.selectedFiles()) {
             addWordsFromFile(f);
        }
    }
    
    private void addWordsFromFile(File f){
        String fileName=f.getName();
        FileResource fr=new FileResource(f);
        for(String word:fr.words()){
            if(myMap.containsKey(word)){
                if(!myMap.get(word).contains(fileName)){
                    myMap.get(word).add(fileName);
                }
            }else{
                ArrayList<String> list=new ArrayList<String>();
                list.add(fileName);
                myMap.put(word,list);
            }       
        }
        
    }
                
            

}
