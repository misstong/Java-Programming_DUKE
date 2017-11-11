
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord markovWord = new MarkovWord(5); 
        markovWord.setRandom(844);
        runModel(markovWord, st, 100); 
    } 
    public void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        long t1=System.currentTimeMillis();
        MarkovWord markovWord = new MarkovWord(2); 
        markovWord.setRandom(42);
        runModel(markovWord, st, 100); 
        long t2=System.currentTimeMillis();
        long t3=System.currentTimeMillis();
        EfficientMarkovWord mk=new EfficientMarkovWord(2);
        mk.setRandom(42);
         runModel(mk, st, 100); 
         long t4=System.currentTimeMillis();
         System.out.println("slow "+(t2-t1));
          System.out.println("fast "+(t4-t3));
        }
        
    public void testHashMap(){
         FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord mk=new EfficientMarkovWord(2);     
         mk.setRandom(65);
        // runModel(mk, st, 100); 
        mk.setTraining(st);
        mk.buildMap();
        mk.printHashMapInfo();
    }
        

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
