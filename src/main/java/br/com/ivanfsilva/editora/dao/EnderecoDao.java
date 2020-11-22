package br.com.ivanfsilva.editora.dao;

import br.com.ivanfsilva.editora.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EnderecoDao extends GenericDao<Endereco> {

    @Autowired
    public EnderecoDao(DataSource dataSource) {
        super(dataSource, Endereco.class);
    }

    @Override
    protected SqlParameterSource parameterSource(Endereco endereco) {
        return new BeanPropertySqlParameterSource(endereco);
    }

    @Override
    protected RowMapper<Endereco> rowMapper() {
        return new BeanPropertyRowMapper<Endereco>(Endereco.class);
    }

    public Endereco save(Endereco endereco) {
        Number key = super.save("enderecos", "id_endereco", parameterSource(endereco));

        endereco.setIdEndereco(key.intValue());
        return endereco;
    }

    public  int update(Endereco endereco) {
        String sql = "UPDATE enderecos SET logradouro = :logradouro, numero = :numero, " +
                " complemento = :complemento, estado = :estado, bairro = :bairro, " +
                " cidade = :cidade, cep = :cep " +
                " WHERE id_endereco = : idEndereco";
        return super.update(sql, parameterSource(endereco));
    }

    public int delete(Integer id) {
        String sql = " DELETE FROM enderecos WHERE id_endereco = ? ";
        return super.delete(sql, id);
    }

    public Endereco findById(Integer id) {
        String sql = "SELECT * FROM enderecos WHERE id_endereco = ? ";
        return super.findById(sql, id, rowMapper());
    }

    public List<Endereco> findAll() {
        String sql = "SELECT * FROM enderecos ";
        return super.findAll(sql, rowMapper());
    }
}
