package web.commands;

import business.entities.SVG;
import business.exceptions.UserException;
import business.services.RequestFacade;
import model.CalculateBom;
import model.DrawSVG;
import model.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LimitedShowMoreCommand extends Command {
    public LimitedShowMoreCommand(String pageToShow) {
        super();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        RequestFacade requestFacade = new RequestFacade(database);

        HttpSession session = request.getSession();
        int reqId;
        double price = 0;
        reqId = Integer.parseInt(request.getParameter("reqid"));

        CalculateBom calculateBom = new CalculateBom(database);

        price = calculateBom.carportPrice(reqId);

        String description = calculateBom.carportDescription(reqId);
        Request request1 = requestFacade.getRequestByRequestId(reqId);

       try {
           reqId = Integer.parseInt(request.getParameter("reqid"));
           price = Double.parseDouble(request.getParameter("price"));
       } catch (Exception e){
//           System.out.println("reqid eller price er nok null");
       }

        DrawSVG drawSVG = new DrawSVG(database);

        List<Request> requestsList = new ArrayList<>();

        String carport = drawSVG.limitedSVG(reqId).toString().replace(",500000",".");
        request.setAttribute("svgdrawing", carport.replace(",000000",""));

        request.getSession().setAttribute("price",price);
        request.getSession().setAttribute("reqid", reqId);
        request.getSession().setAttribute("requestsList", requestsList);
        request.getSession().setAttribute("description", description);
        request.getSession().setAttribute("request", request1);

        return "limitedshowmore";
    }
}
