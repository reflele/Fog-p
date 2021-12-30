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

    public CalculateBom(Database database) {
        materialFacade = new MaterialFacade(database);
//        MaterialFacade materialFacade = new MaterialFacade(database);
    }

    public double carportPrice(int reqId) throws UserException {

        double price;
        Calculate calculateBom = new Calculate(database);
        RequestFacade requestFacade = new RequestFacade(database);

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

            ArrayList<BomMaterial> bomMaterials = new ArrayList<>();

        Calculate calculate = new Calculate(database);
        RequestFacade requestFacade = new RequestFacade(database);

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
//        calculate.screws(carPortLength,carPortWidth);
        calculate.fittings(carPortLength,carPortWidth);

        bomMaterials = calculate.getBomMaterials();



        return bomMaterials;
    }



    public double bomPrice(int reqId) throws UserException {

        double reqPurchasePrise = 0;
        int carPortLength = 0;
        int carPortWidth = 0;

        Calculate calculateBom = new Calculate(database);
        RequestFacade requestFacade = new RequestFacade(database);

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


        calculateBom.posts(carPortLength, carPortWidth);
        calculateBom.beams(carPortLength, carPortWidth);
        calculateBom.rafters(carPortLength, carPortWidth);
        calculateBom.roofing(carPortLength, carPortWidth);
//        calculateBom.screws(carPortLength,carPortWidth);
        calculateBom.fittings(carPortLength,carPortWidth);

        reqPurchasePrise = calculateBom.calcPrice(calculateBom.getBomMaterials());


        return reqPurchasePrise;
    }
    }

