package business.persistence;

import business.entities.Material;
import business.exceptions.UserException;
import model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper
{
    private Database database;

    public RequestMapper(Database database)
    {
        this.database = database;
    }


    public List<Request> getRequestById(int id) throws UserException {
        List<Request> requestList = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `carport_request` WHERE `user_id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (requestList == null) {
                        requestList = new ArrayList<>();
                    }

                    int requestId = rs.getInt("request_id");
                    int user_id = rs.getInt("user_id");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    int width = rs.getInt("width");
                    String roofType = rs.getString("roofType");
                    Timestamp dateTime = rs.getTimestamp("date_time");
                    int status = rs.getInt("order_status");
                    double price = rs.getDouble("price");
                    Request request = new Request(requestId, user_id, length, width, roofType, dateTime, height, status, price);
                    requestList.add(request);

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return requestList;
    }

    public void addPrice(int reqId, double price) throws UserException
    {
        try (Connection connection = database.connect())
        {
            String sql = "UPDATE carport_request SET price = ? WHERE `request_id` = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                ps.setDouble(1, price);
                ps.setInt(2, reqId);
                ps.executeUpdate();
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

    public List<Request> getAllRequests() throws UserException {
        List<Request> requestList = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `carport_request`";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (requestList == null) {
                        requestList = new ArrayList<>();
                    }
                    int requestId = rs.getInt("request_id");
                    int user_id = rs.getInt("user_id");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    int width = rs.getInt("width");
                    String roofType = rs.getString("roofType");
                    Timestamp dateTime = rs.getTimestamp("date_time");
                    int status = rs.getInt("order_status");
                    double price = rs.getDouble("price");
                    Request request = new Request(requestId, user_id, length, width, roofType, dateTime, height, status, price);
                    requestList.add(request);

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return requestList;


    }
}
