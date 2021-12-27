package web.commands;

import business.exceptions.UserException;
import business.services.RequestFacade;
import model.BomMaterial;
import model.Calculate;
import model.CalculateBom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class MotorTestPage extends Command {
    public String pageToShow;
    public int userId;
    public String role;

    public MotorTestPage(String pageToShow) {
        this.userId = userId;
        this.role = role;
        this.pageToShow = pageToShow;

    }

    public int getId() {

        return userId;

    }

    public String getRole() {
        return role;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        RequestFacade requestFacade = new RequestFacade(database);
        Calculate calculate = new Calculate(database);

        int carPortHeight = 210;
        double shortSide = 0;
        double surfaceAreaSides;
        double beamsLength;
        double raftersLength;
        int postsCount;
        double SheetsCount;



        double carPortLength = Double.parseDouble(request.getParameter("length"));
        double carPortWidth = Double.parseDouble(request.getParameter("width"));
        String roofType = request.getParameter("roof");
        String description = request.getParameter("description");

        HttpSession session = request.getSession();

        requestFacade.createRequest((int) session.getAttribute("userId"), (int) carPortLength, (int) carPortWidth, roofType, description);

        int n = (int) session.getAttribute("userId");

        surfaceAreaSides = (carPortLength * carPortHeight + carPortWidth * carPortHeight + shortSide * carPortHeight);

        postsCount = (int) calculate.posts(carPortLength, carPortWidth);
        beamsLength = calculate.beams(carPortLength, carPortWidth);
        raftersLength = calculate.rafters(carPortLength, carPortWidth);
        SheetsCount = calculate.roofing(carPortLength, carPortWidth);

        System.out.println(calculate.calcPrice(calculate.getBomMaterials()));


        request.getSession().setAttribute("raftersLength", raftersLength);
        request.getSession().setAttribute("beamsLength", beamsLength);
        request.getSession().setAttribute("length", carPortLength);
        request.getSession().setAttribute("width", carPortWidth);
        request.getSession().setAttribute("roof", "roofType");
        request.getSession().setAttribute("amountOfSheets", SheetsCount);
        request.getSession().setAttribute("surface", surfaceAreaSides);
        request.getSession().setAttribute("woodenPostCount", postsCount);
        request.getSession().setAttribute("description", description);


        return "motortest";
    }
}
