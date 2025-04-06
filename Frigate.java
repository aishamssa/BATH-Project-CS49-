package wars;

/**
 * Write a description of class Frigate here.
 *
 * @author (Ohemaa&Ishika)
 * @version (a version number or a date)
 */
import java.io.Serializable;

 public class Frigate extends Ship implements Serializable
{
    // instance variables - replace the example below with your own
    private int noOfCannons;
    private boolean pinnace;
    //Subclass of Ship
    /**
     * Constructor for objects of class Frigate
     * 
     */
    public Frigate(String name ,String captain,int battleSkill ,int cannons ,boolean pin)
    {
        // initialise instance variables
        super(name ,captain ,battleSkill,cannons*10,ShipState.RESERVE);
        this.noOfCannons = cannons;
        this.pinnace = pin;
        
        
    }

    public boolean hasPinnace()
    {
        return pinnace; // returns T/F if ship has pinnance
    }
    
    
    public void setCannons( int can)
    {
        noOfCannons = can;
        setCommissionFee(can * 10); //  updates commission fee if cannons change
    }
    
    
    public int calculateComissionFee()
      {
        return noOfCannons*10;    
      }
    //Frigates have their own individual battle skill
    //Friagte commission fee baesd on number of cannons
    //1 cannnon = 10 pounds
    //2 cannon = 20 ponund   
    
    public String toString()
    {
     return super.toString() + 
             "\nNumber of  cannons: "+ noOfCannons +
             " \nHas Pinnace" + pinnace;
    }
}
 