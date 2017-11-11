
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class CaesarCipher {
    public String decryptTwoKeys(String encrypted){
        String s1=halfOfString(encrypted,0);
        String s2=halfOfString(encrypted,1);    
        int key1=getKey(s1);
        int key2=getKey(s2);
        System.out.println("key1: "+key1+" key2: "+key2);
        
        return encryptTwoKeys(encrypted,key1,key2);
    }
        
    public int getKey(String s){
         int[] counts=new int[26];
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<s.length();i++){
            char curr=Character.toUpperCase(s.charAt(i));
            int index=alphabet.indexOf(curr);
            if(index!=-1){
                counts[index]+=1;
            }
        }
        WordLengths wl=new WordLengths();
        int max=wl.indexOfMax(counts);
        int dkey=max-4;
        if(dkey<0){
            dkey+=26;
        }
        return 26-dkey;
    }
    public String halfOfString(String message,int start){
        StringBuilder sb=new StringBuilder();
        for(int i=start;i<message.length();i+=2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    public String decrypt(String encrypted){
        int[] counts=new int[26];
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<encrypted.length();i++){
            char curr=Character.toUpperCase(encrypted.charAt(i));
            int index=alphabet.indexOf(curr);
            if(index!=-1){
                counts[index]+=1;
            }
        }
        WordLengths wl=new WordLengths();
        int max=wl.indexOfMax(counts);
        int dkey=max-4;
        String dec=encrypt(encrypted,26-dkey);
        return dec;
    }
            
        
    public String encryptTwoKeys(String input,int key1,int key2){
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String xalphabet=alphabet.substring(key1)+alphabet.substring(0,key1);
        String yalphabet=alphabet.substring(key2)+alphabet.substring(0,key2);
        StringBuilder sb=new StringBuilder(input);
        for(int i=0;i<input.length();i++){
            char curr=input.charAt(i);
            char curUpper=Character.toUpperCase(curr);
            int index=alphabet.indexOf(curUpper);
            if(index!=-1){
                char rep;
                if(i%2==0){
                     rep=xalphabet.charAt(index);
                    if(Character.isLowerCase(curr)){
                        rep=Character.toLowerCase(rep);
                    }
                }
                else{
                     rep=yalphabet.charAt(index);
                    if(Character.isLowerCase(curr)){
                        rep=Character.toLowerCase(rep);
                    }
                }
                    
                sb.setCharAt(i,rep);
            }
        }
        return sb.toString();
    }
                    
    public String encrypt(String input,int key){
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String xalphabet=alphabet.substring(key)+alphabet.substring(0,key);
        StringBuilder sb=new StringBuilder(input);
        for(int i=0;i<input.length();i++){
            char curr=input.charAt(i);
            char curUpper=Character.toUpperCase(curr);
            int index=alphabet.indexOf(curUpper);
            if(index!=-1){
                char rep=xalphabet.charAt(index);
                if(Character.isLowerCase(curr)){
                    rep=Character.toLowerCase(rep);
                }
                
                sb.setCharAt(i,rep);
            }
        }
        return sb.toString();
    }
    
    public void testCaesar(){
        FileResource fr=new FileResource();
        String message=fr.asString();
        int key=3;
        String encrypted=encrypt(message,key);
        System.out.println("key is "+key+"\n"+encrypted);
        System.out.println(decrypt(encrypted));
    }
    public void test(){
        //System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
       //System.out.println(encrypt("First Legion", 23));
       // System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15));
        //System.out.println(encryptTwoKeys("Hfs cpwewloj loks cd Hoto kyg Cyy.", 12, 2));
        //System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",8,21));
        //System.out.println(halfOfString("Qbkm Zgis", 0));
        FileResource fr=new FileResource();
        String s=fr.asString();
        System.out.println(decryptTwoKeys(s));
    }
        

}
