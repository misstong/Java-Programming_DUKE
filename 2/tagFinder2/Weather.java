
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Weather {
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double sum=0,num=0;
        for(CSVRecord r:parser){
            if(r.get("TemperatureF").equals(" -9999")){
                continue;
            }
            if(Double.parseDouble(r.get("Humidity"))>=value){
                num+=1;
             
                sum+=Double.parseDouble(r.get("TemperatureF"));
            }
        }
        if(num==0)
            return -9999;
        return sum/num;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        double avg=averageTemperatureWithHighHumidityInFile(parser,80);
        if(avg==-9999){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is "+avg);
        }
        
    }
    public double averageTemperatureInFile(CSVParser parser){
        double sum=0,num=0;
        for(CSVRecord r:parser){
            num+=1;
             if(r.get("TemperatureF").equals(" -9999")){
                continue;
            }
            sum+=Double.parseDouble(r.get("TemperatureF"));
        }
        return sum/num;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        System.out.println("Average temperature in file is "+averageTemperatureInFile(parser));
    }
            
            
    public CSVRecord  lowestHumidityInManyFiles (){
        DirectoryResource dr=new DirectoryResource();
        CSVRecord largestSoFar=null;
        
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord current=lowestHumidityInFile(fr.getCSVParser());
            
            if(largestSoFar==null){
                largestSoFar=current;
                
            }
            else{
                 Double currentTemp=Double.parseDouble(current.get("Humidity"));
                 Double coldestTemp=Double.parseDouble(largestSoFar.get("Humidity"));
                 if(currentTemp<coldestTemp){
                     largestSoFar=current;
                     
                 }
            }
        }
        return largestSoFar;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord r=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+r.get("Humidity")+" at "+r.get("DateUTC"));
    }
    public String fileWithColdestTemperature(){
        DirectoryResource dr=new DirectoryResource();
        CSVRecord largestSoFar=null;
        String name=null;
        for(File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord current=coldestHourInFile(fr.getCSVParser());
            
            if(largestSoFar==null){
                largestSoFar=current;
                name=f.getName();
            }
            else{
                 Double currentTemp=Double.parseDouble(current.get("TemperatureF"));
                 Double coldestTemp=Double.parseDouble(largestSoFar.get("TemperatureF"));
                 if(currentTemp<coldestTemp){
                     largestSoFar=current;
                     name=f.getName();
                 }
            }
        }
        return name;
                
    }
    public void testFileWithColdestTemperature(){
        String fn=fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+fn);
        String pathname="D:\\ThunderDownload\\Java programming Coursera\\2\\nc_weather\\2013\\";
        FileResource fr=new FileResource(pathname+fn);
        CSVRecord r=coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was "+r.get("TemperatureF"));
        
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "+csv.get("Humidity")+" at "+csv.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord coldest=null;
        for(CSVRecord r:parser){
            if(r.get("Humidity").equals("N/A")){
                continue;
            }
            if(coldest==null){
                coldest=r;
            }
            else{
                Double currentTemp=Double.parseDouble(r.get("Humidity"));
                Double coldestTemp=Double.parseDouble(coldest.get("Humidity"));
                if(currentTemp<coldestTemp){
                    coldest=r;
                }
            }
        }
        return coldest;
    }
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldest=null;
        for(CSVRecord r:parser){
            if(r.get("TemperatureF").equals("-9999")){
                continue;
            }
            if(coldest==null){
                coldest=r;
            }
            else{
                Double currentTemp=Double.parseDouble(r.get("TemperatureF"));
                Double coldestTemp=Double.parseDouble(coldest.get("TemperatureF"));
                if(currentTemp<coldestTemp){
                    coldest=r;
                }
            }
        }
        return coldest;
             
    }
    public void testColdestHourInFile(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        CSVRecord r=coldestHourInFile(parser);
        System.out.println(r.get("TemperatureF"));
    }

}
