package br.com.ivanfsilva.editora.service;

import br.com.ivanfsilva.editora.dao.CargoDao;
import br.com.ivanfsilva.editora.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoDao dao;

    public void saveOrUpdate(Cargo cargo) {
        if (cargo.getIdCargo() == null) {
            dao.save(cargo);
        } else {
            dao.update(cargo);
        }
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public Cargo findById(Integer id) {
        return dao.findById(id);
    }

    public List<Cargo> findAll() {
        return dao.findAll();
    }
}
