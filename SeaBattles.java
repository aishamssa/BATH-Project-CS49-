package wars;

import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from the BATHS
 system as required for 5COM2007 Cwk1B BATHS - Feb 2025
 * 
 * @author A.A.Marczyk 
 * @version 16/02/25
 */

public class SeaBattles implements BATHS 
{
    // may have one HashMap and select on stat
    private Map<String, Ship> allShips = new HashMap<>();
    private Map<String, Ship> squadron = new HashMap<>();
    private Map<String, Ship> reserveFleet = new HashMap<>();
    private Map<Integer, Encounter> encounters = new HashMap<>();
    private String admiral;
    private double warChest = 1000;


//**************** BATHS ************************** 
    /** Constructor requires the name of the admiral
     * @param admiral the name of the admiral
     */  
    public  SeaBattles(String adm)
    { 
        this.admiral = adm;
        this.warChest = 1000;
        setupShips();
        setupEncounters();
    }
    
    /** Constructor requires the name of the admiral and the
     * name of the file storing encounters
     * @param admiral the name of the admiral
     * @param filename name of file storing encounters
     */  
    public SeaBattles(String admir, String filename)  //Task 3
    {
      
       this.admiral = admir;
       this.warChest = 1000; 
       setupShips();
       // setupEncounters();
       // uncomment for testing Task 
       readEncounters(filename);
    }
    
    
    /**Returns a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     * @return a String representation of the state of the game,including the name of the 
     * admiral, state of the warChest,whether defeated or not, and the ships currently in 
     * the squadron,(or, "No ships" if squadron is empty), ships in the reserve fleet
     **/
    public String toString()
    {
        String result = "";
        result += "Admiral: " + admiral + "\n";
        result += "War Chest: £" + warChest + "\n";
        result += "Status: " + (isDefeated() ? "DEFEATED" : "ACTIVE") + "\n";

        result += "\n--- Squadron Ships ---\n";
        if (squadron.isEmpty()) {
            result += "No ships commissioned\n";
        } else {
            for (Ship ship : squadron.values()) {
                result += ship.toString() + "\n";
            }
        }

        result += "\n--- Reserve Fleet ---\n";
        if (reserveFleet.isEmpty()) {
            result += "No ships in reserve\n";
        } else {
            for (Ship ship : reserveFleet.values()) {
                result += ship.toString() + "\n";
            }
        }

        return result;
    }
    
    
    /** returns true if War Chest <=0 and the admiral's squadron has no ships which 
     * can be retired. 
     * @returns true if War Chest <=0 and the admiral's fleet has no ships 
     * which can be retired. 
     */
    public boolean isDefeated()
    {
        if (warChest > 0) 
            return false;
        for(Ship s: squadron.values())
        {
            if(s.getShipState() != ShipState.SUNK)
            {
                return false;
            }
        }
        return true;
    }
    
    /** returns the amount of money in the War Chest
     * @returns the amount of money in the War Chest
     */
    public double getWarChest()
    {
        return warChest;
    }
    
    
    /**Returns a String representation of all ships in the reserve fleet
     * @return a String representation of all ships in the reserve fleet
     **/
    public String getReserveFleet()
    {   //assumes reserves is a Hashmap
       if(reserveFleet.isEmpty())
       {
           return "No ships";
       }
       String result = "";
       for(Ship s : reserveFleet.values())
       {
           result += s.toString() + "\n";
       }
       return result;
    }
        
    
    
