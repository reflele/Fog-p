package model;

public abstract class Material {
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private String category; //e.g. træstolpe, glastag, skrue, søm etc
    private String materialName;
    private String productNumber;
    private String Description;
    private String shortDescription;
    private double price;
    private int length;
    private int height;
    private int width;
    private int weight;

    public Material(String category, String materialName, String productNumber, String description, String shortDescription, double price, int length, int height, int width, int weight) {
        this.category = category;
        this.materialName = materialName;
        this.productNumber = productNumber;
        Description = description;
        this.shortDescription = shortDescription;
        this.price = price;
        this.length = length;
        this.height = height;
        this.width = width;
        this.weight = weight;
    }

    public Material(String category, String materialName, String productNumber, double price, int weight) {
        this.category = category;
        this.materialName = materialName;
        this.productNumber = productNumber;
        this.price = price;
        this.weight = weight;
    }

    public Material(String category, String materialName, String productNumber, double price, int length, int width) {
        this.category = category;
        this.materialName = materialName;
        this.productNumber = productNumber;
        this.price = price;
        this.length = length;
        this.width = width;
    }

    public Material(String category, String materialName, String productNumber, String description, String shortDescription, double price, int length, int height, int width) {
        this.category = category;
        this.materialName = materialName;
        this.productNumber = productNumber;
        Description = description;
        this.shortDescription = shortDescription;
        this.price = price;
        this.length = length;
        this.height = height;
        this.width = width;
    }
}
