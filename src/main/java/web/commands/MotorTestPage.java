package web.commands;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;
import business.services.RequestFacade;
import business.services.UserFacade;
import model.Calculate;
import model.Materials;
import model.Request;
import model.materials.WoodenPost;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.List;

public class MotorTestPage extends Command {
    public String pageToShow;
    public int userId;
    public String role;

    public MotorTestPage(String pageToShow)
    {
        this.userId = userId;
        this.role = role;
        this.pageToShow = pageToShow;

    }

    public int getId(){

        return userId;

    }
    public String getRole()
    {
        return role;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {



  //      String roleFromCommand = CommandProtectedPage.getRole();



        Calculate calculate = new Calculate(database);
//      Materials.initMaterials();
        double shortSide = 0;
        double surfaceAreaSides = 0;
        double beamsLength = 0;
        double raftersLength = 0;
        int PostsCount = 0;
        int carPortHeight = 210;
        double amountOfSheets = 0;





        double carPortLength = Double.parseDouble(request.getParameter("length"));
        double carPortWidth = Double.parseDouble(request.getParameter("width"));
        String roofType = request.getParameter("roof");

        List<Request> list = new ArrayList<>();

 //       list.add(new Request(1,carPortLength,carPortWidth,roofType));

        RequestFacade requestFacade = new RequestFacade(database);

        HttpSession session = request.getSession();
        requestFacade.createRequest((int) session.getAttribute("userId"),(int) carPortLength,(int) carPortWidth,roofType);


        if (carPortLength<carPortWidth){
            shortSide = carPortWidth;
        }else shortSide= carPortHeight;


 surfaceAreaSides = (carPortLength*carPortHeight+carPortWidth*carPortHeight+shortSide*carPortHeight);



        PostsCount = (int) calculate.posts(carPortLength,carPortWidth);
        beamsLength = calculate.beams(carPortLength,carPortWidth);
        raftersLength = calculate.rafters(carPortLength,carPortWidth);
        amountOfSheets = calculate.roofing(carPortLength, carPortWidth);





        request.getSession().setAttribute("raftersLength",raftersLength);
        request.getSession().setAttribute("beamsLength",beamsLength);
        request.getSession().setAttribute("length",carPortLength);
        request.getSession().setAttribute("width",carPortWidth);
        request.getSession().setAttribute("roof",roofType);
        request.getSession().setAttribute("amountOfSheets", amountOfSheets);
        request.getSession().setAttribute("surface",surfaceAreaSides);
        request.getSession().setAttribute("woodenPostCount",PostsCount);

        return "motortest";
    }
}
