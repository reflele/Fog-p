package web.commands;

import business.exceptions.UserException;
import model.Calculate;
import model.CalculateBom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandEditOrder extends Command
{
    public String role;
    public int id;
    public String email;
    public String pageToShow;

    public CommandEditOrder(String pageToShow, String role)
    {
        this.pageToShow = pageToShow;
        this.role = role;
        this.id = id;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        double purchasePrice;
        double salesPrice;

        CalculateBom calculateBom = new CalculateBom(database);

        int reqId = Integer.parseInt(request.getParameter("reqid"));


        purchasePrice = calculateBom.bomPrice(reqId);
        salesPrice = 1.5 * purchasePrice;

        request.getSession().setAttribute("reqid", reqId);
        request.getSession().setAttribute("purchasePrice", purchasePrice);
        request.getSession().setAttribute("salesPrice", salesPrice);




        return pageToShow;

    }

    public int getId(){
        return id;
    }

    public String getRole()
    {
        return role;
    }
    public String getEmail(){
        return email;
    }

}
