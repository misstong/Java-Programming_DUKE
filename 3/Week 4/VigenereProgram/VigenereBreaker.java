import java.util.*;
import edu.duke.*;
import java.io.*;
public class VigenereBreaker {
   
    public int countWords(String message,HashSet<String> dictionary){
        String[] words=message.split("\\W+");
        int sum=0;
        for(int i=0;i<words.length;i++){
            if(dictionary.contains(words[i].toLowerCase())){
                sum+=1;
            }
        }
        return sum;
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs=new HashSet<String>();
        for(String s:fr.lines()){
            hs.add(s.toLowerCase());
        }
        return hs;
    }
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb=new StringBuilder();
        for(int i=whichSlice;i<message.length();i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HER
        CaesarCracker cc=new CaesarCracker(mostCommon);
        String s;
        // System.out.println("the key is:");
        for(int i=0;i<klength;i++){
            s=sliceString(encrypted,i,klength);
            key[i]=cc.getKey(s);
            //System.out.print(key[i]+" ");
        }
       
        
        return key;
    }
     public String breakForLanguage(String encrypted,HashSet<String> dictionary, char mostCommon){
         int max=0;
         String s=null;
         int keyLength=0;
         for(int i=1;i<=100;i++){
             int[] key=tryKeyLength(encrypted,i,mostCommon);
             VigenereCipher vc=new VigenereCipher(key);
             String line=vc.decrypt(encrypted);
             int count=countWords(line,dictionary);
             if(count>max){
                 max=count;
                 s=line;
                 keyLength=i;
                }
            }
           // System.out.println("key length: "+keyLength);
            //System.out.println("valid words: "+max);
            return s;
        }
             
     public char mostCommonCharIn(HashSet<String> dictionary){
         HashMap<Character,Integer> myMap=new HashMap<Character,Integer>();
         for(String word:dictionary){
             int len=word.length();
             for(int i=0;i<len;i++){
                 char c=word.charAt(i);
                 if(myMap.containsKey(c)){
                     myMap.put(c,myMap.get(c)+1);
                    }
                    else{
                        myMap.put(c,1);
                    }
                }
            }
            char mostCommon='a';
            int max=0;
            for(char c:myMap.keySet()){
                int i=myMap.get(c);
                if(i>max){
                    mostCommon=c;
                    max=i;
                }
            }
            return mostCommon;
        }
      public String breakForAllLanguages(String encrypted,HashMap<String,HashSet<String>> languages){
          int max=0;
          String ret=null,lan=null;
          
          for(String lang:languages.keySet()){
              String message=breakForLanguage(encrypted,languages.get(lang),mostCommonCharIn(languages.get(lang)));
              int count=countWords(message,languages.get(lang));
              if(count>max){
                  max=count;
                  ret=message;
                  lan=lang;
                }
            }
            System.out.println("lang is "+lan);
            return ret;
        }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr=new FileResource();
        String s=fr.asString();
        
        HashMap<String,HashSet<String>> myMap=new HashMap<String,HashSet<String>>();
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            FileResource frd=new FileResource(f);
             HashSet<String> hs=readDictionary(frd);
             String name=f.getName();
             myMap.put(name,hs);
             System.out.println(name+" dictionary is done");
            }
         System.out.println("all dictionary is done");
       String message=breakForAllLanguages(s,myMap);
       System.out.println(message.substring(0,100));
        //String line=breakForLanguage(s,hs);
       // int[] key=tryKeyLength(s,38,'e');
       // VigenereCipher vc=new VigenereCipher(key);
       // String line=vc.decrypt(s);
       // int index=line.indexOf('.');
      // System.out.println(line); 
      //System.out.println(countWords(line,hs));
       // System.out.println(line.substring(0,100));
    }
    
    public void test(){
        //System.out.println(sliceString("abcdefghijklm", 4, 5));
        FileResource fr=new FileResource();
       HashSet<String> hs=readDictionary(fr);
       for(String s:hs){
           //System.out.println(s);
        }
        System.out.println(countWords("You should make a herself like hallmark limitless",hs));
        System.out.println("mostCommonCharIn: "+mostCommonCharIn(hs));
    }
    
}
