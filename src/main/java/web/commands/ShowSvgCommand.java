package web.commands;

import business.exceptions.UserException;
import business.entities.SVG;
import business.persistence.Database;
import model.Calculate;
import model.DrawSVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSvgCommand extends CommandUnprotectedPage {


    public ShowSvgCommand(String pageToShow) {
        super(pageToShow);
    }


    //her laves svg tegning
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        DrawSVG drawSVG = new DrawSVG(database);


        String carport = drawSVG.fullSVG(180).toString().replace(",500000",".");
        request.setAttribute("svgdrawing", carport.replace(",000000",""));
        //ints ville virke
        //doubles skaber problemer på certain devices
        //midlertidig løsning, ændrer alle kommatallene
        return pageToShow;

    }

}
