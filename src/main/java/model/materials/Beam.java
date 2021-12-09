package model.materials;

import business.entities.Material;

public class Beam extends Material {
    public Beam(int material_id, String shortDescription, String category, int length, double price) {
        super(material_id, category, length, price);
    }
}
