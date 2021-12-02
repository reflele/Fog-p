package model.materials;

import model.Material;
import model.Materials;

public class Panel extends Material {
    public Panel(String category, String materialName, String productNumber, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        super(category, materialName, productNumber, description, shortDescription, price, length, height, width, weight);
    }
}
