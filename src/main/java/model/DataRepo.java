package model;

import java.util.ArrayList;
import java.util.List;

public class DataRepo {

    private static List<ChoosableDimensions> lengths;
    private static List<ChoosableDimensions> heights;
    private static List<ChoosableDimensions> widths;
    private static List<RoofType> roofTypes;
//  private static List<TreeType> treeTypes;
//  private static List<Coating> coatings;
//  private static List<Sides> sides;
//  private static List<Lacquering> lacquerings;


    public static List<RoofType> getRoofTypes() {
        return roofTypes;
    }

    public static void initDataRepo() {

        if(roofTypes == null){
            roofTypes = new ArrayList<>();
            roofTypes.add(new RoofType("lige tag",1));
            roofTypes.add(new RoofType("skrå tag",2));
            //fill out list from DB
        }




        if(lengths == null){
            lengths = new ArrayList<>();
            //fill out list from DB
            lengths.add(new ChoosableDimensions(200));
            lengths.add(new ChoosableDimensions(240));
            lengths.add(new ChoosableDimensions(280));
            lengths.add(new ChoosableDimensions(320));
            lengths.add(new ChoosableDimensions(360));
            lengths.add(new ChoosableDimensions(400));
        }



        if(heights == null){
            heights = new ArrayList<>();
            //fill out list from DB
            heights.add(new ChoosableDimensions(200));
            heights.add(new ChoosableDimensions(210));
            heights.add(new ChoosableDimensions(220));
            heights.add(new ChoosableDimensions(230));
        }


        if(widths == null){
            widths = new ArrayList<>();
            //fill out list from DB
            widths.add(new ChoosableDimensions(200));
            widths.add(new ChoosableDimensions(240));
            widths.add(new ChoosableDimensions(280));
        }


            }

    public static void setLengths(List<ChoosableDimensions> lengths) {
        DataRepo.lengths = lengths;
    }

    public static List<ChoosableDimensions> getLengths() {
        return lengths;
    }

    public static List<ChoosableDimensions> getHeights() {
        return heights;
    }

    public static List<ChoosableDimensions> getWidths() {
        return widths;
    }
}
