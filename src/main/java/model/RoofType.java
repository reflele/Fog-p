package model;

public class RoofType {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoofType(String name, int id) {
        this.name = name;
        this.id = id;

    }
}
