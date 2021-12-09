package model;

public class Calculate {

    static double minLengthWidth = 450; //if carport length < minLength. No extra posts are added.
    static double postsDistance = 210; //if side is longer than minLength, extra posts will be added to the construction for every postsDistance cm.

    public static double rafters(){
    int x = 0;
        return 0;
    }

    public static double beams(double carPortLength, double carPortWidth, double postsCount) {

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


       beamDistanceSide1 = shortSide1/beamsShortSideCount;
       beamDistanceSide3 = longSide3/beamsLongSideCount;


       //beregn afstand mellem stolper på hver side








        return 3;
    }

    public static double posts(double carPortLength, double carPortWidth){

        double postsCount = 4;

        if (carPortWidth >= minLengthWidth){
            double extraWidth = carPortWidth - minLengthWidth;
            for (double i = 0; i < extraWidth; i = i + postsDistance) {
                postsCount = postsCount + 2;
            }
        }

        if (carPortLength >= minLengthWidth){
            double extraLength = carPortLength - minLengthWidth;
            for (double i = 0; i < extraLength; i = i + postsDistance) {
                postsCount = postsCount + 2;
            }
        }
        return postsCount;
    }

    public static String postsType(int postCount){
        String type = "birketræ";

        return type;
        //make method der finder ud af type
    }
}
