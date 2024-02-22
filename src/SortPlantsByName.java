import java.util.Comparator;

class SortPlantsByName implements Comparator<Plant> {
    @Override
    public int compare(Plant p1, Plant p2){
        return p1.getName().compareTo(p2.getName());
    }
}
