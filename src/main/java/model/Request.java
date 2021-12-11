package model;

public class Request {
    private int id; // just used to demo retrieval of autogen keys in UserMapper
    int requestId;
    int length;
    int width;
    String roofType;

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

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Request(int id, int length, int width, String roofType) {
        this.requestId = id;
        this.length = length;
        this.width = width;
        this.roofType = roofType;
    }
}
