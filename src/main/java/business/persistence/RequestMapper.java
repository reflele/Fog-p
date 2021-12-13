package business.persistence;

import business.exceptions.UserException;
import model.Request;

import java.sql.*;

public class RequestMapper
{
    private Database database;

    public RequestMapper(Database database)
    {
        this.database = database;
    }




    public void createRequest(Request request) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO carport_request (user_id, length, width, rooftype) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setInt(1, request.getUser_id());
                ps.setInt(2, request.getLength());
                ps.setInt(3, request.getWidth());
                ps.setString(4, request.getRoofType());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                request.setUser_id(id);
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException(ex.getMessage());
        }
    }

}
