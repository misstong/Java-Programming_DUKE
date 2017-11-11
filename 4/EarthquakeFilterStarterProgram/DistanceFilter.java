
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private double maxDist;
    private Location from;
    public DistanceFilter(double dist,Location where){
        from=where;
        maxDist=dist;
    }
    public boolean satisfies(QuakeEntry qe){
        if(qe.getLocation().distanceTo(from)<=maxDist){
            return true;
        }
        return false;
    }
    public String getName(){
        return "DistanceFilter";
    }

}
