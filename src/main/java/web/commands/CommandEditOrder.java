package web.commands;

import business.entities.Material;
import business.exceptions.UserException;
import business.services.BomFacade;
import business.services.MaterialFacade;
import business.services.RequestFacade;
import model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CommandEditOrder extends Command {
    public String role;
    public int id;
    public String email;
    public String pageToShow;

    public CommandEditOrder(String pageToShow, String role) {
        this.pageToShow = pageToShow;
        this.role = role;
        this.id = id;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        Calculate calculate = new Calculate(database);
        CalculateBom calculateBom = new CalculateBom(database);
        RequestFacade requestFacade = new RequestFacade(database);
        MaterialFacade materialFacade = new MaterialFacade(database);
        BomFacade bomFacade = new BomFacade(database);


        double purchasePrice = 0;
        double salesPrice = 0;
        int updateBom = 0;

        int reqId = Integer.parseInt(request.getParameter("reqid"));
        Request carportRequest = requestFacade.getRequestByRequestId(reqId);

        List<Material> type0 = materialFacade.getMaterialByCategory("stolpe");
        List<Material> type1 = materialFacade.getMaterialByCategory("rem");
        List<Material> type2 = materialFacade.getMaterialByCategory("spær");
        List<Material> type5 = materialFacade.getMaterialByCategory("tagplade"); // intialiserer materialelister


        ArrayList<BomMaterial> bomMaterials = bomFacade.getBom(reqId); //count og matid
        ArrayList<Material> materialList = new ArrayList<>();

        if (bomMaterials == null) {
            bomMaterials = calculateBom.bomList(reqId); //count og id request
        }

        try {
            updateBom = Integer.parseInt(request.getParameter("updatebom"));

            if (updateBom == 1) {
                int mat0 = Integer.parseInt(request.getParameter("0"));//Stolper
                int mat1 = Integer.parseInt(request.getParameter("1"));//Rem
                int mat2 = Integer.parseInt(request.getParameter("2"));//Spær
                int mat3 = Integer.parseInt(request.getParameter("3"));//Skruer
                int mat4 = Integer.parseInt(request.getParameter("4"));//Skruer
                int mat5 = Integer.parseInt(request.getParameter("5"));//Tagplader
                int mat6 = Integer.parseInt(request.getParameter("6"));//Tagskruer
                int mat7 = Integer.parseInt(request.getParameter("7"));//Beslag
                int mat8 = Integer.parseInt(request.getParameter("8"));//Beslagskruer


                if (mat0 != bomMaterials.get(0).getCount()) ;
                bomMaterials.get(0).setCount(mat0);
                if (mat1 != bomMaterials.get(1).getCount()) ;
                bomMaterials.get(1).setCount(mat1);
                if (mat2 != bomMaterials.get(2).getCount()) ;
                bomMaterials.get(2).setCount(mat2);
                if (mat3 != bomMaterials.get(3).getCount()) ;
                bomMaterials.get(3).setCount(mat3);
                if (mat4 != bomMaterials.get(4).getCount()) ;
                bomMaterials.get(4).setCount(mat4);
                if (mat5 != bomMaterials.get(5).getCount()) ;
                bomMaterials.get(5).setCount(mat5);
                if (mat6 != bomMaterials.get(6).getCount()) ;
                bomMaterials.get(6).setCount(mat6);
                if (mat7 != bomMaterials.get(7).getCount()) ;
                bomMaterials.get(7).setCount(mat7);
                if (mat8 != bomMaterials.get(8).getCount()) ;
                bomMaterials.get(8).setCount(mat8);  //opdaterer styklisten

            }

        } catch (Exception e) {
        } //"opdater priser og stykliste"

        try {
            int materialId = Integer.parseInt(request.getParameter("materialid"));
            int count = Integer.parseInt(request.getParameter("count"));
            if (materialId != 0) {
                Material mat = materialFacade.getMaterialById(materialId);
                for (int i = 0; i < bomMaterials.size(); i++) {
                    Material bommat = materialFacade.getMaterialById(bomMaterials.get(i).getMaterialId());
                    if (bommat.getCategory().equals(mat.getCategory())) {
                        bomMaterials.get(i).setMaterialId(mat.getMaterial_id());
                        if (count != 0) {
                            bomMaterials.get(i).setCount(count);
                        }
                    }
                }
            }

        } catch (Exception e) {
        } //updatebom logik


        for (int i = 0; i < bomMaterials.size(); i++) {
            Material material = materialFacade.getMaterialById(bomMaterials.get(i).getMaterialId());
            materialList.add(material); //indeholder materialerne der gør at styklistetabellen kan populeres.
        }

        purchasePrice = calculate.calcPrice(bomMaterials);
        bomFacade.createBom(bomMaterials, reqId);

        if (purchasePrice <= 3000) {
            salesPrice = purchasePrice * 1.4;
        } else {
            salesPrice = purchasePrice * 1.6;
        }

        DrawSVG drawSVG = new DrawSVG(database);

        String carport = drawSVG.fullSVG(reqId).toString().replace(",500000", ".");

        request.setAttribute("svgdrawing", carport.replace(",000000", ""));
        request.getSession().setAttribute("materiallist", materialList);
        request.getSession().setAttribute("bommaterials", bomMaterials);
        request.getSession().setAttribute("reqid", reqId);
        request.getSession().setAttribute("purchasePrice", String.format("%.2f", purchasePrice));
        request.getSession().setAttribute("salesPrice", String.format("%.2f", salesPrice));
        request.getSession().setAttribute("request", carportRequest);



        request.getSession().setAttribute("stolpe", type0);
        request.getSession().setAttribute("rem", type1);
        request.getSession().setAttribute("spær", type2);
        request.getSession().setAttribute("tagplade", type5);
        return pageToShow;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

}
