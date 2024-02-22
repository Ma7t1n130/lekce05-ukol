import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Plant {
    private String name;
    private String notes;
    private LocalDate plantedDate;
    private LocalDate lastWateringDate;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate plantedDate, LocalDate lastWateringDate, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.plantedDate = plantedDate;
        setFrequencyOfWatering(frequencyOfWatering);
        setLastWateringDate(lastWateringDate);
    }

    public Plant(String name, LocalDate plantedDate, int frequencyOfWatering) throws PlantException {
        this(name, "", plantedDate, LocalDate.now(), frequencyOfWatering);
    }

    public Plant(String name) throws PlantException {
        this(name, LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlantedDate() {
        return plantedDate;
    }

    public void setPlantedDate(LocalDate plantedDate) {
        this.plantedDate = plantedDate;
    }

    public LocalDate getLastWateringDate() {
        return lastWateringDate;
    }

    public void setLastWateringDate(LocalDate lastWateringDate) throws PlantException {
        if(lastWateringDate.isBefore(this.plantedDate)) {
            throw new PlantException("Datum poslední zálivky nemůže být před datem zasazení rostliny."+Utils.formatDate(lastWateringDate)+" "+Utils.formatDate(this.plantedDate));
        } else {
            this.lastWateringDate = lastWateringDate;
        }
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering>0) {
            this.frequencyOfWatering = frequencyOfWatering;
        } else {
            throw new PlantException("Nesprávná hodnota frekvence zálivky.");
        }
    }

    public String getWateringInfo(){
        DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern(Settings.getDateFormat());
        return "Květina: "
                + this.name
                +", "
                + this.notes
                + ", poslední zálivka: "
                + Utils.formatDate(lastWateringDate)
                + ", další zálivka: "
                + Utils.formatDate(this.lastWateringDate.plusDays(this.frequencyOfWatering))
                ;
    }
}
