package model;

public class BomMaterial {

    int materialId;
    int count;

    public BomMaterial(int materialId, int count) {
        this.materialId = materialId;
        this.count = count;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


//    Tænker vi lave en klasse der hedder BOM
//    Den har attributterne materialId og count
//    Så ind i vores Calculate-klasse laver vi en ny ArrayList af typen BOM
//    Og hver af metoderne i beregningsmotoren skal tilføje materialId og amount ind i ArrayListen
