package web.commands;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.MaterialFacade;
import business.services.RequestFacade;
import model.BomMaterial;
import model.CalculateBom;
import model.DrawSVG;
import model.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class FullShowMoreCommand extends Command {
    public FullShowMoreCommand(String pageToShow) {
        super();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        CalculateBom calculateBom = new CalculateBom(database);
        RequestFacade requestFacade = new RequestFacade(database);
        MaterialFacade materialFacade = new MaterialFacade(database);
        BomFacade bomFacade = new BomFacade(database);

        HttpSession session = request.getSession();
        int reqId;
        double price = 0;
        String description;
        ArrayList<BomMaterial> bomMaterials;
        ArrayList<Material> materialList = new ArrayList<>();


        reqId = Integer.parseInt(request.getParameter("reqid"));


        description = calculateBom.carportDescription(reqId);
        double purchasePrice = calculateBom.bomPrice(reqId);


        bomMaterials = bomFacade.getBom(reqId);

        if (bomMaterials == null){
            bomMaterials = calculateBom.bomList(reqId);
        }


        for (int i = 0; i < bomMaterials.size(); i++) {
            Material material = materialFacade.getMaterialById(bomMaterials.get(i).getMaterialId());

            materialList.add(material);




            Request carportRequest = requestFacade.getRequestByRequestId(reqId);


            DrawSVG drawSVG = new DrawSVG(database);

            String carport = drawSVG.fullSVG(reqId).toString().replace(",500000",".");

            request.setAttribute("svgdrawing", carport.replace(",000000",""));
            request.getSession().setAttribute("carportrequest", carportRequest);
            request.getSession().setAttribute("bommaterials", bomMaterials);
            request.getSession().setAttribute("materiallist", materialList);
            request.getSession().setAttribute("description", description);
            request.getSession().setAttribute("reqid", reqId);


        }
        return "fullshowmore";
    }

}