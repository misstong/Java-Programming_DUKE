
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class EfficientMarkovModel extends AbstractMarkovModel{
    private int num;
    private HashMap<String,ArrayList<String>> mMap;
    public EfficientMarkovModel(int i) {
        myRandom = new Random();
        num=i;
        mMap=new HashMap<String,ArrayList<String>>();
    }
    

    public void buildMap(){
       
        
        for(int i=0;i<=myText.length()-num;i++){
            String key=myText.substring(i,i+num);
            if(mMap.containsKey(key)){  
                if(i<myText.length()-num){
                    mMap.get(key).add(myText.substring(i+num,i+num+1));
               }
            }else
            {
                   ArrayList<String> list=new ArrayList<String>();
                   if(i<myText.length()-num){
                       list.add(myText.substring(i+num,i+num+1));
                   }
                       
                   mMap.put(key,list);
            }
       }
    }
    
   public ArrayList<String> getFollows(String key){
       System.out.println("override version");
       return mMap.get(key);
    }
    public void test(){
        myText="this is a test a test";
        buildMap();
        ArrayList<String> list=super.getFollows("t");
        System.out.println(list);
    }
    public void printHashMapInfo(){
//         for(String s:mMap.keySet()){
//             System.out.println(s+mMap.get(s));
//         }
        System.out.println("It has "+mMap.size()+"  keys in the HashMap");
        int maxSize=0;
        for(String s:mMap.keySet()){
            //System.out.println(s+mMap.get(s));
            ArrayList<String> list=mMap.get(s);
            if(list.size()>maxSize){
                maxSize=list.size();
            }
        }
        System.out.println("The maximum number of keys following a key is "+maxSize);
        ArrayList<String> li=new ArrayList<String>();
        for(String s:mMap.keySet()){
            ArrayList<String> list=mMap.get(s);
            if(list.size()==maxSize){
                li.add(s);
            }
        }
        System.out.println(li);
    }
    
        
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-num);
        String pre=myText.substring(index,index+num);
            sb.append(pre);
         buildMap();   
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> list=getFollows(pre);
            if(list.size()==0){
                break;
              }
             index = myRandom.nextInt(list.size());
            String next=list.get(index);
            sb.append(next);
            pre=pre.substring(1)+next;
            
        }
        
        return sb.toString();
    }
    public String toString(){
        return "EfficientMarkovModel of "+num;
       }

}