    /**Returns a String representation of the ships in the admiral's squadron
     * or the message "No ships commissioned"
     * @return a String representation of the ships in the admiral's fleet
     **/
    public String getSquadron()
    {
         if (squadron.isEmpty())
         {
             return "No ships commissioned";
         }
          String result = "";
           for (Ship s : squadron.values())
           {
              result +=s.toString() + "\n";
           }
           return result;
    }   
 
    
    /**Returns a String representation of the ships sunk (or "no ships sunk yet")
     * @return a String representation of the ships sunk
     **/
    public String getSunkShips()
    {
        String result = "";
        for (Ship s: allShips.values()) 
        {
            if(s.getShipState() == ShipState.SUNK)
            {
                result += s.toString() +"\n";
            }
        }
        if (result.isEmpty())
        {
           result += "No ships sunk yet";
        }
            return result;
        }
   
    
    /**Returns a String representation of the all ships in the game
     * including their status
     * @return a String representation of the ships in the game
     **/
    public String getAllShips()
    {
        if (allShips.isEmpty()) 
        {
            return "No ships";
        }
        
        String result = "";
        for(Ship s : allShips.values())
        {
            result += s.toString() + "\n";
        }
        return result;
    }
    
    
    /** Returns details of any ship with the given name
     * @return details of any ship with the given name
     **/
    public String getShipDetails(String nme)
    {
        Ship ship = allShips.get(nme);
        if (ship == null)
        {
           return "No such ship";
        }
        else
        {
           return ship.toString();
        }
    }     
 
    // ***************** Fleet Ships ************************   
    /** Allows a ship to be comissioned to the admiral's squadron, if there 
     * is enough money in the War Chest for the commission fee.The ship's 
     * state is set to "active"
     * @param nme represents the name of the ship
     * @return "Ship commissioned" if ship is commissioned, "Not found" if 
     * ship not found, "Not available" if ship is not in the fleet, "Not 
     * enough money" if not enough money in the warChest
     **/        
    public String commissionShip(String nme)
    {
        Ship ship = reserveFleet.get(nme);
        
        if(ship == null)
        { 
            return "- Ship not found"; 
        }
        if (!ship.getShipState().equals(ShipState.RESERVE)) 
        {
            return "Not available";
        }
       
        if(warChest < ship.getCommissionFee())
        {
            return "Not enough money";
        }
          
        reserveFleet.remove(nme);
        squadron.put(nme,ship);
        warChest -= ship.getCommissionFee();
        ship.commission();
        
        return "Ship commissioned";
               
    }
        
    /** Returns true if the ship with the name is in the admiral's squadron, false otherwise.
     * @param nme is the name of the ship
     * @return returns true if the ship with the name is in the admiral's squadron, false otherwise.
     **/
    public boolean isInSquadron(String nme)
    {
        return squadron.containsKey(nme);
    }
    
    /** Decommissions a ship from the squadron to the reserve fleet (if they are in the squadron)
     * pre-condition: isInSquadron(nme)
     * @param nme is the name of the ship
     * @return true if ship decommissioned, else false
     **/
    public boolean decommissionShip(String nme)
    {
         if (!isInSquadron(nme)) return false;

        Ship ship = squadron.remove(nme);
        ship.deCommission();
        reserveFleet.put(nme, ship);
        warChest += ship.getCommissionFee() / 2;
        return true;
    }
    
  
    /**Restores a ship to the squadron by setting their state to ACTIVE 
     * @param the name of the ship to be restored
     */
    public void restoreShip(String ref)
    {
        Ship ship = squadron.get(ref);
        if (ship != null && ship.getShipState() == ShipState.SUNK) 
        {
            ship.commission(); // Reset to ACTIVE
        }
    }
    
//**********************Encounters************************* 
    /** returns true if the number represents a encounter
     * @param num is the reference number of the encounter
     * @returns true if the reference number represents a encounter, else false
     **/
     public boolean isEncounter(int num)
     {
         return encounters.containsKey(num) ;
     }
     
     
    /** Retrieves the encounter represented by the encounter 
      * number.Finds a ship from the fleet which can fight the 
      * encounter. The results of fighting an encounter will be 
      * one of the following: Encounter won by...(ship reference and name) 
      *  add prize money to War Chest and ship's state is set to RESTING,  Encounter 
      * lost as no ship available  deduct prize money from the War Chest,Encounter 
      * lost on battle skill level and (ship name) sunk/lost" - deduct prize money from 
      * War Chest and ship state set to sunk. If an encounter is lost and admiral 
      * is completely defeated, add �You have been defeated  to the output.
      * Ensure that the state of the war chest is also included in the return message.
      * @param encNo is the number of the encounter
      * @return a String showing the result of fighting the encounter
      */ 
    public String fightEncounter(int encNo)
    {
       Encounter e = encounters.get(encNo);
       if (e == null)
       {
           return "No such encouter";
       }
       
       Ship selected = null ;

           for(Ship s : squadron.values())
           {
              if(s.isAvailableForEncounter())
              {
               selected = s;
               break;
              }
           }


           // no availabe  ships
           if(selected == null)
           {
               warChest -= e.getPrizeMoney();
               if (isDefeated())
               {
                   return"No ships available. War chest is now £" + warChest + ". You have been defeated.";
               }
               return "No ships available for encounter. War chest is now £" + warChest;
           }
           // Ship can figth - compare skill levels
           if(selected.getShipBattleSkill() >=  e.getSkillLevel())
           {
               warChest += e.getPrizeMoney();
               selected.deCommission(); // ship needs rest
               return"Encouter won by " + selected.getShipName() + " . War chest is now £ " + warChest;
           }
           else
           {
               selected.sink(); // shipmloee fight
               warChest -= e.getPrizeMoney();

               if(isDefeated())
               {
                   return selected.getShipName() + " Lost the encounter and was sunk. War chest is now £" + warChest + ". You have been DEFEATED!!";
               }
              return "Encounter lost. " + selected.getShipName()+ " was sunk. War chest is now £ " + warChest;
           }
       
       }

 

