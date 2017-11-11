
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private ArrayList<String> flagList;
    private ArrayList<String> usedCateg;
    private int count;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        flagList=new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedCateg=new ArrayList<String>();
        
    }
    
    public GladLibMap(String source){
        flagList=new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
        usedCateg=new ArrayList<String>();
        
    }
    public int totalWordsInMap(){
        int num=0;
        for(String key:myMap.keySet()){
            num+=myMap.get(key).size();
        }
        return num;
    }
    public int totalWordsConsidered(){
        int num=0;
        for(String s:usedCateg){
            num+=myMap.get(s).size();
        }
        return num;
    }
        
    private void initializeFromSource(String source) {
        String[] types={"adjective","noun","color","country",
            "name","animal","timeframe","verb","fruit"};
        myMap=new HashMap<String,ArrayList<String>>();
        for(int i=0;i<types.length;i++){
            ArrayList<String> list=readIt(source+"/"+types[i]+".txt");
            myMap.put(types[i],list);
           }
            
        
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        
        return source.get(index);
    }
    
    private String getSubstitute(String label) {              
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if(myMap.containsKey(label)){
            ArrayList<String> list=myMap.get(label);
             if(!usedCateg.contains(label)){
                 usedCateg.add(label);
                }
            
            return randomFrom(list);
        }
       
        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(flagList.indexOf(sub)!=-1){
            sub = getSubstitute(w.substring(first+1,last));
          }
          flagList.add(sub);
          count++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
                
                
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
                
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        flagList.clear();
        
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n"+count);
        System.out.println("total word: "+totalWordsInMap ());
        System.out.println("total word considered: "+ totalWordsConsidered());
    }
    


}

