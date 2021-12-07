
//package business.persistence;
//
//import business.entities.Material;
//import business.entities.User;
//import business.exceptions.UserException;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class MaterialMapper {
//
//        private Database database;
//
//        public MaterialMapper(Database database)
//        {
//            this.database = database;
//        }
//
//        public Material importAllMaterials(String material_id) throws UserException
//        {
//            try (Connection connection = database.connect())
//            {
//                String sql = "SELECT materiale_id, length FROM materiale WHERE price=? AND unit=?";
//
//                try (PreparedStatement ps = connection.prepareStatement(sql))
//                {
//                    ps.setString(1, price);
//                    ps.setString(2, unit);
//                    ResultSet rs = ps.executeQuery();
//                    if (rs.next())
//                    {
//                        String role = rs.getString("unit");
//                        Double price = rs.getDouble("price");
//          //              User user = new User(email, password, role);
//                        Material material = new Material(344,"stk");
//                        material.setId(id);
//                        return material;
//                    } else
//                    {
//                        throw new UserException("Could not validate user");
//                    }
//                }
//                catch (SQLException ex)
//                {
//                    throw new UserException(ex.getMessage());
//                }
//            }
//            catch (SQLException ex)
//            {
//                throw new UserException("Connection to database could not be established");
//            }
//        }
//}

package business.persistence;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper {

    private Database database;

    public MaterialMapper(Database database) {
        this.database = database;
    }

    public List<Material> getAllMaterials(int id) throws UserException {
        List<Material> materialList = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * from material where material_id=?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (materialList == null) {
                        materialList = new ArrayList<>();
                    }

                    String material_id = rs.getString("material_id");
                    String short_description = rs.getString("short_description");
                    Material material = new Material(material_id, short_description);
                    materialList.add(material);

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return materialList;
    }

//    public Material getMaterialByDescription() {
//        // TODO skal laves en metode til at få materials ud baseret på deres description
//
//
//
//
//    }
}