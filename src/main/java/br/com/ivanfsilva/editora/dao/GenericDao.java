package br.com.ivanfsilva.editora.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

public abstract class GenericDao<T> extends JdbcDaoSupport {

    private Class aClass;

    public GenericDao(DataSource dataSource, Class aClass) {
        setDataSource( dataSource );
        this.aClass = aClass;
    }

    protected abstract SqlParameterSource parameterSource(T entity);

    protected abstract RowMapper<T> rowMapper();

    protected NamedParameterJdbcTemplate namedQuery() {
        return new NamedParameterJdbcTemplate(getDataSource());
    }

    protected Number save(String tableName, String key, SqlParameterSource parameterSource) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(getDataSource());
        insert.withTableName(tableName);
        insert.usingGeneratedKeyColumns(key);

        return insert.executeAndReturnKey(parameterSource);
    }

    protected int update(String sql, SqlParameterSource parameterSource) {
        return namedQuery().update(sql, parameterSource);
    }

    protected int delete(String sql, Integer id) {
        return getJdbcTemplate().update(sql, id);
    }

//    protected T findById(String sql, Integer id) {
//        return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<T>(aClass), id);
//    }

    protected T findById(String sql, Integer id, RowMapper<T> rowMapper) {
        return getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<T>(aClass), id);
    }

//    protected List<T> findAll(String sql) {
//        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<T>(aClass));
//    }

    protected List<T> findAll(String sql, RowMapper<T> rowMapper) {
        return getJdbcTemplate().query(sql, rowMapper);
    }
}
