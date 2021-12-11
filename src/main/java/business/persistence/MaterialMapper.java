package business.persistence;

import business.entities.Material;
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

                    int material_id = rs.getInt("material_id");
                    String description = rs.getString("description");

                    Material material = new Material(material_id);
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


    public List<Material> getMaterialByCategory(String category) throws UserException {
        List<Material> materialList = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `material` WHERE `category`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, category);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (materialList == null) {
                        materialList = new ArrayList<>();
                    }
                    int material_id = rs.getInt("material_id");
                    String description = rs.getString("description");
  //                  String categoryDB = rs.getString("category");
                    int length = rs.getInt("length");
                    int height = rs.getInt("height");
                    int width = rs.getInt("width");
                    double price = rs.getDouble("price");
                    String unit = rs.getString("unit");
                    Material material = new Material(material_id, description, category, length, height, width, price, unit);
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