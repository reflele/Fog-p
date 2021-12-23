package web.commands;

import business.exceptions.UserException;
import business.entities.SVG;
import model.Calculate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSvgCommand extends CommandUnprotectedPage {


    public ShowSvgCommand(String pageToShow) {
        super(pageToShow);
    }


    //her laves svg tegning
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        Calculate calculate = new Calculate(database);

        int raftersDistance = (int) calculate.getRaftersDistance();
        int postDistance = (int) calculate.getPostsDistance();
        int carportHeight = 600;
        int carportWidth = 780;


        double rafterWidth = 4.5;
        int raftersCount = 16;
        int rafterX = 0;
        int rafterY = 0;
        int beamX = 0;
        int upperBeamPostY = (int) (carportHeight * 0.12);
        int lowerBeamPostY = (int) (carportHeight * 0.88);
        double beamWidth = 4.5;

        //viewbox
        SVG svg = new SVG(0, 0, "0 0 800 600", 100, 50);

        //carport omrids
        svg.addRect(0, 0, carportHeight, carportWidth);

        //rafter
        for (int i = 0; i < raftersCount; i++) {
            svg.addRect(rafterX , 0, carportHeight, rafterWidth);
            rafterX = rafterX + raftersDistance;
        }

        //beams
        svg.addRect(beamX, upperBeamPostY, beamWidth, carportWidth);
        svg.addRect(beamX, lowerBeamPostY, beamWidth, carportWidth);

//        //posts
//        for (int a = 0; a < 14; a++) {
//            svg.addRect(100 + postDistance * i, (int) y, 4.5, 4.5);
//        }


        request.setAttribute("svgdrawing", svg.toString());
        return pageToShow;

    }

}
