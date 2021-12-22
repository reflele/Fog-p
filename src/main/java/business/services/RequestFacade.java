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


public List<Request> getRequestById (int id) throws UserException{
    return requestMapper.getRequestById(id);
}

    public Request createRequest(int user_id, int length, int width, String roofType) throws UserException
    {
        Request request = new Request(user_id,length,width,roofType);

        requestMapper.createRequest(request);

        return request;
    }

    public List<Request> getAllRequests() throws UserException {
        return requestMapper.getAllRequests();
    }
}
