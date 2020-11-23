package br.com.ivanfsilva.editora.service;

import br.com.ivanfsilva.editora.dao.DepartamentoDao;
import br.com.ivanfsilva.editora.entity.Departamento;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private static Logger logger = Logger.getLogger(DepartamentoService.class);

    @Autowired
    private DepartamentoDao dao;

    public Departamento save(Departamento departamento) {
        return dao.save(departamento);
    }

    public void update(Departamento departamento) {
        dao.update(departamento);
    }

    public void saveOrUpdate(Departamento departamento) {
        if ( departamento.getIdDepartamento() == null) {
            logger.info("Salvando um Departamento");
            dao.save(departamento);
            logger.info("Departamento" + departamento.getIdDepartamento() + " salvo com sucesso!");
        } else {
            logger.info("Alterando um Departamento");
            dao.update(departamento);
        }
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public Departamento findById(Integer id) {
        return dao.findById(id);
    }

    public List<Departamento> findAll() {
        return dao.findAll();
    }
}
