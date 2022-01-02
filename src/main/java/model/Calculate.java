package model;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;

import javax.xml.crypto.Data;
import java.util.*;

import static web.FrontController.database;

public class Calculate {

        MaterialFacade materialFacade;

    public ArrayList<BomMaterial> getBomMaterials() {
        return bomMaterials;
    }


    public Calculate(Database database) {
        materialFacade = new MaterialFacade(database);
//        MaterialFacade materialFacade = new MaterialFacade(database);
    }
    public Calculate(){
        materialFacade = new MaterialFacade(database);
    }


    static double minLengthWidth = 450; //if carport length < minLength. No extra posts are added.
    static double postsDistance = 210; //if side is longer than minLength, extra posts will be added to the construction for every postsDistance cm.
    static double raftersDistance = 52;

    public double getPostsDistance() {
        return postsDistance;
    }

    public double getRaftersDistance() {
        return raftersDistance;
    }

    public List<Material> CategoryList(String category) throws UserException {

        List<Material> list = new ArrayList<>();

        list = materialFacade.getMaterialByCategory(category);

        return list;
    }

//    public static int getRaftersCount(double carportLength, double carportWidth) throws UserException{
//}

    public double rafters(double carPortLength, double carportWidth) throws UserException {

        double shortSide = 0;
        double longSide = 0;
        double raftersLength = 0;
        double raftersCount = 0;
        int rafterId = 0;

        if (carPortLength > carportWidth) {
            longSide = carPortLength;
            shortSide = carportWidth;
        } else {
            longSide = carportWidth;
            shortSide = carPortLength;
        }

        List<Material> raftersList;
        raftersList = CategoryList("spær"); //gets rafters from db


        raftersList.sort(new SortByLength()); //sorted raftersList by length


        for (int i = 0; i < raftersList.size(); i++) {
            if (raftersList.get(i).getLength() >= shortSide) {
                raftersLength = raftersList.get(i).getLength();
                rafterId = raftersList.get(i).getMaterial_id();
                break;
            }

        }

        raftersCount = (longSide / raftersDistance) + 1;
        //vi skal enten +1 eller -1 baseret på om der skal være rafters på ydersiderne

        //raftersCount skal rundes op og castes som int

        BomMaterial bomMaterial = new BomMaterial(rafterId, (int) raftersCount);
        BomMaterial bomMaterial2 = new BomMaterial(997, (int) raftersCount);
        BomMaterial bomMaterial3 = new BomMaterial(998, (int) raftersCount);

        bomMaterials.add(bomMaterial);
        bomMaterials.add(bomMaterial2);
        bomMaterials.add(bomMaterial3);


        return raftersCount;
    }


    public double beams(double carPortLength, double carPortWidth) throws UserException {

        double beamLength = 0;
        int beamRequiredCount = 0;
        int beamId = 0;

        List<Material> beamsList;
        beamsList = CategoryList("rem"); //gets beams from db


        beamsList.sort(new SortByLength()); //sorted beamsList by length

        double longSide = 0;

        if (carPortLength > carPortWidth) {
            longSide = carPortLength;
        } else {
            longSide = carPortWidth;
        }

        

        for (int i = 0; i < beamsList.size(); i++) {
            if (beamsList.get(i).getLength() > longSide) {
                beamLength = beamsList.get(i).getLength();
                beamId = beamsList.get(i).getMaterial_id();
                beamRequiredCount = 2;
                break;
            }


        }

        if (beamRequiredCount == 0) {

            for (int i = 0; i < beamsList.size(); i++) {
                if (beamsList.get(i).getLength() > longSide / 2) {
                    beamLength = beamsList.get(i).getLength();
                    beamId = beamsList.get(i).getMaterial_id();
                    beamRequiredCount = 4;
                    break;

                }

            }

        }
        BomMaterial bomMaterial = new BomMaterial(beamId, beamRequiredCount);
        bomMaterials.add(bomMaterial);


        return beamLength;


        //lav liste med beams
        //sorter efter længde (mindst til størst)
        //loop i gennem liste for at finde den mindste beam som er > longSide
        //tilføj til list som indeholder material_id og antal
    }

