package model.materials;

import model.Material;

public class CustomCut extends Material {
    public CustomCut(String category, String materialName, String productNumber, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        super(category, materialName, productNumber, description, shortDescription, price, length, height, width, weight);
    }
}
