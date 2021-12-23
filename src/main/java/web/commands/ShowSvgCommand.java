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

        SVG carport =  drawSVG.draw(60);


        request.setAttribute("svgdrawing", carport.toString());
        return pageToShow;

    }

}
