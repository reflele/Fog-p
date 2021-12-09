package business.entities;

public class Material {
    private int material_id;
    private String description;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Material(int material_id, String description, String category, int length, int height, int width, double price, String unit) {
        this.material_id = material_id;
        this.description = description;
        this.category = category;
        this.length = length;
        this.height = height;
        this.width = width;
        this.price = price;
        this.unit = unit;
    }

    private String category; //e.g. træstolpe, glastag, skrue, søm etc
    private int length;
    private int height;
    private int width;
    private double price;
    private String unit;

    public Material(int material_id, String category, int length, double price) {
        this.material_id = material_id;
        this.category = category;
        this.length = length;
        this.price = price;
    }

    public Material(int material_id, double price, String unit) {
        this.material_id = material_id;
        this.price = price;
        this.unit = unit;
    }

    public Material(String category, int material_id, double price, int length, int width) {
        this.category = category;
        this.material_id = material_id;
        this.price = price;
        this.length = length;
        this.width = width;
    }

    public Material(String category, int material_id, String description, double price, int length, int height, int width) {
        this.category = category;
        this.material_id = material_id;
        this.description = description;
        this.price = price;
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public Material(String category, int material_id, double price, String unit) {
        this.category = category;
        this.material_id = material_id;
        this.price = price;
        this.unit = unit;
    }

    public Material(int material_id, double price) {
        this.material_id = material_id;
        this.price = price;
    }

    public Material(int material_id) {
        this.material_id = material_id;
    }


    public String getCategory() {
        return category;
    }



    public void setCategory(String category) {
        this.category = category;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
