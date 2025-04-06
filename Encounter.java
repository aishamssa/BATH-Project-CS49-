package wars;

import java.io.Serializable;

/**
 * Write a description of class Encounter here.
 *
 * @author (Ohemaa)
 * @version (a version number or a date)
 */
public class Encounter implements Serializable
{
    // instance variables - replace the example below with your own
    private int    encounterNo; //
    private String location;
    private int    requiredSkill;
    private double prizeMoney;
    private EncounterType encounterType;

    /**
     * Constructor for objects of class Encounter
     */
    public Encounter(int enNo, EncounterType enType, String loc,int reqSkill, double prizeMoney)
    {
       this.encounterNo = enNo;
       this.encounterType = enType;       
       this. location = loc;
       this.requiredSkill = reqSkill;
       this.prizeMoney = prizeMoney;
    }

    public int getEnNo()
    {
        return encounterNo;
    }
    
    public int getSkillLevel()
    {
        // put your code here
        return requiredSkill;
    }
    
    public EncounterType getEncounterType()
    {
        return encounterType;
    }
    public String getLocation()
    {   
        return location;
    }
    public double getPrizeMoney()
    {
        return prizeMoney;
    }
    public boolean inShipEligible(Ship ship)
    {
        return ship.getShipBattleSkill() >= requiredSkill;
    }
    
    
    public String toString()
    {
        return "Encounter # " + encounterNo +
               "\n Type: " + encounterType +
               "\n Location: " + location +
               "\n Required Skill: " + requiredSkill +
               "\n Prize: £" + prizeMoney;
    }
}
