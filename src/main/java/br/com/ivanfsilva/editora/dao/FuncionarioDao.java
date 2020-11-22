package br.com.ivanfsilva.editora.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import br.com.ivanfsilva.editora.entity.Cargo;
import br.com.ivanfsilva.editora.entity.Endereco;
import br.com.ivanfsilva.editora.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class FuncionarioDao extends GenericDao<Funcionario> {

    private CargoDao cargoDao;

    private EnderecoDao enderecoDao;

    @Autowired
    public FuncionarioDao(DataSource dataSource, CargoDao cargoDao, EnderecoDao enderecoDao) {
        super(dataSource, Funcionario.class);
        this.cargoDao = cargoDao;
        this.enderecoDao = enderecoDao;
    }

    @Override
    protected SqlParameterSource parameterSource(Funcionario funcionario) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("nome", funcionario.getNome());
        source.addValue("salario", funcionario.getSalario());
        source.addValue("dataEntrada", java.sql.Date.valueOf(funcionario.getDataEntrada()));
        source.addValue("idFuncionario", funcionario.getIdFuncionario());
        source.addValue("idCargo", funcionario.getCargo().getIdCargo());
        source.addValue("idEndereco", funcionario.getEndereco().getIdEndereco());

        if (funcionario.getDataSaida() != null) {
            source.addValue("dataSaida", java.sql.Date.valueOf(funcionario.getDataSaida()));
        }

        return source;
    }

    @Override
    protected RowMapper<Funcionario> rowMapper() {

        return new RowMapper<Funcionario>() {

            public Funcionario mapRow(ResultSet rs, int rowNum)	throws SQLException {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("ID_FUNCIONARIO"));
                funcionario.setNome(rs.getString("NOME"));
                funcionario.setSalario(rs.getDouble("SALARIO"));
                funcionario.setDataEntrada(rs.getDate("DATA_ENTRADA").toLocalDate());

                if (rs.getDate("DATA_SAIDA") != null) {
                    funcionario.setDataSaida(rs.getDate("DATA_SAIDA").toLocalDate());
                }

                Endereco endereco = enderecoDao.findById(rs.getInt("ID_ENDERECO"));
                funcionario.setEndereco(endereco);

                Cargo cargo = cargoDao.findById(rs.getInt("ID_CARGO"));
                funcionario.setCargo(cargo);

                return funcionario;
            }
        };
    }

    public Funcionario save(Funcionario funcionario) {
        Number key = super.save("FUNCIONARIOS", "ID_FUNCIONARIO", parameterSource(funcionario));
        funcionario.setIdFuncionario(key.intValue());
        return funcionario;
    }

    public int  update(Funcionario funcionario) {
        String sql = "UPDATE funcionario "
                + "SET nome = :nome, salario = :salario, id_cargo :idCargo, "
                + "id_endereco = :idEndereco, data_entrada = : dataEntrada, "
                + "data_saida = :dataSaida "
                + "WHERE id_funcionario = :idFuncionario";
        return super.update(sql, parameterSource(funcionario));
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario = ?";
        return super.delete(sql, id);
    }

    public Funcionario findById(Integer id) {
        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";
        return super.findById(sql, id, rowMapper());
    }

    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionarios";
        return super.findAll(sql, rowMapper());
    }
}
