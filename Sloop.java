package wars;
import java.io.Serializable;
/**
 * Write a description of class Sloop here.
 *
 * @author (Ohemaa&Ishika)
 * @version (a version number or a date)
 */
public class Sloop extends Ship implements Serializable
{
    // instance variables - replace the example below with your own
   
    private boolean doctor;

    /**
     * Constructor for objects of class Sloop
     */
    public Sloop(String nm ,String cap,boolean doc )
    {
        // initialise instance variables
        super(nm ,cap ,5 ,0.0 , ShipState.RESERVE);
        this.doctor = doc;
    }
    public void setSloopCommission(double fee)
    {
        setCommissionFee(fee);// inherited method from Ships class
    }
    
    public boolean hasDoctor()
    {
     return doctor; // returns T/F if the ship has a doctor
    }

 
    public String toString()
    {
        return super.toString() + 
                "\nDoctor: " + doctor +
                "\nbattelSkill: " + getShipBattleSkill();
    }
    
}
