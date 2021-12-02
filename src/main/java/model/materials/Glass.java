package model.materials;

import model.Material;

public class Glass extends Material {
    public Glass(String category, String materialName, String productNumber, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        super(category, materialName, productNumber, description, shortDescription, price, length, height, width, weight);
    }
}
