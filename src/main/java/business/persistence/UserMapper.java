package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;
import business.entities.User;
import model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper
{
    private Database database;

    public UserMapper(Database database)
    {
        this.database = database;
    }

    public User login(String email, String password) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "SELECT id, role, first_name, last_name, phone_number FROM users WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    String role = rs.getString("role");
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String phoneNumber = rs.getString("phone_number");
                    User user = new User(email, password, role,firstName,lastName,phoneNumber);
                    user.setId(id);
                    return user;
                } else
                {
                    throw new UserException("Could not validate user");
                }
            }
            catch (SQLException ex)
            {
                throw new UserException(ex.getMessage());
            }
        }
        catch (SQLException ex)
        {
            throw new UserException("Connection to database could not be established");
        }
    }
    public void createUser(User user) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO users (email, password, role, address, zipcode, phone_number, first_name, last_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.setString(4, user.getAddress());
                ps.setString(5, user.getZipcode());
                ps.setString(6, user.getPhoneNumber());
                ps.setString(7, user.getFirstName());
                ps.setString(8, user.getLastName());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
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

//    public User getUserByUserId(int Id) throws UserException {
//        User user = null;
//        try (Connection connection = database.connect()) {
//            String sql = "SELECT * FROM `users` WHERE `id`=?";
//
//            try (PreparedStatement ps = connection.prepareStatement(sql)) {
//                ps.setInt(1, Id);
//                ResultSet rs = ps.executeQuery();
//                while (rs.next()) {
//                    int requestId = rs.getInt("request_id");
//                    int user_id = rs.getInt("user_id");
//                    int length = rs.getInt("length");
//                    int height = rs.getInt("height");
//                    int width = rs.getInt("width");
//                    String roofType = rs.getString("roofType");
//                    Timestamp dateTime = rs.getTimestamp("date_time");
//                    int status = rs.getInt("order_status");
//                    double price = rs.getDouble("price");
//                    String description = rs.getString("description");
//                    request = new Request(requestId, user_id, length, width, roofType, dateTime, height, status, price, description);
//                }
//            }
//        } catch (SQLException ex) {
//            throw new UserException(ex.getMessage());
//        }
//        return user;
//    }


}
