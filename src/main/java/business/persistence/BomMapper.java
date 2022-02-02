package business.persistence;

import business.exceptions.UserException;
import model.BomMaterial;
import model.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BomMapper
{
    private Database database;

    public BomMapper(Database database)
    {
        this.database = database;
    }




    public void createBom(List<BomMaterial> bomlist, int reqId) throws UserException
    {
        try (Statement stmt = database.connect().createStatement()){

            String sql = "DELETE FROM bom where request_id = " + reqId + ";";

            stmt.execute(sql);


        } catch (Exception e){

        }
        try (Connection connection = database.connect())
        {
            String sql = "INSERT INTO bom (request_id, material_id, count) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
            {
                for (int i = 0; i < bomlist.size(); i++) {
                    ps.setInt(1, reqId);
                    ps.setInt(2, bomlist.get(i).getMaterialId());
                    ps.setInt(3, bomlist.get(i).getCount());

                    ps.executeUpdate();
                    ResultSet ids = ps.getGeneratedKeys();
                    ids.next();
                }
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

    public ArrayList<BomMaterial> getBom(int id) throws UserException { //getbom
        ArrayList<BomMaterial> bomMaterials = null;
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM `bom` WHERE `request_id`=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    if (bomMaterials == null) {
                        bomMaterials = new ArrayList<>();
                    }

                    int materialId = rs.getInt("material_id");
                    int count = rs.getInt("count");

                    BomMaterial bomMaterial = new BomMaterial(materialId, count);
                    bomMaterials.add(bomMaterial);

                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
        return bomMaterials;
    }
}
