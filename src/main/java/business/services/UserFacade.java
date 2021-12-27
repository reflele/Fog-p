package business.services;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;
import model.Request;

public class UserFacade
{
    UserMapper userMapper;

    public UserFacade(Database database)
    {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException
    {
        return userMapper.login(email, password);
    }

    public User createUser(String email, String password, String address, String zipcode, String phoneNumber, String firstName, String lastName) throws UserException
    {
        User user = new User(email, password, "customer", address, zipcode, phoneNumber, firstName, lastName);
        userMapper.createUser(user);
        return user;
    }
//    public User getUserByUserId (int Id) throws UserException{
//        return Usermapper.getUserByUserId(Id);
//    }
}
