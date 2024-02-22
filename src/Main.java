import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws PlantException
    {
        List<Plant> kytkyListTmp =  new ArrayList<>();

        kytkyListTmp.add(new Plant("Letnička červená"));
        kytkyListTmp.add(new Plant("Letnička modrá"));
        kytkyListTmp.add(new Plant("Letnička zelená"));

        PlantManager letnicky = new PlantManager("letnicky",kytkyListTmp);
        String report = letnicky.getWateringInfoReport();
        System.out.println("Letničky:");
        System.out.println(report);
        letnicky.saveToFile();

        kytkyListTmp.clear();
        PlantManager trvalky = new PlantManager("trvalky",kytkyListTmp);
        trvalky.loadFromFile();
        System.out.println("Trvalky:");
        System.out.println(trvalky.getWateringInfoReport());

        kytkyListTmp.clear();
        PlantManager kvetiny = new PlantManager("kvetiny",kytkyListTmp);
        kvetiny.loadFromFile();
        System.out.println("Květiny:");
        System.out.println(kvetiny.getWateringInfoReport());

        PlantManager kvetiny2 = new PlantManager("kvetiny2",kvetiny.getPlants());
        kvetiny2.getPlant(1).setNotes("pozor nepřelejt");
        kvetiny2.addPlant(new Plant("Kaktus suchý"));
        kvetiny2.addPlant(new Plant("Kaktus ultrasuchý"));
        kvetiny2.removePlant(2);
        kvetiny2.saveToFile();
        System.out.println("Květiny2:");
        System.out.println(kvetiny2.getWateringInfoReport());

        kvetiny2.sortByName();
        System.out.println("Květiny2 podle názvu:");
        System.out.println(kvetiny2.getWateringInfoReport());

        kvetiny2.sortByLastWateringDate();
        System.out.println("Květiny2 podle data zálivky:");
        System.out.println(kvetiny2.getWateringInfoReport());

        kytkyListTmp.clear();
        System.out.println("Květiny špatné datum:");
        try {
            PlantManager kvetiny3 = new PlantManager("kvetiny-spatne-datum", kytkyListTmp);
            kvetiny3.loadFromFile();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage()+ "\nKončím zpracování.\n");
        }

        kytkyListTmp.clear();
        System.out.println("Květiny špatná frekvence:");
        try {
            PlantManager kvetiny4 = new PlantManager("kvetiny-spatne-frekvence", kytkyListTmp);
            kvetiny4.loadFromFile();
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage()+ "\nKončím zpracování.\n");
        }

    }
}