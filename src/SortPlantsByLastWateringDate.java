import java.util.Comparator;

public class SortPlantsByLastWateringDate  implements Comparator<Plant> {
    @Override
    public int compare(Plant p1, Plant p2){
        return p1.getLastWateringDate().compareTo(p2.getLastWateringDate());
    }
}

