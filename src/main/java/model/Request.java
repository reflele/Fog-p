package model;

import java.sql.Time;
import java.sql.Timestamp;

public class Request {
    private int id; // just used to demo retrieval of autogen keys in UserMapper
    int user_id;
    int length;
    int width;
    int status;
    String roofType;
    Timestamp dateTime;
    double price;
    String description;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Request(int id, int user_id, int length, int width, String roofType, Timestamp dateTime, int height, int status, double price, String description) {
        this.id = id;
        this.user_id = user_id;
        this.length = length;
        this.width = width;
        this.status = status;
        this.roofType = roofType;
        this.dateTime = dateTime;
        this.height = height;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    int height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Request(int id, int length, int width, String roofType, String description) {
        this.user_id = id;
        this.length = length;
        this.width = width;
        this.roofType = roofType;
        this.description = description;
    }
}
