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

       int id = (int) session.getAttribute("userId");


       List<Request> requestsList = requestFacade.getRequestById(id);


     request.getSession().setAttribute("requestsList",requestsList);

            return "showallorders";
    }
}
