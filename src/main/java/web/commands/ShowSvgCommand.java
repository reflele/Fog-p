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
int postDistance = (int) Calculate.postsDistance;

        //viewbox
        SVG svg = new SVG(0, 0, "0 0 800 600", 100, 100);

        //carport omrids
        svg.addRect(0,0, 220, 200);


        for (int i = 0; i < 14; i++) {
            svg.addRect(100 + raftersDistance * i, 0, 600, 4.5);
        }


        //stolper
        for (int i = 0; i < 14; i++) {
            svg.addRect(100 + 210 * i, 0, 4.5, 4.5);
        }


        request.setAttribute("svgdrawing", svg.toString());
        return pageToShow;
    }

}
