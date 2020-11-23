package br.com.ivanfsilva.editora.service;

import java.util.List;

import br.com.ivanfsilva.editora.dao.EnderecoDao;
import br.com.ivanfsilva.editora.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EnderecoService {

    @Autowired
    private EnderecoDao dao;

    @Transactional(rollbackFor = Exception.class)
    public Endereco saveOrUpdate(Endereco endereco) {
        if (endereco.getIdEndereco() == null) {
            dao.save(endereco);
        } else {
            dao.update(endereco);
        }
        return endereco;
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Endereco findById(Integer id) {
        return dao.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Endereco> findAll() {
        return dao.findAll();
    }
}