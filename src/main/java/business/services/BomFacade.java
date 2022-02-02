package business.services;

import business.exceptions.UserException;
import business.persistence.BomMapper;
import business.persistence.Database;
import model.BomMaterial;

import java.util.ArrayList;
import java.util.List;

public class BomFacade
{
    BomMapper bomMapper;

    public BomFacade(Database database)
    {
        bomMapper = new BomMapper(database);
    }



    public void createBom(List<BomMaterial> bomlist, int reqId) throws UserException
    {
        bomMapper.createBom(bomlist, reqId);
    }

    public ArrayList<BomMaterial> getBom(int reqId) throws UserException
    {
        return bomMapper.getBom(reqId);
    }
}
