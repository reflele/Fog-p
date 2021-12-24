package web.commands;

import business.exceptions.UserException;
import business.services.RequestFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Redirect extends Command{
    public Redirect(String pageToShow) {
        super();
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String destination = request.getParameter("destination");

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









        return destination;
    }
}
