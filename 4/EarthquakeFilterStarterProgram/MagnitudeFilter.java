
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double maxMag;
    private double minMag;
    public MagnitudeFilter(double min,double max){
        maxMag=max;
        minMag=min;
    }
    public boolean satisfies(QuakeEntry qe){
        if(qe.getMagnitude()>=minMag&&qe.getMagnitude()<=maxMag){
            return true;
        }
        return false;
    }
    public String getName(){
        return "MagnitudeFilter";
    }

}
