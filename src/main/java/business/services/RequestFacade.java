package business.services;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.RequestMapper;
import business.persistence.UserMapper;
import model.Calculate;
import model.Request;

import java.util.List;

public class RequestFacade
{
    RequestMapper requestMapper;

    public RequestFacade(Database database)
    {
        requestMapper = new RequestMapper(database);
    }

    //id refers to userId, not requestId
public List<Request> getRequestById (int id) throws UserException{
    return requestMapper.getRequestById(id);
}

    public Request getRequestByRequestId (int reqId) throws UserException{
        return requestMapper.getRequestByRequestId(reqId);
    }


    public void addPrice (int requestId, double price) throws UserException{
        requestMapper.addPrice(requestId, price);
    }

    public void updateStatus (int requestId, int status) throws UserException{
        requestMapper.updateStatus(requestId, status);
    }

    public Request createRequest(int user_id, int length, int width, String roofType, String description) throws UserException
    {
        Request request = new Request(user_id,length,width,roofType, description);

        requestMapper.createRequest(request);

        return request;
    }

    public List<Request> getAllRequests() throws UserException {
        return requestMapper.getAllRequests();
    }
}