    public double posts(double carPortLength, double carPortWidth) {

        //getDimensionsFromReqId(reqId);

        int postId = 777; //hardcoded del. en post med denne postId skal sættes i db.
        double postsCount = 0;

        double longSide;
        double shortSide;

        if (carPortLength > carPortWidth) {
            longSide = carPortLength;
        } else {
            longSide = carPortWidth;
        }

        for (int i = 0; i < longSide-raftersDistance; i = (int) (i+raftersDistance*3)) {
            postsCount = postsCount +2;
        }


//        carPortLength = carPortLength*0.8;

//        if (carPortWidth >= minLengthWidth) {
//            double extraWidth = carPortWidth - minLengthWidth;
//            for (double i = 0; i < extraWidth; i = i + postsDistance) {
//                postsCount = postsCount + 2;
//            }
//        }
//
//        if (carPortLength >= minLengthWidth) {
//            double extraLength = carPortLength - minLengthWidth;
//            for (double i = 0; i < extraLength; i = i + postsDistance) {
//                postsCount = postsCount + 2;
//            }
//        }
//        //add to a list

        BomMaterial bomMaterial = new BomMaterial(postId, (int) postsCount);

        bomMaterials.add(bomMaterial);


        return postsCount;
    }


    public double roofing(double carPortLength, double carPortWidth) {

        int roofSheetId = 666; //hardcoded del. en roof med denne roofSheetId skal sættes i db.
        double roofSheetlength = 200;
        double roofSheetWidth = 220;
        double roofSheetSurface = roofSheetlength * roofSheetWidth;
        double carportRoofSurface = carPortLength * carPortWidth;
        double amountOfSheets = 0;

//        while (carportRoofSurface >= roofSheetSurface) {
//            roofSheetSurface++;
//        }

        amountOfSheets = (carPortLength * carPortWidth) / roofSheetSurface;





        BomMaterial roofScrews = new BomMaterial(996, 1); //dette er for tagskruerne.
        //antal tagskruer er min 1 pk. per carportrequest. nedenstående beregning runder ned til 0 hvis carporten er lille.
        if ((int) Math.ceil(amountOfSheets) <= 2){
            roofScrews = new BomMaterial(996, 1);
        } else {
            roofScrews = new BomMaterial(996, (int) Math.ceil(amountOfSheets)/3);
        }

        BomMaterial roofSheets = new BomMaterial(roofSheetId, (int) Math.ceil(amountOfSheets));//runder altid op til nærmeste hele tal


        bomMaterials.add(roofSheets);
        bomMaterials.add(roofScrews);
        return Math.ceil(amountOfSheets);

    }


    public void fittings(double carPortLength, double carPortWidth) {

        Double areal = carPortLength * carPortWidth;
        int fittingsCount;


        if (areal <= 100000){
            fittingsCount = 12;

        } else if (areal <= 200000){
            fittingsCount = 16;
        } else if (areal <= 300000){
            fittingsCount = 20;
        }else if (areal <= 400000){
            fittingsCount = 24;
        }else {
            fittingsCount = 28;
        }

        int fittingScrewsCount = fittingsCount/4;


        BomMaterial bomMaterial = new BomMaterial(888, fittingsCount);
        BomMaterial bomMaterial2 = new BomMaterial(999, fittingScrewsCount);

        bomMaterials.add(bomMaterial);
        bomMaterials.add(bomMaterial2);

    }


    ArrayList<BomMaterial> bomMaterials = new ArrayList<>();

    public double calcPrice(ArrayList<BomMaterial> bomMaterials) throws UserException {


        double materialsPrice;
        double purchasePrice = 0;

        for (int i = 0; i < bomMaterials.size(); i++) {

            materialsPrice = materialFacade.getPriceById(bomMaterials.get(i).getMaterialId());
            materialsPrice = materialsPrice * bomMaterials.get(i).getCount();

            purchasePrice = purchasePrice + materialsPrice;


        }
        return purchasePrice;
    }


}



