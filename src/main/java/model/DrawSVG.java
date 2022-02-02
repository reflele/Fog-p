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


    public SVG fullSVG(int reqId) throws UserException {


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

        double beamWidth = 4.5;
        double postWidth = 6;
        double postHeight = 6;
        double postCount = calculate.posts(carportLength,carportWidth);

        double sizeDiff = 1.2;

        if (shortSide<400){
            sizeDiff = 2;
        }

        //viewbox
        int viewBoxL = (int) (longSide);
        int viewBoxS = (int) (shortSide);
        int viewBoxL2 = (int) (longSide*sizeDiff);
        int viewBoxS2 = (int) (shortSide*sizeDiff);




        String l = String.valueOf(viewBoxL);
        String s = String.valueOf(viewBoxS);
        String l2 = String.valueOf(viewBoxL2);
        String s2 = String.valueOf(viewBoxS2);
        String viewBoxSize = "0 0 " + l + " " + s;

        String viewBoxSize2 = "0 0 " + l2 + " " + s2;


        SVG outerSVG = new SVG(0,0, viewBoxSize, 100, 100);
        SVG svg = new SVG(50, 0, viewBoxSize2, 100, 100);

//        SVG svg = new SVG(0, 0, "0 0 "+longSide+100+" "+shortSide+100, 100, 100);

        //carport omrids
        svg.addRect(0, 0, shortSide, longSide);


        //beams
        //2 upper
        svg.addRect(beamX, upperBeamPostY, beamWidth, longSide);

        //2 lower
        svg.addRect(beamX, lowerBeamPostY, beamWidth, longSide);



        //rafter
        for (int i = 0; i < raftersCount; i++) {
            svg.addRect(rafterX, rafterY, shortSide, rafterWidth);
            rafterX = rafterX + raftersDistance;
        }


        //posts row 1
        for (int i = 0; i < postCount; i++) {
            svg.addRect(postX, upperBeamPostY, postHeight, postWidth);
            svg.addRect(postX, lowerBeamPostY, postHeight, postWidth);
            postX = postX + (raftersDistance * 3);
            if (postX > longSide){
                break;
            }
        }
        //y-akse
        svg.addLine(0, 0, 0, shortSide);
       // x-akse
        svg.addLine(0, shortSide, longSide, shortSide);



        outerSVG.addSvg(svg);


        int yIndent = (int) (viewBoxS/sizeDiff);
        int xIndent = (int) (viewBoxL/sizeDiff);
        int outerSVGRaftersDistance = (int) (raftersDistance/sizeDiff);
        int outerSVGSingleBeamHeight = (int) (upperBeamPostY/sizeDiff);
        int outerSVGBeamHeight = (int) (lowerBeamPostY/sizeDiff);


//        int upperBeamPostY = (int) (shortSide * 0.12);
//        int lowerBeamPostY = (int) (shortSide * 0.88);


        //length/shortside measurement
        outerSVG.addLine(20, 0, 30, 0);
        outerSVG.addLine(20,yIndent,30,yIndent);
        outerSVG.addLine(25,0,25,yIndent);
        outerSVG.addText(15,(yIndent)/2,-90,shortSide);

        //width/longside measurement
        outerSVG.addLine(xIndent+50,yIndent+35,xIndent+50,yIndent+45);
        outerSVG.addLine(50,yIndent+35,50,yIndent+45);
        outerSVG.addLine(50,yIndent+40,xIndent+50,yIndent+40);
        outerSVG.addText((xIndent/2)+50,(yIndent)+55,0,longSide);

        //rafters measurement
        outerSVG.addLine(outerSVGRaftersDistance+50,yIndent+10,outerSVGRaftersDistance+50,yIndent+20);
        outerSVG.addLine(50,yIndent+10,50,yIndent+20);
        outerSVG.addLine(50,yIndent+15,outerSVGRaftersDistance+50,yIndent+15);
        outerSVG.addText((outerSVGRaftersDistance/2)+50,(yIndent)+30,0,raftersDistance);

        //beam/post-height measurement
        outerSVG.addLine(xIndent+70, outerSVGBeamHeight, xIndent+80, outerSVGBeamHeight);
        outerSVG.addLine(xIndent+70,yIndent,xIndent+80,yIndent);
        outerSVG.addLine(xIndent+75,outerSVGBeamHeight,xIndent+75,yIndent);
        outerSVG.addText(xIndent+80,yIndent-(outerSVGSingleBeamHeight/2),-270,upperBeamPostY);

        //post to post
        outerSVG.addLine(xIndent+70,outerSVGSingleBeamHeight, xIndent+80,outerSVGSingleBeamHeight);
        outerSVG.addLine(xIndent+70,yIndent-outerSVGSingleBeamHeight,xIndent+80,yIndent-outerSVGSingleBeamHeight);
        outerSVG.addLine(xIndent+75,outerSVGSingleBeamHeight,xIndent+75,yIndent-outerSVGSingleBeamHeight);
        outerSVG.addText(xIndent+80,(yIndent)/2,-270,lowerBeamPostY-upperBeamPostY);

        return outerSVG;

    }
    public SVG limitedSVG(int reqId) throws UserException {


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

        double beamWidth = 4.5;
        double postWidth = 6;
        double postHeight = 6;
        double postCount = calculate.posts(carportLength,carportWidth);

        double sizeDiff = 1.2;

        if (shortSide<400){
            sizeDiff = 2;
        }

        //viewbox
        int viewBoxL = (int) (longSide);
        int viewBoxS = (int) (shortSide);
        int viewBoxL2 = (int) (longSide*sizeDiff);
        int viewBoxS2 = (int) (shortSide*sizeDiff);




        String l = String.valueOf(viewBoxL);
        String s = String.valueOf(viewBoxS);
        String l2 = String.valueOf(viewBoxL2);
        String s2 = String.valueOf(viewBoxS2);
        String viewBoxSize = "0 0 " + l + " " + s;

        String viewBoxSize2 = "0 0 " + l2 + " " + s2;

        SVG outerSVG = new SVG(0,0, viewBoxSize, 100, 100);
        SVG svg = new SVG(50, 0, viewBoxSize2, 100, 100);

//        SVG svg = new SVG(0, 0, "0 0 "+longSide+100+" "+shortSide+100, 100, 100);

        //carport omrids
        svg.addRect(0, 0, shortSide, longSide);


        //beams
        //2 upper
        svg.addRect(beamX, upperBeamPostY, beamWidth, longSide);

        //2 lower
        svg.addRect(beamX, lowerBeamPostY, beamWidth, longSide);



        //rafter
        for (int i = 0; i < raftersCount; i++) {
            svg.addRect(rafterX, rafterY, shortSide, rafterWidth);
            rafterX = rafterX + raftersDistance;
        }


        //posts row 1
        for (int i = 0; i < postCount; i++) {
            svg.addRect(postX, upperBeamPostY, postHeight, postWidth);
            svg.addRect(postX, lowerBeamPostY, postHeight, postWidth);
            postX = postX + (raftersDistance * 3);
            if (postX > longSide){
                break;
            }
        }
        //y-akse
        svg.addLine(0, 0, 0, shortSide);
        // x-akse
        svg.addLine(0, shortSide, longSide, shortSide);



//        outerSVG.addSvg(svg);
//
//
//        int yIndent = (int) (viewBoxS/sizeDiff);
//        int xIndent = (int) (viewBoxL/sizeDiff);
//        int outerSVGRaftersDistance = (int) (raftersDistance/sizeDiff);
//        int outerSVGSingleBeamHeight = (int) (upperBeamPostY/sizeDiff);
//        int outerSVGBeamHeight = (int) (lowerBeamPostY/sizeDiff);
//
//
////        int upperBeamPostY = (int) (shortSide * 0.12);
////        int lowerBeamPostY = (int) (shortSide * 0.88);
//
//
//        //length/shortside measurement
//        outerSVG.addLine(20, 0, 30, 0);
//        outerSVG.addLine(20,yIndent,30,yIndent);
//        outerSVG.addLine(25,0,25,yIndent);
//        outerSVG.addText(15,(yIndent)/2,-90,shortSide);
//
//        //width/longside measurement
//        outerSVG.addLine(xIndent+50,yIndent+35,xIndent+50,yIndent+45);
//        outerSVG.addLine(50,yIndent+35,50,yIndent+45);
//        outerSVG.addLine(50,yIndent+40,xIndent+50,yIndent+40);
//        outerSVG.addText((xIndent/2)+50,(yIndent)+55,0,longSide);
//
//        //rafters measurement
//        outerSVG.addLine(outerSVGRaftersDistance+50,yIndent+10,outerSVGRaftersDistance+50,yIndent+20);
//        outerSVG.addLine(50,yIndent+10,50,yIndent+20);
//        outerSVG.addLine(50,yIndent+15,outerSVGRaftersDistance+50,yIndent+15);
//        outerSVG.addText((outerSVGRaftersDistance/2)+50,(yIndent)+30,0,raftersDistance);
//
//        //beam/post-height measurement
//        outerSVG.addLine(xIndent+70, outerSVGBeamHeight, xIndent+80, outerSVGBeamHeight);
//        outerSVG.addLine(xIndent+70,yIndent,xIndent+80,yIndent);
//        outerSVG.addLine(xIndent+75,outerSVGBeamHeight,xIndent+75,yIndent);
//        outerSVG.addText(xIndent+80,yIndent-(outerSVGSingleBeamHeight/2),-270,upperBeamPostY);
//
//        //post to post
//        outerSVG.addLine(xIndent+70,outerSVGSingleBeamHeight, xIndent+80,outerSVGSingleBeamHeight);
//        outerSVG.addLine(xIndent+70,yIndent-outerSVGSingleBeamHeight,xIndent+80,yIndent-outerSVGSingleBeamHeight);
//        outerSVG.addLine(xIndent+75,outerSVGSingleBeamHeight,xIndent+75,yIndent-outerSVGSingleBeamHeight);
//        outerSVG.addText(xIndent+80,(yIndent)/2,-270,lowerBeamPostY-upperBeamPostY);
//
//
//        System.out.println("carportLength " + carportLength);
//        System.out.println("carportWidth " + carportWidth);
//        System.out.println("shortSide " + shortSide);
//        System.out.println("longSide " + longSide);

        return svg;

    }


}
