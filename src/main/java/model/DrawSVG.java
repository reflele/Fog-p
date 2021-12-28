package model;

import business.entities.SVG;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;
import business.services.RequestFacade;
import model.Calculate;

import java.util.List;

import static model.Calculate.raftersDistance;

public class DrawSVG {

    RequestFacade requestFacade;

    public DrawSVG(Database database) {
        requestFacade = new RequestFacade(database);
    }


    public SVG draw(int reqId) throws UserException {


        List<Request> requestList = requestFacade.getAllRequests();
        Calculate calculate = new Calculate();


        int shortSide;
        int longSide;
        int carportLength;
        int carportWidth;
        int a = 0;
        while (true) {
            if (requestList.get(a).getId() == reqId) {
                carportLength = requestList.get(a).getLength();
                carportWidth = requestList.get(a).getWidth();
                break;
            }
            a++;
        }

        if (carportLength > carportWidth) {
            longSide = carportLength;
            shortSide = carportWidth;
        } else {
            longSide = carportWidth;
            shortSide = carportLength;
        }


        double rafterWidth = 4.5;


        int raft = (int) raftersDistance; //this number is the approximate distance between rafters. The actual number is "int raftersDistance"
        double raftersCount =  (longSide / raft) + 1;
        int raftersDistance = (int) (longSide/(raftersCount-1));


        int rafterX = 0;
        int rafterY = 0;

        int postX = raftersDistance;

        int postX1 = 0;
        int postY1 = 0;

        int beamX = 0;
        int upperBeamPostY = (int) (shortSide * 0.12);
        int lowerBeamPostY = (int) (shortSide * 0.88);

        int beamSplitX = (longSide/2);
        double beamWidth = 4.5;
        double postWidth = 6;
        double postHeight = 6;
        double postCount = calculate.posts(carportLength,carportWidth);
//        int postEndX = (int) (longSide - postWidth);

        //viewbox
        int viewBoxL = longSide;
        int viewBoxS = shortSide;

        String l = String.valueOf(viewBoxL + 250);
        String s = String.valueOf(viewBoxS + 15);
        String viewBoxSize = "0 0 " + l + " " + s;

        System.out.println(viewBoxSize);
        SVG svg = new SVG(0, 0, viewBoxSize, 100, 100);

//        SVG svg = new SVG(0, 0, "0 0 "+longSide+100+" "+shortSide+100, 100, 100);

        //carport omrids
        svg.addRect(0, 0, shortSide, longSide);


        //beams
        //2 upper
        svg.addRect(beamX, upperBeamPostY, beamWidth, longSide);
        svg.addLine(beamSplitX, (int) (upperBeamPostY),beamSplitX, (int) (upperBeamPostY+ beamWidth));

        //2 lower
        svg.addRect(beamX, lowerBeamPostY, beamWidth, longSide);
        svg.addLine(beamSplitX, (int) (lowerBeamPostY),beamSplitX, (int) (lowerBeamPostY+ beamWidth));


        //rafter
        for (int i = 0; i < raftersCount; i++) {
            svg.addRect(rafterX, rafterY, shortSide, rafterWidth);
            rafterX = rafterX + raftersDistance;
        }


        //posts row 1
        for (int i = 0; i < postCount; i++) {
            svg.addRect(postX, upperBeamPostY, postHeight, postWidth);
            postX = postX + (raftersDistance * 3);
            if (postX > longSide){
                break;
            }
        }
//        svg.addRect(postEndX, upperBeamPostY, postHeight, postWidth);

        postX = raftersDistance;
        //posts row 2
        for (int i = 0; i < postCount; i++) {
            svg.addRect(postX, lowerBeamPostY, postHeight, postWidth);
            postX = postX + (raftersDistance * 3);
            if (postX > longSide){
                break;
            }
        }
//        svg.addRect(postEndX, lowerBeamPostY, postHeight, postWidth);
        //y-akse
        svg.addLine(0, 0, 0, shortSide);
       // x-akse
        svg.addLine(0, carportLength, carportWidth, carportLength);


        return svg;

    }


}
