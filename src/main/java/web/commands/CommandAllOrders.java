package web.commands;

import business.exceptions.UserException;
import business.services.RequestFacade;
import model.Request;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CommandAllOrders extends Command {
    public CommandAllOrders(String pageToShow) {
//        super();

        this.pageToShow = pageToShow;
    }
    public String pageToShow;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        RequestFacade requestFacade = new RequestFacade(database);
        HttpSession session = request.getSession();

        int reqId = 0;

        int userId = (int) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");
        try {
            reqId = (int) session.getAttribute("reqid");
        } catch (Exception e){

        }

        List<Request> requestsList = new ArrayList<>();

        if (role.equals("customer")) {
            requestsList = requestFacade.getRequestById(userId);
        } else if (role.equals("employee")) {
            requestsList = requestFacade.getAllRequests();
        }

        request.getSession().setAttribute("requestsList", requestsList);
        request.getSession().setAttribute("reqid", reqId);
        request.getSession().setAttribute("userid", userId);
        request.getSession().setAttribute("role", role);

        return pageToShow;
    }

}
