package web.commands;

import business.exceptions.UserException;
import business.services.RequestFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Redirect extends Command {
    public Redirect(String pageToShow) {
        super();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        RequestFacade requestFacade = new RequestFacade(database);

        HttpSession session = request.getSession();

        String destination = request.getParameter("destination");

        int userId = (int) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");

        try {
            int updatePrice = Integer.parseInt(request.getParameter("updateprice"));
            if (updatePrice == 1) {
                int reqId = Integer.parseInt(request.getParameter("reqid"));
                double price = Double.parseDouble(request.getParameter("price"));
                requestFacade.addPrice(reqId, price);

                CommandAllOrders commandAllOrders = new CommandAllOrders("showallorders");
                commandAllOrders.execute(request, response);
            }
        } catch (Exception e) {

        }

        try {
            int updateStatus = Integer.parseInt(request.getParameter("updatestatus"));
            if (updateStatus == 1) {
                int reqId = Integer.parseInt(request.getParameter("reqid"));
                requestFacade.updateStatus(reqId, updateStatus);

                CommandAllOrders commandAllOrders = new CommandAllOrders("showallorders");
                commandAllOrders.execute(request, response);
            }

        } catch (Exception e) {
        }


        request.getSession().setAttribute("userid", userId);
        request.getSession().setAttribute("role", role);

        return destination;
    }
}
