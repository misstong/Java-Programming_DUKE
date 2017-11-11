
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class TestCaesarCipher {
    public String breakCaesarCipher(String encrypted){
        int[] counts=new int[26];
        String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i=0;i<encrypted.length();i++){
            char curr=Character.toUpperCase(encrypted.charAt(i));
            int index=alphabet.indexOf(curr);
            if(index!=-1){
                counts[index]+=1;
            }
        }
        
        int max=maxIndex(counts);
        int dkey=max-4;
        CaesarCipher cc=new CaesarCipher(dkey);
        String dec=cc.decrypt(encrypted);
        return dec;
    }
        
     public void simpleTests(){
        FileResource fr=new FileResource();
        String s=fr.asString();
        CaesarCipher cc=new CaesarCipher(18);
        String encrypted=cc.encrypt(s);
        System.out.println("encrypted: "+encrypted);
        System.out.println("decrypted: "+cc.decrypt(encrypted));
        System.out.println("decrypted auto: "+breakCaesarCipher(encrypted));
    }
     public int maxIndex (int[] values){
        int ret=0;
        for(int i=1;i<values.length;i++){
            if(values[i]>values[ret]){
                ret=i;
            }
        }
        return ret;
    }
   

}
