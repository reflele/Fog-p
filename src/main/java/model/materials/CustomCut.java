package model.materials;

import business.entities.Material;

public class CustomCut extends Material {
    public CustomCut(String category, String materialName, int material_id, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        super(category, material_id, description, price, length, height, width);
    }
}
