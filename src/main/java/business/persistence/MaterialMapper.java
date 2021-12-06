package business.persistence;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialMapper {

        private Database database;

        public MaterialMapper(Database database)
        {
            this.database = database;
        }

        public Material importAllMaterials(String material_id) throws UserException
        {
            try (Connection connection = database.connect())
            {
                String sql = "SELECT materiale_id, length FROM materiale WHERE price=? AND unit=?";

                try (PreparedStatement ps = connection.prepareStatement(sql))
                {
                    ps.setString(1, price);
                    ps.setString(2, unit);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next())
                    {
                        String role = rs.getString("unit");
                        Double price = rs.getDouble("price");
          //              User user = new User(email, password, role);
                        Material material = new Material(344,"stk");
                        material.setId(id);
                        return material;
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
}
