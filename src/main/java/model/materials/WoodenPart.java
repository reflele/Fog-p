package model.materials;

import business.entities.Material;

public class WoodenPart extends Material {
    public WoodenPart(String category, String materialName, String productNumber, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        super(category, productNumber, description, shortDescription, price, length, height, width);
    }
}
