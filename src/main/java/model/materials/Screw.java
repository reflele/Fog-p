package model.materials;

import business.entities.Material;

public class Screw extends Material {
    public Screw(String category, String materialName, String productNumber, double price, int length, int width) {
        super(category, productNumber, price, length, width);
    }
}
