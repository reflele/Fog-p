package web.commands;

import business.exceptions.UserException;
import business.services.RequestFacade;
import model.CalculateBom;
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

       try {
           reqId = Integer.parseInt(request.getParameter("reqid"));
           price = Double.parseDouble(request.getParameter("price"));
       } catch (Exception e){
//           System.out.println("reqid eller price er nok null");
       }

//        int userId = (int) session.getAttribute("userId");
//        String role = (String) session.getAttribute("role");


        List<Request> requestsList = new ArrayList<>();

        request.getSession().setAttribute("price",price);
        request.getSession().setAttribute("reqid", reqId);
        request.getSession().setAttribute("requestsList", requestsList);
        request.getSession().setAttribute("description", description);

        return "limitedshowmore";
    }
}
