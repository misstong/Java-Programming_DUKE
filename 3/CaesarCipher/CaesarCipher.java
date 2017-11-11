
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet=alphabet.substring(key)+alphabet.substring(0,key);
        mainKey=key;
    }
    public String decrypt(String input){
        CaesarCipher cc=new CaesarCipher(26-mainKey);
        return cc.encrypt(input);
    }
     public String encrypt(String input){                
        StringBuilder sb=new StringBuilder(input);
        for(int i=0;i<input.length();i++){
            char curr=input.charAt(i);
            char curUpper=Character.toUpperCase(curr);
            int index=alphabet.indexOf(curUpper);
            if(index!=-1){
                char rep=shiftedAlphabet.charAt(index);
                if(Character.isLowerCase(curr)){
                    rep=Character.toLowerCase(rep);
                }
                
                sb.setCharAt(i,rep);
            }
        }
        return sb.toString();
    }

}
