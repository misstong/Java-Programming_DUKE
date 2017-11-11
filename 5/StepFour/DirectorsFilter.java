
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String myDirectors;
    DirectorsFilter(String director){
        myDirectors=director;
    }
    public boolean satisfies(String id){
        String d=MovieDatabase.getDirector(id);
        String[] dirs=myDirectors.split(",");
        for(String dir:dirs){
            if(d.indexOf(dir)!=-1)
            {
                return true;
            }
        }
        return false;
    }

}
