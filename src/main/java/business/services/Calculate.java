package business.services;

import business.entities.Material;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;

import java.util.List;

public class Calculate {


    private static MaterialFacade materialFacade;


    public Calculate(Database database) {
        materialFacade = new MaterialFacade(database);
    }

    public static double posts(double carPortLength, double carPortWidth) {
        String description = "Stolper nedgraves 90 cm i jorden. ";

        double splitPosts = 350;
        double numberOfPosts;
        double minLength = 300;
        double minWidth = 300;

        numberOfPosts = 4;

        if (carPortWidth >= minWidth) {
            double extraWidth = carPortWidth - minWidth;
            for (double i = 0; i < extraWidth; i = i + splitPosts) {
                numberOfPosts = numberOfPosts + 2;
            }
        }

        if (carPortLength >= minLength) {
            double extraLength = carPortLength - minLength;
            for (double i = 0; i < extraLength; i = i + splitPosts) {
                numberOfPosts = numberOfPosts + 2;
            }
        }
        return numberOfPosts;
    }

    public static String postsType(int postCount) {
        String type = "birketræ";

        return type;
        //make method der finder ud af type
    }
//
//    public static double beams(double carPortLength, double carPortWidth) throws UserException {
//        String description = "Remme i siderne til at sadles ned i stolperne ";
//        List<Material> materialList = materialFacade.getMaterialByDescription(description);
//
//        double numberOfBeams;
//        numberOfBeams = 4;
//
//
//    }
//    return eoele
}
