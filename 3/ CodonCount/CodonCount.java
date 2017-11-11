
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class CodonCount {
    private HashMap<String,Integer> myMap;
    public CodonCount(){
        myMap=new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start,String dna){
        myMap.clear();
        for(int i=start;i<dna.length()-3;i+=3){
            String codon=dna.substring(i,i+3);
            if(myMap.containsKey(codon)){
                myMap.put(codon,myMap.get(codon)+1);
            }
            else{
                myMap.put(codon,1);
            }
        }
    }
    public void printCodonCounts(int start,int end){
         for(String s:myMap.keySet()){
            if(myMap.get(s)>=start&&myMap.get(s)<=end){
                System.out.println(s+" "+myMap.get(s));
            }
        }
    }
    public void test(){
        FileResource fr=new FileResource();
        String s=fr.asString();
        System.out.println(s.length());
        int start=0;
        buildCodonMap(start,s);
        System.out.println("Reading frame starting with "+start+" results in "+myMap.size()+" unique codons");
        System.out.println("and most common codon is "+getMostCommonCodon()+" with count "+myMap.get(getMostCommonCodon()));
        printCodonCounts(6,7);
    }
    public String getMostCommonCodon(){
        String max=null;
        int count=0;
        for(String s:myMap.keySet()){
            if(myMap.get(s)>count){
                max=s;
                count=myMap.get(s);
            }
        }
        return max;
    }
            

}
