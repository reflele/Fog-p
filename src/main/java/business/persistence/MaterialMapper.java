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
}
