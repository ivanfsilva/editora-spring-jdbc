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

    public int getTotalPages(int size) {
        int count = dao.getJdbcTemplate()
                .queryForObject("SELECT count(*) FROM cargos", Integer.class);

        return (int) Math.ceil( new Double(count) / new Double(size) );
    }
    public List<Cargo> findByPage(int page, int size) {

        return dao.findByPage( (page - 1) * size, size);
    }
}
