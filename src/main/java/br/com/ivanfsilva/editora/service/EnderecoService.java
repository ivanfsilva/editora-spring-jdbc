package br.com.ivanfsilva.editora.service;

import java.util.List;

import br.com.ivanfsilva.editora.dao.EnderecoDao;
import br.com.ivanfsilva.editora.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoDao dao;

    public Endereco save(Endereco endereco) {
        if (endereco.getIdEndereco() == null) {
            endereco = dao.save(endereco);
        } else {
            dao.update(endereco);
        }
        return endereco;
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public Endereco findById(Integer id) {
        return dao.findById(id);
    }

    public List<Endereco> findAll() {
        return dao.findAll();
    }
}
