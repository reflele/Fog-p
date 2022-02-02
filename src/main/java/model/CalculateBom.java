package model;

import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;
import business.services.RequestFacade;

import java.util.ArrayList;
import java.util.List;

import static web.FrontController.database;

public class CalculateBom {

    MaterialFacade materialFacade;
    Calculate calculate;
    RequestFacade requestFacade;


    public CalculateBom(Database database) {
        materialFacade = new MaterialFacade(database);
        calculate = new Calculate(database);
        requestFacade = new RequestFacade(database);

//        MaterialFacade materialFacade = new MaterialFacade(database);
    }

    public double carportPrice(int reqId) throws UserException {

        double price;


        List<Request> requestList = requestFacade.getAllRequests();

        int i = 0;
        while (true) {
            if (requestList.get(i).getId() == reqId) {
                price = requestList.get(i).getPrice();
                break;
            }
            i++;
        }
        return price;
    }

    public String carportDescription(int reqId) throws UserException {

        String description;
        RequestFacade requestFacade = new RequestFacade(database);

        List<Request> requestList = requestFacade.getAllRequests();

        int i = 0;
        while (true) {
            if (requestList.get(i).getId() == reqId) {
                description = requestList.get(i).getDescription();
                break;
            }
            i++;
        }
        return description;
    }

    public ArrayList<BomMaterial> bomList(int reqId) throws UserException {

        ArrayList<BomMaterial> bomMaterials;


        int carPortLength = 0;
        int carPortWidth = 0;


        List<Request> requestList = requestFacade.getAllRequests();

        int i = 0;
        while (true) {
            if (requestList.get(i).getId() == reqId) {
                carPortLength = requestList.get(i).getLength();
                carPortWidth = requestList.get(i).getWidth();
                break;
            }
            i++;
        }

        calculate.posts(carPortLength, carPortWidth);
        calculate.beams(carPortLength, carPortWidth);
        calculate.rafters(carPortLength, carPortWidth);
        calculate.roofing(carPortLength, carPortWidth);
        calculate.fittings(carPortLength, carPortWidth);

        bomMaterials = calculate.getBomMaterials();


        return bomMaterials;
    }


    public double bomPrice(int reqId) throws UserException {

        Calculate calculateBomPrice = new Calculate(database);
        Request request = requestFacade.getRequestByRequestId(reqId);

        double reqPurchasePrise = 0;


        int carPortLength = request.getLength();
        int carPortWidth = request.getWidth();


        calculateBomPrice.posts(carPortLength, carPortWidth);
        calculateBomPrice.beams(carPortLength, carPortWidth);
        calculateBomPrice.rafters(carPortLength, carPortWidth);
        calculateBomPrice.roofing(carPortLength, carPortWidth);
        calculateBomPrice.fittings(carPortLength, carPortWidth);


        reqPurchasePrise = calculateBomPrice.calcPrice(calculateBomPrice.getBomMaterials());


        return reqPurchasePrise;
    }
}

