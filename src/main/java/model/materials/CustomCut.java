package model.materials;

import business.entities.Material;

public class CustomCut extends Material {
    public CustomCut(String category, String materialName, String productNumber, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        super(category, productNumber, description, shortDescription, price, length, height, width);
    }
}
