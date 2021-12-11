package model.materials;

import business.entities.Material;

public class Glass extends Material {
    public Glass(String category, String materialName, int material_id, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        super(category, material_id, description, price, length, height, width);
    }
}