    /** Provides a String representation of an encounter given by 
     * the encounter number
     * @param num the number of the encounter
     * @return returns a String representation of a encounter given by 
     * the encounter number
     **/
    public String getEncounter(int num)
    {
        Encounter e = encounters.get(num);
            if (e != null)
            {   
                return e.toString();
            }
        return "\nNo such encounter";
    }
    
    /** Provides a String representation of all encounters 
     * @return returns a String representation of all encounters
     **/
    public String getAllEncounters()
    {
        if (encounters == null || encounters.isEmpty()) {
        return "No encounters";
    } else {
        String results = "";

        for (Encounter e : encounters.values()) {
            results += e.toString() + "\n";
        }

        return results;
    }

    }
    

    //****************** private methods for Task 4 functionality*******************
    //*******************************************************************************
     private void setupShips()
     {
         Ship s1 = new ManOWar("Victory", "Alan Aikin",3 ,3,30);
         allShips.put(s1.getShipName(), s1);
         reserveFleet.put(s1.getShipName(), s1);
         
         Ship s2 = new Frigate ("Sophie", "Ben Baggins", 8, 16, true);
         allShips.put(s2.getShipName(), s2);
         reserveFleet.put(s2.getShipName(), s2);
         
         Ship s3 = new ManOWar("Endeavour","Col Cannon" ,4 ,2,20 );
         allShips.put(s3.getShipName(), s3);
         reserveFleet.put(s3.getShipName(), s3);
         
         Sloop s4 = new Sloop("Arrow","Dan Dare",true);
         s4.setSloopCommission(150);
         allShips.put(s4.getShipName(), s4);
         reserveFleet.put(s4.getShipName(), s4);
         
         Ship s5 = new ManOWar("Belerophon", "Ed Evans", 8 ,3 ,50);
         allShips.put(s5.getShipName(), s5);
         reserveFleet.put(s5.getShipName(), s5);
         
         Ship s6 = new Frigate ("Surprise" , "Fred Fox",6 ,10 ,false);
         allShips.put(s6.getShipName(), s6);
         reserveFleet.put(s6.getShipName(), s6);
         
         Ship s7 = new Frigate("Jupiter", "Gil Gamage",7 ,20,false);
         allShips.put(s7.getShipName(), s7);
         reserveFleet.put(s7.getShipName(), s7);
         
         Sloop s8 = new Sloop("Paris", "Hal Henry",true);
         s8.setSloopCommission(200);
         allShips.put(s8.getShipName(), s8);
         reserveFleet.put(s8.getShipName(), s8);
         
         Sloop s9 = new Sloop("Beast","Ian idle",false);
         s9.setSloopCommission(400);
         allShips.put(s9.getShipName(), s9);
         reserveFleet.put(s9.getShipName(), s9);
         
         Sloop s10 = new Sloop ("Athena", "John Jones",true );
         s10.setSloopCommission(100);
         allShips.put(s10.getShipName(), s10);
         reserveFleet.put(s10.getShipName(), s10);      
     }
     
