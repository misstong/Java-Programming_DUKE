
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public String emphasize(String phrase,char ch){
        StringBuilder sb=new StringBuilder(phrase);
        char chx=Character.toLowerCase(ch);
        for(int i=0;i<sb.length();i++){
            char curr=Character.toLowerCase(sb.charAt(i));
            if(curr==chx){
                if(i%2==0){
                    sb.setCharAt(i,'*');
                }
                else{
                    sb.setCharAt(i,'+');
                }
            }
        }
        return sb.toString();
    }
    public boolean isVowel(char ch){
        String s="aeiou";
        char chx=Character.toLowerCase(ch);
        int i=s.indexOf(chx);
        if(i==-1){
            return false;
        }
        return true;
    }
    public String replaceVowels(String phrase,char ch){
        StringBuilder sb=new StringBuilder(phrase);
        for(int i=0;i<phrase.length();i++){
            char curr=phrase.charAt(i);
            if(isVowel(curr)){
                sb.setCharAt(i,ch);
            }
            
        }  
        return sb.toString();
    }
    public void test(){
        String s="Hello World";
        //System.out.println(replaceVowels(s,'*'));
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

}
