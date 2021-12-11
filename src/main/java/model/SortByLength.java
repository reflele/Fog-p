package model;
import business.entities.Material;
import java.util.Comparator;

class SortByLength implements Comparator<Material> {
    public int compare(Material one, Material two) {
        return one.getLength() - two.getLength();
    }
}