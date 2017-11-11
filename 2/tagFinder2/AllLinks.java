
/**
 * Write a description of AllLinks here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class AllLinks {
    public StorageResource findURLs(String url){
        URLResource ur=new URLResource(url);
        String s=ur.asString();
        int start=0;
        StorageResource sr=new StorageResource();
        while(true){
            int index=s.indexOf("href=",start);
            if(index==-1){
                break;
            }
            int stop=s.indexOf("\"",index+6);
            System.out.println("index: "+index);
            System.out.println("stop: "+stop);
            String candidatelink=s.substring(index+6,stop);
            if(candidatelink.startsWith("http")){
                sr.add(candidatelink);
                //System.out.println(candidatelink);
            }
            start=stop;
        }
        return sr;
        
    }
    public int dotNumber(String url){
        int start=0;
        int num=0;
        while(true){
            int index=url.indexOf(".",start);
            if(index==-1)
            {
                break;
            }
            num+=1;
            start=index+1;
        }
        return num;
            
    }
    public void testURLWithStorage(){
        //String url="http://www.dukelearntoprogram.com/course2/data/manylinks.html";
        String url="http://www.dukelearntoprogram.com/course2/data/newyorktimes.html";
        //findURLs(url);
        StorageResource sr=findURLs(url);
        int snum=0,cnum=0,endnum=0,dotnum=0;
        for(String e:sr.data()){
            System.out.println(e);
            if(e.startsWith("https")){
                snum+=1;
            }
            if(e.indexOf(".com")!=-1){
                cnum+=1;
            }
            if(e.endsWith(".com")||e.endsWith(".com/")){
                endnum+=1;
            }
            dotnum+=dotNumber(e);
        }
        System.out.println(sr.size());
        System.out.println("number of secure links:"+snum);
        System.out.println("number of links that have .com:"+cnum);
        System.out.println("number of links that end with .com or .com/: "+endnum);
        System.out.println("number of dots:"+dotnum);
    }

}
