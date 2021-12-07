package business.entities;

public class CarportCalc {
    int sides = 3;
    Double height = Double.valueOf(210);
    String treeType = "baseWood";


    public static  Double calcCarport (Double length, Double width, Double height, String treeType, int sides ){
        return




    }
    public static String findKategori(Double bmi){
        String kategori;
        if (bmi < 18.5) {
            kategori = "Undervægtig";

        } else if (bmi > 30) {
            kategori= "meget overvægtig";

        }else if (bmi<25){
            kategori = "normalvægtig";
        }else{
            kategori="Overvægtig";
        }
        return kategori;

    }
}

}
