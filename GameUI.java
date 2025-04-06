package wars;

import java.io.*;
import java.util.*;
/**
 * Task 2 - provide command line interface
 * 
 * @author A.A.Marczyk
 * @version 16/02/25
 */
public class GameUI
{
    private BATHS myBattles ;
    private Scanner myIn = new Scanner(System.in);

    public void doMain()
    {
        int choice;
        System.out.println("Enter admiral's name");
        String name = myIn.nextLine();
        myBattles = new SeaBattles(name); // create
        
        choice = 100;
        while (choice != 0 )
        {
            choice = getMenuItem();
            if (choice == 1)
            {
                System.out.println(myBattles.getReserveFleet());
            }
            else if (choice == 2)
            {
                System.out.println(myBattles.getSquadron());
            }
            else if (choice == 3)
            {
                System.out.println("Enter Ship name");
                myIn.nextLine();
                String ref = (myIn.nextLine());
                System.out.println(myBattles.getShipDetails(ref));
            } 
            else if (choice == 4)
            {
               System.out.println("Enter name of ship to commission:");
               myIn.nextLine(); // clear buffer
               String shipName = myIn.nextLine();
               System.out.println(myBattles.commissionShip(shipName));

            }
            else if (choice == 5)
            {
       	        System.out.println("Enter number of encounter:");
                int encNumber = myIn.nextInt();
                System.out.println(myBattles.fightEncounter(encNumber));
                  
            }
            else if (choice == 6)
            {
	        System.out.println("Enter name of ship to restore:");
                myIn.nextLine();
                String nameRestore = myIn.nextLine();
                myBattles.restoreShip(nameRestore);
                System.out.println("Ship: " + nameRestore +" has been restored!");   


            }
            else if (choice == 7)
            {
                System.out.println("Enter name of ship to decommission:");
                myIn.nextLine();
                String nameDecom = myIn.nextLine().trim();
                System.out.println(myBattles.decommissionShip(nameDecom));

            }
            else if (choice==8)
            {
                System.out.println(myBattles.toString());
            }
            else if (choice == 9) // Task 7 only
            {
                // Save game
                System.out.println("Enter filename to save:");
                myIn.nextLine();
                String saveFile = myIn.nextLine().trim();
                myBattles.saveGame(saveFile);
                System.out.println("Game saved.");
            }
            else if (choice == 10) // Task 7 only
            {
                // Load game
                System.out.println("Enter filename to load:");
                myIn.nextLine();
                String loadFile = myIn.nextLine().trim();
                SeaBattles loaded = SeaBattles.loadGame(loadFile);
                if (loaded != null) {
                    myBattles = loaded;
                    System.out.println("Game loaded successfully.");
                    System.out.println(myBattles.toString());
                } else {
                    System.out.println("Failed to load game.");          
            }  //Replaced default save/load with custom version -allows filename input
        }
    }
        System.out.println("Thank you for playing");
  }
    private int getMenuItem()
    {   int choice = 100;  
        System.out.println("Main Menu");
        System.out.println("0. Quit");
        System.out.println("1. List ships in the reserve fleet");
        System.out.println("2. List ships in admirals squadron"); 
        System.out.println("3. View a ship");
        System.out.println("4. Commission a ship into admiral's squadron");
        System.out.println("5. Fight an encounter");
        System.out.println("6. Restore a ship");
        System.out.println("7. Decommission a ship");
        System.out.println("8. View admiral's state");
        System.out.println("9. Save this game");
        System.out.println("10. Restore a game");
       
        
        while (choice < 0 || choice  > 10)
        {
            System.out.println("Enter the number of your choice");
            choice =  myIn.nextInt();
        }
        return choice;        
    } 
    
    public static void main(String[] args)
    {
        GameUI xxx = new GameUI();
        xxx.doMain();
    }
}