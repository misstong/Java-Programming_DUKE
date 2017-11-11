
/**
 * Write a description of CSV here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class CSV {
    public void bigExporters(CSVParser parser,String amount){
        for(CSVRecord r:parser){
            if(r.get("Value (dollars)").length()>amount.length())
            {
                System.out.println(r.get("Country")+" "+r.get("Value (dollars)"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser,String exportItem ){
        int i=0;
        for(CSVRecord r:parser){
            if(r.get("Exports").contains(exportItem)){
                i+=1;
            }
        }
        return i;
    }
    public void listExportersTwoProducts(CSVParser parser,String exportItem1,String exportItem2){
        for(CSVRecord r:parser){
            String export=r.get("Exports");
            if(export.contains(exportItem1)&&export.contains(exportItem2))
            {
                System.out.println(r.get("Country"));
            }
        }
    }
    public void tester(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //countryInfo(parser,"Germany");
        parser=fr.getCSVParser();
        //listExportersTwoProducts(parser,"cotton","flowers");
        parser=fr.getCSVParser();
        //System.out.println(numberOfExporters(parser,"cocoa"));
        parser=fr.getCSVParser();
        bigExporters(parser,"$999,999,999,999");
        
    }
    public String countryInfo(CSVParser parser,String country){
        String ret;
        for(CSVRecord r:parser){                    
            if(r.get("Country").equals(country)){
                ret=r.get("Country")+":"+r.get("Exports")+":"+r.get("Value (dollars)");
                return ret;
            }
        }
        return "NOT FOUND";
    }
    

}
