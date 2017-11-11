
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int myMin;
    private int myMax;
    MinutesFilter(int min,int max){
        myMin=min;
        myMax=max;
    }
    public boolean satisfies(String id){
        int m=MovieDatabase.getMinutes(id);
        return m>=myMin&&m<=myMax;
    }

}
