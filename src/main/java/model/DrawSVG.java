package model;

import business.entities.SVG;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;
import business.services.RequestFacade;
import model.Calculate;

import java.util.List;

public class DrawSVG {

    RequestFacade requestFacade;

    public DrawSVG(Database database) {
        requestFacade = new RequestFacade(database);
    }


    public SVG draw(int reqId) throws UserException {


        List<Request> requestList = requestFacade.getAllRequests();
        Calculate calculate;

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

        double raftersCount = 10;
        int raftersDistance = (int) (longSide/(raftersCount-1));


        int rafterX = 0;
        int rafterY = 0;

        int postX = raftersDistance;

        int postX1 = 0;
        int postY1 = 0;

        int beamX = 0;
        int upperBeamPostY = (int) (shortSide * 0.12);
        int lowerBeamPostY = (int) (shortSide * 0.88);

        double beamWidth = 4.5;
        double postWidth = 6;
        double postHeight = 6;
        double postCount = (longSide / raftersDistance);

        //viewbox
        SVG svg = new SVG(0, 0, "0 0 800 600", 100, 50);

        //carport omrids
        svg.addRect(0, 0, shortSide, longSide);


        //beams
        svg.addRect(beamX, upperBeamPostY, beamWidth, longSide);
        svg.addRect(beamX, lowerBeamPostY, beamWidth, longSide);

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

        postX = raftersDistance;
        //posts row 2
        for (int i = 0; i < postCount; i++) {
            svg.addRect(postX, lowerBeamPostY, postHeight, postWidth);
            postX = postX + (raftersDistance * 3);
            if (postX > longSide){
                break;
            }
        }
        return svg;

    }


}
