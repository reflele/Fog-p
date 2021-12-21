package web.commands;

import business.exceptions.UserException;
import business.services.Calculate;
import business.services.SVG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSvgCommand extends CommandUnprotectedPage {
    public ShowSvgCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        //her laves SVG tegning
        Calculate calculate;
        //viewbox
        SVG svg = new SVG(0, 0, "0 0 800 600", 100, 100);



//spær
        for (int i = 0; i < calculate.; i++)
            svg.addRect(100 + 50 * i, 0, 600, 4);





        request.setAttribute("svgdrawing", svg.toString());
        return pageToShow;
    }
}
