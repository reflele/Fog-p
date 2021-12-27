package web.commands;

import business.entities.Material;
import business.entities.SVG;
import business.entities.User;
import business.exceptions.UserException;
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
import java.util.List;

public class FullShowMoreCommand extends Command {
    public FullShowMoreCommand(String pageToShow) {
        super();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {


        RequestFacade requestFacade = new RequestFacade(database);
        MaterialFacade materialFacade = new MaterialFacade(database);
        CalculateBom calculateBom = new CalculateBom(database);

        HttpSession session = request.getSession();
        int reqId;
        double price = 0;
        String description;
        ArrayList<BomMaterial> bomMaterials;
        ArrayList<Material> materialList = new ArrayList<>();





        reqId = Integer.parseInt(request.getParameter("reqid"));
        description = calculateBom.carportDescription(reqId);
        double purchasePrice = calculateBom.bomPrice(reqId);

        bomMaterials = calculateBom.bomList(reqId);

        for (int i = 0; i < bomMaterials.size(); i++) {
           Material material = materialFacade.getMaterialById(bomMaterials.get(i).getMaterialId());
//            System.out.println("category");
//           System.out.println(material.getCategory());
//            System.out.println("getMaterial_id");
//            System.out.println(material.getMaterial_id());
//            System.out.println("getLength");
//            System.out.println(material.getLength());
//            System.out.println("getPrice");
//            System.out.println(material.getPrice());
//            System.out.println("getUnit");
//            System.out.println(material.getUnit());
//            System.out.println("getHeight");
//            System.out.println(material.getHeight());
//            System.out.println("getDescription");
//            System.out.println(material.getDescription());
//            System.out.println("getWidth");
//            System.out.println(material.getWidth());

           materialList.add(material);

        }
//        System.out.println(bomMaterials.size());
        Request carportRequest = requestFacade.getRequestByRequestId(reqId);

        int n = (int) session.getAttribute("userId");

        String firstName = (String) session.getAttribute("firstname");

        System.out.println("firstname");
        System.out.println(firstName);

        String firstName2 = (String) session.getAttribute("firstName");

        System.out.println("firstname");
        System.out.println(firstName2);

        DrawSVG drawSVG = new DrawSVG(database);

        SVG carport =  drawSVG.draw(reqId);


        request.setAttribute("svgdrawing", carport.toString().replace(",","."));
        request.getSession().setAttribute("firstname", firstName);
        request.getSession().setAttribute("carportrequest", carportRequest);
        request.getSession().setAttribute("bommaterials", bomMaterials);
        request.getSession().setAttribute("materiallist", materialList);
        request.getSession().setAttribute("description", description);
        request.getSession().setAttribute("reqid", reqId);



//       try {
//           reqId = Integer.parseInt(request.getParameter("reqid"));
//           price = Double.parseDouble(request.getParameter("price"));
//       } catch (Exception e){
////           System.out.println("reqid eller price er nok null");
//       }
//        List<Request> requestsList = new ArrayList<>();


//        request.getSession().setAttribute("requestsList", requestsList);







        return "fullshowmore";
    }
}
