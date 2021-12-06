package business.entities;

public class Material {
    private String material_id;
    private String shortDescription;
    private String description;
    private String category; //e.g. træstolpe, glastag, skrue, søm etc
    private int length;
    private int height;
    private int width;
    private double price;
    private String unit;

    public Material(String material_id, double price, String unit) {
        this.material_id = material_id;
        this.price = price;
        this.unit = unit;
    }

    public Material(String category, String material_id, double price, int length, int width) {
        this.category = category;
        this.material_id = material_id;
        this.price = price;
        this.length = length;
        this.width = width;
    }

    public Material(String category, String material_id, String description, String shortDescription, double price, int length, int height, int width) {
        this.category = category;
        this.material_id = material_id;
        this.description = description;
        this.shortDescription = shortDescription;
        this.price = price;
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public Material(String category, String material_id, double price) {
        this.category = category;
        this.material_id = material_id;
        this.price = price;
    }



    public String getCategory() {
        return category;
    }



    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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
