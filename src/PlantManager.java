import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantManager {
    private final List<Plant> plants;
    private String listName; //pouziju jako fileName

    public PlantManager(String listName,List<Plant> plants) {
        this.plants = new ArrayList<>(plants);
        this.listName = listName;
    }

    public Plant getPlant(int index) throws PlantException {
        try {
            return plants.get(index);
        } catch (Exception e){
            throw new PlantException("Chyba při získávání prvku kolekce: " + e.getLocalizedMessage());
        }
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public String getListName() {
        return listName;
    }

    public void removePlant(int index) throws PlantException {
        try {
            plants.remove(index);
        } catch (Exception e){
            throw new PlantException("Chyba při mazání prvku kolekce: " + e.getLocalizedMessage());
        }
    }

    public String getWateringInfoReport(){
        String wateringInfoReport = "";
        for(Plant plant : plants) {
            wateringInfoReport = wateringInfoReport + plant.getWateringInfo() + Settings.getNewLine();
        }
        return wateringInfoReport;
    }

    public void addPlant(Plant plant){
        plants.add(plant);
    }

    public void saveToFile() throws PlantException{
        String fileName=Utils.getFileName(this.listName);
        String delimiter=Settings.getDelimiter();
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))){
            for(Plant plant:plants){
                writer.println(
                            plant.getName() + delimiter +
                            plant.getNotes() + delimiter +
                            plant.getFrequencyOfWatering() + delimiter +
                            plant.getLastWateringDate() + delimiter +
                            plant.getPlantedDate());
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu do souboru " + fileName + " " + e.getLocalizedMessage());
        }
    }

    public void loadFromFile() throws PlantException {
        String fileName=Utils.getFileName(this.listName);
        String delimiter=Settings.getDelimiter();
        int lineCounter=0;
        plants.clear();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while(scanner.hasNextLine()){
                lineCounter++;
                String line = scanner.nextLine();
                String[] parts = line.split(delimiter);
                if(parts.length!=5){
                    throw new PlantException("Nesprávný formát řádku "+lineCounter);
                }
                String name=parts[0];
                String notes=parts[1];
                int frequencyOfWatering=Integer.parseInt(parts[2]);
                LocalDate lastWateringDate=LocalDate.parse(parts[3]);
                LocalDate plantedDate=LocalDate.parse(parts[4]);
                plants.add(new Plant(name,notes,plantedDate,lastWateringDate,frequencyOfWatering));
            }
        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " + fileName + " nebyl nalezen " + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new PlantException("Chyba při čtení číselné hodnoty na řádku " + lineCounter + " " + e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            throw new PlantException("Chyba při čtení datumu na řádku " + lineCounter + " " + e.getLocalizedMessage());
        }
    }

    public void sortByName(){
        this.plants.sort(new SortPlantsByName());
    }

    public void sortByLastWateringDate(){
        this.plants.sort(new SortPlantsByLastWateringDate());
    }
}
