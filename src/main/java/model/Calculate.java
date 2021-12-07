package model;

public class Calculate {



    public static double posts(double carPortLength, double carPortWidth){

        double splitPosts = 350;
        double numberOfPosts;
        double minLength = 300;
        double minWidth = 300;

        numberOfPosts = 4;

        if (carPortWidth >= minWidth){
            double extraWidth = carPortWidth - minWidth;
            for (double i = 0; i < extraWidth; i = i + splitPosts) {
                numberOfPosts = numberOfPosts + 2;
            }
        }

        if (carPortLength >= minLength){
            double extraLength = carPortLength - minLength;
            for (double i = 0; i < extraLength; i = i + splitPosts) {
                numberOfPosts = numberOfPosts + 2;
            }
        }
        return numberOfPosts;
    }

    public static String postsType(int postCount){
        String type = "birketræ";

        return type;
        //make method der finder ud af type
    }
}
