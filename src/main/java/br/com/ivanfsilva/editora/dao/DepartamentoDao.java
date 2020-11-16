package br.com.ivanfsilva.editora.dao;

import br.com.ivanfsilva.editora.entity.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DepartamentoDao extends GenericDao<Departamento> {

    @Autowired
    public DepartamentoDao(DataSource dataSource, Class Departamento) {
        super(dataSource, Departamento);
    }

    @Override
    public SqlParameterSource parameterSource(Departamento departamento) {
        return new BeanPropertySqlParameterSource(departamento);
    }

    public Departamento save(Departamento departamento) {
        Number key = save("departamentos", "id_departamento", parameterSource(departamento));
        departamento.setIdDepartamento(key.intValue());

        return departamento;
    }

    public int update(Departamento departamento) {
        String sql = " UPDATE departamentos " +
                " SET departamento = :departamento " +
                " WHERE id_departamento = :idDepartamento ";

        return update(sql, parameterSource(departamento));
    }

    public int delete(Integer id) {
        String sql = " DELETE FROM departamentos WHERE id_departamento = ? ";

        return delete(sql, id);
    }

    public Departamento findById(Integer id) {
        String sql = " SELECT * FROM departamentos WHERE id_departamento = ? ";

        return findById(sql, id);
    }

    public List<Departamento> findAll() {
        String sql = " SELECT * FROM departamentos ";

        return findAll(sql);
    }
}
