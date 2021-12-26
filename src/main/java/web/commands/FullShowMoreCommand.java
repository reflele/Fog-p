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

public class FullShowMoreCommand extends Command {
    public FullShowMoreCommand(String pageToShow) {
        super();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        RequestFacade requestFacade = new RequestFacade(database);
        CalculateBom calculateBom = new CalculateBom(database);

        HttpSession session = request.getSession();
        int reqId;
        double price = 0;
        String description;


        reqId = Integer.parseInt(request.getParameter("reqid"));
        description = calculateBom.carportDescription(reqId);



       try {
           reqId = Integer.parseInt(request.getParameter("reqid"));
           price = Double.parseDouble(request.getParameter("price"));
       } catch (Exception e){
//           System.out.println("reqid eller price er nok null");
       }


        List<Request> requestsList = new ArrayList<>();


        request.getSession().setAttribute("requestsList", requestsList);



        request.getSession().setAttribute("description", description);
        request.getSession().setAttribute("reqid", reqId);











        return "fullshowmore";
    }
}
