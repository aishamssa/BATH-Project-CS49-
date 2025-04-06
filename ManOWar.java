package wars;
import java.io.Serializable;
/**
 * Write a description of class ManOWar here.
 *
 * @author (Ohemaa&Ishika)
 * @version (a version number or a date)
 */

public class ManOWar extends Ship implements Serializable
{
    // instance variables - replace the example below with your own
    private int noOfMarines;
    private int  noOfDecks;
    

    /**
     * Constructor for objects of class ManOWar
     */
    public ManOWar(String name , String captain,int battleSkill, int decks, int marines )
    {
      
        
        super(name ,captain,battleSkill, (decks == 2) ? 300 : 500,ShipState.RESERVE);
       
        noOfMarines = marines;
        noOfDecks = decks ; 
       
    }
   
    
     //updates the number of marines
    public int setMarines(int no)
    {
        noOfMarines = no;
        return  noOfMarines;     
    }

    public String toString()
    {
        return super.toString() +
            "\nMarines: " + noOfMarines
            + "\nDecks: " + noOfDecks ;
    }
}
