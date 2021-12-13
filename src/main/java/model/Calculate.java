package model;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;

import java.util.*;

public class Calculate {

    MaterialFacade materialFacade;

    public Calculate(Database database) {
        materialFacade = new MaterialFacade(database);
    }


    static double minLengthWidth = 450; //if carport length < minLength. No extra posts are added.
    static double postsDistance = 210; //if side is longer than minLength, extra posts will be added to the construction for every postsDistance cm.
    static double raftersDistance = 55;




    public double rafters(double carPortLength, double carportWidth) throws UserException {

        double shortSide = 0;
        double longSide = 0;
        double raftersLength = 0;
        double raftersCount = 0;
        int rafterId = 0;

        if (carPortLength > carportWidth){
            longSide  = carPortLength;
            shortSide = carportWidth;
        } else {
            longSide  = carportWidth;
            shortSide = carPortLength;
        }

        List<Material> raftersList;
        raftersList = CategoryList("rafter"); //gets rafters from db


        raftersList.sort(new SortByLength()); //sorted raftersList by length


        for (int i = 0; i < raftersList.size(); i++) {
            if (raftersList.get(i).getLength() > shortSide) {
                raftersLength = raftersList.get(i).getLength();
                rafterId = raftersList.get(i).getMaterial_id();
                break;
            }

        }

        raftersCount = longSide/raftersDistance;

        System.out.println("rafterscount:");
        System.out.println(raftersCount);

        //tilføj rafters afstand fra hinanden.



        return raftersLength;
    }

    public List<Material> CategoryList(String category) throws UserException {

        List<Material> list = new ArrayList<>();

        list = materialFacade.getMaterialByCategory(category);

        return list;
    }

    public double beams(double carPortLength, double carPortWidth) throws UserException {


        double shortSide1;
        double shortSide2;
        double longSide3;
        double longSide4;

        double beamDistanceSide1 = 0;
        double beamDistanceSide2 = 0;
        double beamDistanceSide3 = 0;
        double beamDistanceSide4 = 0;

        if (carPortLength > carPortWidth) {
            shortSide1 = carPortWidth;
            shortSide2 = carPortWidth;
            longSide3 = carPortLength;
            longSide4 = carPortLength;
        } else {
            shortSide1 = carPortLength;
            shortSide2 = carPortLength;
            longSide3 = carPortWidth;
            longSide4 = carPortWidth;
        }


        double beamsShortSideCount = 0;
        double beamsLongSideCount = 0;

        if (shortSide1 > minLengthWidth + postsDistance) {
            beamsShortSideCount = 3;
        } else if (shortSide1 > minLengthWidth) {
            beamsShortSideCount = 2;
        } else if (shortSide1 < minLengthWidth) {
            beamsShortSideCount = 1;
        }

       if (longSide3 > minLengthWidth + postsDistance){
             beamsLongSideCount = 3;
        } else if (longSide3 > minLengthWidth){
             beamsLongSideCount = 2;
        } else if (longSide3 < minLengthWidth){
             beamsLongSideCount = 1;
        }


//       beamDistanceSide1 = shortSide1/beamsShortSideCount;
//       beamDistanceSide3 = longSide3/beamsLongSideCount;





        List<Material> beamsList;
        beamsList = CategoryList("beam"); //gets beams from db


        beamsList.sort(new SortByLength()); //sorted beamsList by length

        double longSide = 0;

        if (carPortLength>carPortWidth){
            longSide = carPortLength;
        } else {
            longSide = carPortWidth;
        }

        double beamLength = 0;
        int beamRequiredCount = 0;
        int beamId = 0;

        for (int i = 0; i < beamsList.size(); i++) {
            if (beamsList.get(i).getLength() > longSide) {
                beamLength = beamsList.get(i).getLength();
                beamId = beamsList.get(i).getMaterial_id();
                beamRequiredCount = 2;
                break;
            }

        }
        System.out.println(beamId);

            if (beamRequiredCount == 0){

                for (int i = 0; i < beamsList.size(); i++) {
                    if (beamsList.get(i).getLength() > longSide/2) {
                        beamLength = beamsList.get(i).getLength();
                        beamId = beamsList.get(i).getMaterial_id();
                        beamRequiredCount = 4;

                        break;

                    }

                    }

            }









        return beamLength;








            //lav liste med beams
            //sorter efter længde (mindst til størst)
            //loop i gennem liste for at finde den mindste beam som er > longSide
            //tilføj til list som indeholder material_id og antal



    }

    public static double posts(double carPortLength, double carPortWidth) {

        //getDimensionsFromReqId(reqid);

        double postsCount = 4;

        if (carPortWidth >= minLengthWidth) {
            double extraWidth = carPortWidth - minLengthWidth;
            for (double i = 0; i < extraWidth; i = i + postsDistance) {
                postsCount = postsCount + 2;
            }
        }

        if (carPortLength >= minLengthWidth) {
            double extraLength = carPortLength - minLengthWidth;
            for (double i = 0; i < extraLength; i = i + postsDistance) {
                postsCount = postsCount + 2;
            }
        }
        //add to a list
        return postsCount;
    }

    public static String postsType(int postCount) {
        String type = "birketræ";

        return type;
        //make method der finder ud af type
    }


}
