package business.services;

import business.entities.Material;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.MaterialMapper;
import business.persistence.UserMapper;

import java.util.List;

public class MaterialFacade {
    MaterialMapper materialMapper;

    public MaterialFacade(Database database) {
        materialMapper = new MaterialMapper(database);
    }

    public List<Material> getAllMaterials;

    public List<Material> getAllMaterials() throws UserException {
        return getAllMaterials();
    }

    public List<Material> getMaterialByCategory(String category) throws UserException {
        return materialMapper.getMaterialByCategory(category);
    }
}