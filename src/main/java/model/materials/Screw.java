package model.materials;

import business.entities.Material;

public class Screw extends Material {
    public Screw(String category, String materialName, int material_id, double price, int length, int width) {
        super(category, material_id, price, length, width);
    }
}
