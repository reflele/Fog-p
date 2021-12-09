package web.commands;

import business.exceptions.UserException;
import model.Calculate;
import model.Materials;
import model.materials.WoodenPost;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class MotorTestPage extends Command {
    public String pageToShow;

    public MotorTestPage(String pageToShow)
    {
        this.pageToShow = pageToShow;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        double shortSide = 0;
        double surfaceAreaSides = 0;
        int PostsCount = 0;

        int carPortHeight = 210;
        double carPortLength = Double.parseDouble(request.getParameter("length"));
        double carPortWidth = Double.parseDouble(request.getParameter("width"));
        String roofType = request.getParameter("roof");








        if (carPortLength<carPortWidth){
            shortSide = carPortWidth;
        }else shortSide= carPortHeight;

        Materials.initMaterials();
        ArrayList<WoodenPost> woodenPosts = new ArrayList<>();
        woodenPosts = Materials.getWoodenPosts();

//        for (int i = 0; i < materials.; i++) {
//
//        }



        surfaceAreaSides = (carPortLength*carPortHeight+carPortWidth*carPortHeight+shortSide*carPortHeight);


//        if (surfaceAreaSides == 1) {
//            woodenPostCount = 1;
//        } else if (surfaceAreaSides == 2) {
//            woodenPostCount = 2;
//        } else if (surfaceAreaSides >= 3) {
//            woodenPostCount = 3;
//        }// til beregning af antal bjælker


        PostsCount = (int) Calculate.posts(carPortLength,carPortWidth);



        request.getSession().setAttribute("length",carPortLength);
        request.getSession().setAttribute("width",carPortWidth);
        request.getSession().setAttribute("roof",roofType);
        request.getSession().setAttribute("surface",surfaceAreaSides);
        request.getSession().setAttribute("woodenPostCount",PostsCount);

        return "motortest";
    }
}
