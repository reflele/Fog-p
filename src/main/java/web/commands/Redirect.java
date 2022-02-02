package web.commands;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.MaterialFacade;
import business.services.RequestFacade;
import model.BomMaterial;
import model.CalculateBom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class Redirect extends Command {
    private String message;

    public Redirect(String pageToShow) {
        super();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        RequestFacade requestFacade = new RequestFacade(database);
        BomFacade bomFacade = new BomFacade(database);
        MaterialFacade materialFacade = new MaterialFacade(database);
        CalculateBom calculateBom = new CalculateBom(database);

        HttpSession session = request.getSession();

        String destination = request.getParameter("destination");

        int userId = (int) session.getAttribute("userId");
        String role = (String) session.getAttribute("role");
        int updatePrice = 0;

        try {
           updatePrice = Integer.parseInt(request.getParameter("updateprice"));
        } catch (Exception e) {
        }
            if (updatePrice == 1) {
                int reqId = Integer.parseInt(request.getParameter("reqid"));
                double price = Double.parseDouble(request.getParameter("price"));
                requestFacade.addPrice(reqId, price);



                ArrayList<BomMaterial> bomMaterials;
                bomMaterials = calculateBom.bomList(reqId); //count og id

                message = "Den angivne pris for forespørsel nr." + reqId + "er nu gemt.";

                CommandAllOrders commandAllOrders = new CommandAllOrders("showallorders");
                commandAllOrders.execute(request, response);

            }

            try {
                int updateStatus = Integer.parseInt(request.getParameter("updatestatus"));
                if (updateStatus == 1) {
                    int reqId = Integer.parseInt(request.getParameter("reqid"));
                    requestFacade.updateStatus(reqId, updateStatus);

                    message = "Tak for din betaling for forespørgsel nummer: "+ reqId +". Du kan nu trykke på 'Stykliste & ordrebek.' for at se hele din ordre";

                    CommandAllOrders commandAllOrders = new CommandAllOrders("showallorders");
                    commandAllOrders.execute(request, response);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }


            request.getSession().setAttribute("message", message);
            request.getSession().setAttribute("userid", userId);
            request.getSession().setAttribute("role", role);

            return destination;
        }
    }
