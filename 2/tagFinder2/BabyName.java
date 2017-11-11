
/**
 * Write a description of BabyName here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyName {
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        String fileName="D:\\ThunderDownload\\Java programming Coursera\\2\\us_babynames_by_year\\"+"yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        CSVParser parser=fr.getCSVParser(false);
        int sum=0;
        for(CSVRecord r:parser){
            if(r.get(1).equals(gender)){
                if(r.get(0).equals(name)){
                    return sum;
                }
                sum+=Integer.parseInt(r.get(2));
            }
        }
        return sum;
    }
    public double getAverageRank(String name,String gender){
        DirectoryResource dr=new DirectoryResource();
        int sum=0,num=0;
        for(File f:dr.selectedFiles()){
            num+=1;
            int year=Integer.parseInt(f.getName().substring(3,7));
            int rank=getRank(year,name,gender);
            if(rank!=-1){
                sum+=rank;
            }
        }
        if(sum==0){
            return -1.0;
        }else{
            return (double)sum/num;
        }
    }
           
            
    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr=new DirectoryResource();
        int highest=-1,highestyear=-1;
        for(File f:dr.selectedFiles()){
            int year=Integer.parseInt(f.getName().substring(3,7));
            int rank=getRank(year,name,gender);
            if(highest==-1&&rank!=-1){
                highest=rank;
                highestyear=year;
            }else if(rank!=-1&&rank<highest){
                highest=rank;
                highestyear=year;
            }
        }
        return highestyear;
        
    }                                
    public void whatIsNameInYear(String name,int year,int newYear,String gender){     
        int rank=getRank(year,name,gender);
        String newName=getName(newYear,rank,gender);
        if(gender.equals("F")){
            System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);
        }
        else{
            System.out.println(name+" born in "+year+" would be "+newName+" if he was born in "+newYear);
        }                         
    }        
    String getName(int year,int rank,String gender){
        String fileName="D:\\ThunderDownload\\Java programming Coursera\\2\\us_babynames_by_year\\"+"yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        CSVParser parser=fr.getCSVParser(false);
        int i=0;
        for(CSVRecord r:parser){
            if(r.get(1).equals(gender)){
                i+=1;
                if(rank==i){
                    return r.get(0);
                }
            }
        }
        return "NO NAME";
    }
                
    public int getRank(int year,String name,String gender){
        String fileName="D:\\ThunderDownload\\Java programming Coursera\\2\\us_babynames_by_year\\"+"yob"+year+".csv";
        FileResource fr=new FileResource(fileName);
        CSVParser parser=fr.getCSVParser(false);
        int rank=0;
        for(CSVRecord r:parser){
            if(r.get(1).equals(gender)){
                rank+=1;
                if(r.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
            
    public void totalBirths(CSVParser parser){
        int total=0,fnum=0,mnum=0,ufnum=0,umnum=0,totalName=0;
        for(CSVRecord r:parser){
            total+=Integer.parseInt(r.get(2));
            totalName+=1;
            //System.out.println(r.get(1));
            if(r.get(1).equals("F")){
                ufnum+=1;
                fnum+=Integer.parseInt(r.get(2));
            }else{
                umnum+=1;
                mnum+=Integer.parseInt(r.get(2));
            }
        }
        System.out.println("total babies "+total);
        System.out.println("number of girls "+fnum);
        System.out.println("number of boys "+mnum);
        System.out.println("number of unique girls names"+ufnum);
        System.out.println("number of unique boys names "+umnum);
        System.out.println("number of total names"+totalName);
    }
    public void tester(){
        //FileResource fr=new FileResource();
        //CSVParser parser=fr.getCSVParser(false);
        //totalBirths(parser);
        
        //System.out.println(getRank(1971,"Frank","M"));
        
        //System.out.println(getName(1982,450,"M"));
        
        //whatIsNameInYear("Owen",1974,2014,"M");
        
       //System.out.println(yearOfHighestRank("Mich","M"));
        
       // System.out.println(getAverageRank("Susan","F"));
        //System.out.println(getAverageRank("Robert","M"));
         
        System.out.println(getTotalBirthsRankedHigher(1990,"Drew","M"));
    }
            
        
    public void readOneFile(int year){
        String fname="data/yob"+year+".txt";
        FileResource fr=new FileResource(fname);
        CSVParser parser=fr.getCSVParser(false);//no header row
        for(CSVRecord rec:parser){
            String name=rec.get(0);
            String gender=rec.get(1);
        }
    }

}