    private void setupEncounters()
    {
    Encounter e1 = new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300);
    encounters.put(e1.getEnNo(), e1);

    Encounter e2 = new Encounter(2, EncounterType.SKIRMISH, "Belle Isle", 3, 120);
    encounters.put(e2.getEnNo(), e2);

    Encounter e3 = new Encounter(3, EncounterType.BLOCKADE, "Brest", 3, 150);
    encounters.put(e3.getEnNo(), e3);

    Encounter e4 = new Encounter(4, EncounterType.BATTLE, "St Malo", 9, 200);
    encounters.put(e4.getEnNo(), e4);

    Encounter e5 = new Encounter(5, EncounterType.BLOCKADE, "Dieppe", 7, 90);
    encounters.put(e5.getEnNo(), e5);

    Encounter e6 = new Encounter(6, EncounterType.SKIRMISH, "Jersey", 8, 45);
    encounters.put(e6.getEnNo(), e6);

    Encounter e7 = new Encounter(7, EncounterType.BLOCKADE, "Nantes", 6, 130);
    encounters.put(e7.getEnNo(), e7);

    Encounter e8 = new Encounter(8, EncounterType.BATTLE, "Finisterre", 4, 100);
    encounters.put(e8.getEnNo(), e8);

    Encounter e9 = new Encounter(9, EncounterType.SKIRMISH, "Biscay", 5, 200);
    encounters.put(e9.getEnNo(), e9);

    Encounter e10 = new Encounter(10, EncounterType.BATTLE, "Cadiz", 1, 250);
    encounters.put(e10.getEnNo(), e10);
    }
        
    // Useful private methods to "get" objects from collections/maps

    //*******************************************************************************
    //*******************************************************************************
  
    /************************ Task 3 ************************************************/

    
    //******************************** Task 3.5 **********************************
    /** reads data about encounters from a text file and stores in collection of 
     * encounters.Data in the file is editable
     * @param fileName name of the file to be read
     */
    public void readEncounters(String filename)
    { 
      try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
      {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                EncounterType type = EncounterType.valueOf(parts[1].trim().toUpperCase());
                String location = parts[2].trim();
                int difficulty = Integer.parseInt(parts[3].trim());
                double prize = Double.parseDouble(parts[4].trim());
    
                Encounter encounter = new Encounter(id, type, location, difficulty, prize);
                encounters.put(id, encounter);
            } 
        }
        catch (IOException e) 
        {
            System.out.println("Error reading encounters: " + e.getMessage()); 
        }
    }
        
    
         
    
    
    // ***************   file write/read  *********************
    /** Writes whole game to the specified file
     * @param fname name of file storing requests
     */
    public void saveGame(String fname)
    {   // uses object serialisation 
      try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
                out.writeObject(this);
                System.out.println("Game saved successfully to " + fname);
            } catch (IOException e) {
                System.out.println("Error saving game: " + e.getMessage());
            }     
    }
    
    /** reads all information about the game from the specified file 
     * and returns 
     * @param fname name of file storing the game
     * @return the game (as an SeaBattles object)
     */
    public static SeaBattles loadGame(String fname)
    {   // uses object serialisation 
       try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) 
       {
                return (SeaBattles) in.readObject();
       } 
       catch (IOException | ClassNotFoundException e) 
       {
                System.out.println("Error loading game: " + e.getMessage());
                return null;
       }
    }
     
    
 
}



