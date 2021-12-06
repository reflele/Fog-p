package web.commands;

import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MotorTestPage extends Command {
    public String pageToShow;

    public MotorTestPage(String pageToShow)
    {
        this.pageToShow = pageToShow;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        int height = 210;


        double length = Double.parseDouble(request.getParameter("length"));
        double width = Double.parseDouble(request.getParameter("width"));
        String roofType = request.getParameter("roof");


        double shortSide = 0;
        double surfaceAreaSides;

        if (length<width){
            shortSide = width;
        }else shortSide= height;

        surfaceAreaSides = (surfaceAreaSides = length*height+width*height+shortSide*height);


        request.getSession().setAttribute("length",length);
        request.getSession().setAttribute("width",width);
        request.getSession().setAttribute("roof",roofType);
        request.getSession().setAttribute("surface",surfaceAreaSides);

        return "motortest";
    }
}
