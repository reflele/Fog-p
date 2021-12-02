package model;

import model.materials.*;

import java.util.ArrayList;

public class Materials {
    public Materials() {

        ArrayList<Screw> screwsBolts = new ArrayList();

        screwsBolts.add(new Screw("Skrue","Metal","11388",2,20,3));
        screwsBolts.add(new Screw("Skrue","Metal","11389",2.2,20,3));
        screwsBolts.add(new Screw("Skrue","Metal","11390",2.2,20,3));
        screwsBolts.add(new Screw("Bolt","Metal","21234",3,50,3));

        ArrayList<WoodenPart> woodenParts = new ArrayList();

        woodenParts.add(new WoodenPart("Bjælke","Lærke","4720","RAFTE I LÆRKETRÆ Ø7 CM","Raften er med fas i begge ender, Stolperne er rundfræset",61,200,7,7,0));
        woodenParts.add(new WoodenPart("Bjælke","Lærke","4721","RAFTE I LÆRKETRÆ Ø7 CM","Raften er med fas i begge ender, Stolperne er rundfræset",63,220,7,7,0));
        woodenParts.add(new WoodenPart("Spærtræ","Gran","5343","SPÆRTRÆ - 47X200 MM","Styrkesorteret træ som bærende element",69,420,1,2,0));

        ArrayList<ConcreteMix> concreteMix = new ArrayList();
        ArrayList<Glass> glass = new ArrayList();
        ArrayList<Panel> panels = new ArrayList();
        ArrayList<Misc> misc = new ArrayList();
        ArrayList<CustomCut> customCutPieces = new ArrayList();

    }




}
