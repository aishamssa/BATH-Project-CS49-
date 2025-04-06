
package wars;

import java.io.*;
import java.util.*;

/**
 * Partial implementation of the BATHS interface focusing on persistence.
 * Includes save/load functionality and reading encounters from a file.
 */
public class SeaBattles implements BATHS {
    private String admiral;
    private double warChest;

    private Map<String, Ship> allShips = new HashMap<>();
    private Map<String, Ship> squadron = new HashMap<>();
    private Map<String, Ship> reserveFleet = new HashMap<>();
    private Map<String, Ship> resting = new HashMap<>();
    private Map<String, Ship> sunk = new HashMap<>();

    private Map<Integer, Encounter> encounters = new HashMap<>();

    public SeaBattles(String admiralName) {
        this.admiral = admiralName;
        this.warChest = 1000;
        setupShips();
        setupEncounters();
    }

    public SeaBattles(String admiralName, String encounterFile) {
        this.admiral = admiralName;
        this.warChest = 1000;
        setupShips();
        readEncounters(encounterFile);
    }

    @Override
    public void saveGame(String fname) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fname))) {
            out.writeObject(this);
            System.out.println("Game successfully saved to " + fname);
        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    @Override
    public SeaBattles loadGame(String fname) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))) {
            SeaBattles loadedGame = (SeaBattles) in.readObject();
            System.out.println("Game successfully loaded from " + fname);
            return loadedGame;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load game: " + e.getMessage());
            return null;
        }
    }

    public void readEncounters(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                EncounterType type = EncounterType.valueOf(parts[1].trim().toUpperCase());
                String location = parts[2].trim();
                int difficulty = Integer.parseInt(parts[3].trim());
                double prize = Double.parseDouble(parts[4].trim());

                Encounter encounter = new Encounter(id, type, location, difficulty, prize);
                encounters.put(id, encounter);
            }
        } catch (IOException e) {
            System.out.println("Error reading encounters: " + e.getMessage());
        }
    }

    private void setupShips() {
        // Dummy implementation for testing
        Ship s1 = new Ship("Victory", "Admiral Nelson", 8, 500);
        Ship s2 = new Ship("Sophie", "Ben Baggins", 6, 160);
        allShips.put(s1.getName(), s1);
        reserveFleet.put(s1.getName(), s1);
        allShips.put(s2.getName(), s2);
        reserveFleet.put(s2.getName(), s2);
    }

    private void setupEncounters() {
        Encounter e1 = new Encounter(1, EncounterType.BATTLE, "Trafalgar", 3, 300);
        encounters.put(e1.getId(), e1);
    }

    // Stub methods required by interface but not implemented here
    @Override public String toString() { return ""; }
    @Override public boolean isDefeated() { return false; }
    @Override public double getWarChest() { return warChest; }
    @Override public String getReserveFleet() { return ""; }
    @Override public String getSquadron() { return ""; }
    @Override public String getSunkShips() { return ""; }
    @Override public String getAllShips() { return ""; }
    @Override public String getShipDetails(String nme) { return ""; }
    @Override public String commissionShip(String nme) { return ""; }
    @Override public boolean isInSquadron(String nme) { return false; }
    @Override public boolean decommissionShip(String nme) { return false; }
    @Override public void restoreShip(String nme) {}
    @Override public boolean isEncounter(int num) { return encounters.containsKey(num); }
    @Override public String fightEncounter(int encNo) { return ""; }
    @Override public String getEncounter(int num) { 
        Encounter e = encounters.get(num);
        return (e != null) ? e.toString() : "No such encounter";
    }
    @Override public String getAllEncounters() {
        StringBuilder sb = new StringBuilder();
        for (Encounter e : encounters.values()) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }
}
