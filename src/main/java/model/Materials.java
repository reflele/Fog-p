package model;

import model.materials.*;

import java.util.ArrayList;
import java.util.List;

public class Materials {

    private static ArrayList<WoodenPost> woodenPosts;
    private static ArrayList<Beam> beams;

    public static ArrayList<WoodenPost> getWoodenPosts() {
        return woodenPosts;
    }

    public static void initMaterials() {

        ArrayList<Screw> screwsBolts = new ArrayList();

        screwsBolts.add(new Screw("Skrue", "Metal", 11388, 2, 20, 3));
        screwsBolts.add(new Screw("Skrue", "Metal", 11389, 2.2, 20, 3));
        screwsBolts.add(new Screw("Skrue", "Metal", 11390, 2.2, 20, 3));
        screwsBolts.add(new Screw("Bolt", "Metal", 21234, 3, 50, 3));

        if (beams == null) {
            beams = new ArrayList<>();
            //fill out list from DB
            beams.add(new Beam(451, "beam 150", "beam", 150, 100));
            beams.add(new Beam(452, "beam 200", "beam", 200, 120));
            beams.add(new Beam(453, "beam 360", "beam", 360, 180));
            beams.add(new Beam(454, "beam 400", "beam", 400, 250));
            beams.add(new Beam(455, "beam 500", "beam", 500, 300));

            if (woodenPosts == null) {
                woodenPosts = new ArrayList<>();
                //fill out list from DB
                woodenPosts.add(new WoodenPost("Bjælke", "Lærke", 4720, "RAFTE I LÆRKETRÆ Ø7 CM", "Raften er med fas i begge ender, Stolperne er rundfræset", 61, 200, 7, 7, 0));
                woodenPosts.add(new WoodenPost("Bjælke", "Lærke", 4721, "RAFTE I LÆRKETRÆ Ø7 CM", "Raften er med fas i begge ender, Stolperne er rundfræset", 63, 220, 7, 7, 0));
                woodenPosts.add(new WoodenPost("Bjælke", "Gran", 5343, "SPÆRTRÆ - 47X200 MM", "Styrkesorteret træ som bærende element", 69, 420, 1, 2, 0));
            }

        }

        ArrayList<Glass> glass = new ArrayList();
        ArrayList<Panel> panels = new ArrayList();
        ArrayList<Misc> misc = new ArrayList();
        ArrayList<CustomCut> customCutPieces = new ArrayList();

    }
}