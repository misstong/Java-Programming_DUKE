/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findProtein(String dna) {
        String ldna=dna.toLowerCase();
        int start = ldna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = ldna.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return dna.substring(start, stop+3);
        }
        stop=ldna.indexOf("tga",start+3);
        if((stop-start)%3==0){
            return dna.substring(start,stop+3);
        }
        stop=ldna.indexOf("taa",start+3);
        if((stop-start)%3==0){
            return dna.substring(start,stop+3);
        }
        else {
            return "";
        }
    }
    public int findStopIndex(String jdna,int index){
        String dna=jdna.toLowerCase();
        int stop1=dna.indexOf("tga",index);
        //System.out.println("stop1: "+stop1);
        if(stop1==-1||(stop1-index)%3!=0){
            stop1=dna.length();
        }
        int stop2=dna.indexOf("taa",index);
        if(stop2==-1||(stop2-index)%3!=0){
            stop2=dna.length();
        }
        int stop3=dna.indexOf("tag",index);
        if(stop3==-1||(stop3-index)%3!=0){
            stop3=dna.length();
        }
        return Math.min(stop1,Math.min(stop2,stop3));
    }
    public void printAll(String jdna){
        String dna=jdna.toLowerCase();
        int start=0;
        while(start<dna.length()){
            int index=dna.indexOf("atg",start);
            if(index==-1){
                break;
            }
            int stop=findStopIndex(dna,index+3);
            //System.out.println("index: "+index);
            //System.out.println("stop: "+stop);
            if(stop<dna.length()){
                System.out.println(jdna.substring(index,stop+3));
                start=stop+3;
            }
            else
                start=index+3;
            
        }
    }
    public StorageResource storeAll(String jdna){
        String dna=jdna.toLowerCase();
        int start=0;
        StorageResource sr=new StorageResource();
        while(start<dna.length()){
            int index=dna.indexOf("atg",start);
            if(index==-1){
                break;
            }
            int stop=findStopIndex(dna,index+3);
            //System.out.println("index: "+index);
            //System.out.println("stop: "+stop);
            if(stop<dna.length()){
               // System.out.println(jdna.substring(index,stop+3));
                sr.add(jdna.substring(index,stop+3));
                start=stop+3;
            }
            else
                start=index+3;
        }
        return sr;
    }
    public float cgRatio(String jdna){
        String dna=jdna.toLowerCase();
        int start=0,sum=0;
        
        while(true){
            int index=dna.indexOf("c",start);
            if(index==-1)
            {
                break;
            }
            sum+=1;
            start=index+1;
        }
        start=0;
        while(true){
            int index=dna.indexOf("g",start);
            if(index==-1)
            {
                break;
            }
            sum+=1;
            start=index+1;
        }
        return ((float)sum)/dna.length();
    }
    public void testStorageFinder(){
        FileResource fr=new FileResource("D:\\ThunderDownload\\Java programming Coursera\\2\\dna\\GRch38dnapart.fa");
        String s=fr.asString();
        //System.out.println(s);
        StorageResource sr=storeAll(s);
        System.out.println(sr.size());
        printGenes(sr);
        
        System.out.println(cgRatio("ATGCCATAG"));
        System.out.println(numOfCTG(s));
        //for(String a:sr.data()){
        //    System.out.println(a);
        //}
    }
    public int numOfCTG(String jdna){
        String dna=jdna.toLowerCase();
        int num=0,start=0;
        while(true){
            int index=dna.indexOf("ctg",start);
            if(index==-1){
                break;
            }
            num+=1;
            start=index+3;
        }
        return num;
    }
    public void printGenes(StorageResource sr){
        int sum=0,sum1=0,len=0;
        for(String a:sr.data()){
            if(a.length()>60){
                //System.out.println(a);
                sum+=1;
            }
            if(cgRatio(a)>0.35){
                //System.out.println(a);
                sum1+=1;
            }
            if(a.length()>len){
                len=a.length();
            }
        }
        //System.out.println(sum);
        //System.out.println(sum1);
        System.out.println("length:"+len);
    }
    public void test(){
        String a="ccatgccctaataaatgtctgtaatgtaga";
        printAll(a);
        String b="ATGAAATGAAAA";
        printAll(b);
        String c="CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        printAll(c);
       // System.out.println(c.indexOf("ATGCTATGA"));
        
    }
    
    public String stopCoden(String gene){
        if (gene.equals(""))
            return "";
        return gene.substring(gene.length()-3,gene.length());
        
    }
    
    public void testing() {
        //String a = "cccatggggtttaaataataataggagagagagagagagttt";
        //String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        //String a = "ATGCCCTAG";
        //String ap = "ATGCCCTAG";
        //String a="AATGCTAGTTTAAATCTGA";
        //String ap="ATGCTAGTTTAAATCTGA";
        //String a="ataaactatgttttaaatgt";
        //String ap="atgttttaa";
        String s="AAATGCCCTAACTAGATTGAAACC";
        System.out.println(findProtein(s));
        String a="acatgataacctaag";
        String ap="";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
            System.out.println("stop for"+stopCoden(ap));
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }
}
