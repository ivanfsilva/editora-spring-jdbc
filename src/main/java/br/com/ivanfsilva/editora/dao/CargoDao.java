package br.com.ivanfsilva.editora.dao;

import br.com.ivanfsilva.editora.entity.Cargo;
import br.com.ivanfsilva.editora.entity.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CargoDao extends GenericDao<Cargo> {

    @Autowired
    private DepartamentoDao departamentoDao;

    public CargoDao(DataSource dataSource) {
        super(dataSource, Cargo.class);
    }

    @Override
    protected SqlParameterSource parameterSource(Cargo cargo) {

        return new MapSqlParameterSource()
                .addValue("cargo", cargo.getCargo())
                .addValue("idDepartamento", cargo.getDepartamento().getIdDepartamento())
                .addValue("idCargo", cargo.getIdCargo());
    }

    @Override
    protected RowMapper<Cargo> rowMapper() {

        return new RowMapper<Cargo>() {

            public Cargo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Cargo cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("id_cargo"));
                cargo.setCargo(rs.getString("cargo"));

                Integer id = rs.getInt("id_cargo");

                Departamento departamento = departamentoDao.findById(id);
                cargo.setDepartamento(departamento);

                return cargo;
            }
        };
    }

    public Cargo save(Cargo cargo) {
        Number key = super.save("cargo", "id_cargo", parameterSource(cargo));
        cargo.setIdCargo(key.intValue());

        return cargo;
    }

    public int update(Cargo cargo) {
        String sql = "UPDATE cargos SET cargo = :cargo, id_departamento = :idDepartamento WHERE id_cargo = :idCargo";

        return super.update(sql,parameterSource(cargo));
    }

    public int delete(Integer id) {
        String sql = "DELETE FROM cargos WHERE id_cargo = ? ";

        return super.delete(sql, id);
    }

    public Cargo findById(Integer id) {
        String sql = " SELECT * FROM cargos WHERE id_cargo = ? ";

        return super.findById(sql, id, rowMapper());
    }

    public List<Cargo> findAll() {
        String sql = " SELECT * FROM cargos ";

        return super.findAll(sql, rowMapper());
    }
}
