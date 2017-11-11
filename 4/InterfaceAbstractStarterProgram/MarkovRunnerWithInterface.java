
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size,int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    public void testHashMap(){
         FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
        EfficientMarkovModel em=new EfficientMarkovModel(5);
        em.setRandom(531);
        em.setTraining(st);
        em.buildMap();
        em.printHashMapInfo();
    }
    public void compareMethods(){
          FileResource fr = new FileResource("data/hawthorne.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		long t1=System.nanoTime();
		  MarkovModel mThree = new MarkovModel(2);
        runModel(mThree, st, size,42);
        long t2=System.nanoTime();
        
        long t3=System.nanoTime();
		  EfficientMarkovModel mThree1 = new EfficientMarkovModel(2);
        runModel(mThree1, st, size,42);
        long t4=System.nanoTime();
        
        System.out.println("time slow "+(t2-t1));
        System.out.println("time fast "+(t4-t3));
    }
        
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size,42);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size,42);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,42);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size,42);

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
