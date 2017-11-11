
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    public PhraseFilter(String request,String title){
        where=request;
        phrase=title;
    }
    public boolean satisfies(QuakeEntry qe){
        String title=qe.getInfo();
        if(where.equals("start")){
            return title.startsWith(phrase);
        }else if(where.equals("end")){
            return title.endsWith(phrase);
        }else if(where.equals("any")){
            if(title.indexOf(phrase)!=-1){
                return true;
            }
        }
        return false;
    }
    public String getName(){
        return "PhraseFilter";
    }
        
    

}
