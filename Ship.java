/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wars;

import java.io.Serializable;

/**
 *
 * @author (Aisha&Ohemaa)
 */
public class Ship implements Serializable
{
    private String name;
    private String captain;
    private int battleSkill;
    private double commissionFee;
    private ShipState shipState;
    
    public Ship(String nam, String cap, int bs, double cf, ShipState ss ){
        name = nam;
        captain = cap;
        battleSkill = bs;
        commissionFee = cf;
        shipState = ss;  
    }
    public String getCaptain()
    {
        return captain;
    }
    public double getCommissionFee()
    {
        return commissionFee;
    }
    public String getShipName()
    {
        return name;
    }
    
    public int getShipBattleSkill()
    {
        return battleSkill;
    }
    public ShipState getShipState()
    {
        return shipState;
    }
    public void setCommissionFee(double fee)
    {
        commissionFee = fee;
    }
    
    public void commission()
    {
        shipState = ShipState.ACTIVE;
    }
    //Should the ship’s status or cost be updated dynamically when these methods are called?

    public void deCommission(){
        shipState = ShipState.RESERVE;
    }
    
    public boolean isAvailableForEncounter(){
       return shipState == ShipState.ACTIVE;
    }
    
    public void sink()
    {
     shipState = ShipState.SUNK ;  
    }
    
   @Override
    public String toString(){
    return "Name: "+ name + "\nCaptain: " + captain + 
            "\nBattle Skill:" + battleSkill + 
            "\n Commission Fee: " + commissionFee +
            "\nShip State: " + shipState ; 
    }

}

