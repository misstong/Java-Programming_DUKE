
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return  myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length();i++){
            sb.append(myWords[i]+" ");
        }
        ret=sb.toString();

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if(this.length()!=other.length()){
            return false;
        }
        for(int i=0;i<length();i++){
            if(!wordAt(i).equals(other.wordAt(i))){
                return false;
            }
        }
        return true;

    }
    public int hashCode(){
        String s=this.toString();
        return s.hashCode();
    }

    public WordGram shiftAdd(String word) {	
        String[] words=new String[length()];
        System.arraycopy(myWords,1,words,0,length()-1);
        words[length()-1]=word;
        WordGram out = new WordGram(words, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        
        return out;
    }

}