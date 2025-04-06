package wars;
import java.io.Serializable;
/**
 * Write a description of class Warchest here.
 *
 * @author (Ohemaa)
 * @version (a version number or a date)
 */
public class Warchest implements Serializable
{
    // instance variables - replace the example below with your own
    private int prizeMoney;
    private int payment;
    private int warChest;

    /**
     * Constructor for objects of class Warchest
     */
    public Warchest()
    {
        // initialise instance variables
        
    }
    
    public int getWarChest()
    {
        return warChest;
    }

    public int deductPound(int decution)
    {
     return warChest-decution;
     
    }
    
    public int addPounds( int addition)
    {
        return warChest+addition;
    }
    
    public String toString()
    {
        return "Prize Money :" + prizeMoney + "/n" +
        "Payment :" + payment + "/n" +
        " War chest :" + warChest ;
    }
    
}
