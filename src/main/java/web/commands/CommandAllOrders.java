package web.commands;

import business.exceptions.UserException;
import business.services.RequestFacade;
import model.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CommandAllOrders extends Command {
    public CommandAllOrders(String showallorders) {
        super();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        RequestFacade requestFacade = new RequestFacade(database);
        HttpSession session = request.getSession();
        int updatePrice = 0;

        try {
            updatePrice = Integer.parseInt(request.getParameter("updateprice"));
        }catch (Exception e){

        }
        if (updatePrice == 1){
            int reqId = Integer.parseInt(request.getParameter("reqid"));
            double price = Double.parseDouble(request.getParameter("price"));
            requestFacade.addPrice(reqId, price);
        }
//       //dette tilføjer en pris til en specifik carportforespørgsel. metoden burde nok eksistere et andet sted i koden.

        int userId = (int) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        List<Request> requestsList = new ArrayList<>();

        if (role.equals("customer")) {
            requestsList = requestFacade.getRequestById(userId);
        } else if (role.equals("employee")) {
            requestsList = requestFacade.getAllRequests();
        }


//        request.getSession().setAttribute("reqid", reqId);
        request.getSession().setAttribute("requestsList", requestsList);

        return "showallorders";
    }
}
