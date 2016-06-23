package com.luosl.akhasi.domain.base;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/5/23.
 */
public class EntryDao<T extends Entry> extends Dao{

    private Class<T> clazz;
    private T instance;

    public EntryDao(Class<T> clazz, JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.clazz = clazz;
        instance = Entry.getInstance(clazz);
    }

    public T findSingle(String sql, Object ...params){
        List<T> res = findList(genSql(sql),params);
        if(res.size()>0){
            return res.get(0);
        }else {
            return null;
        }
    }

    public List<T> findAll(){
        return findList("select * from tbl");
    }

    public List<T> findList(String sql, Object ...params){
        return jdbcTemplate.query(genSql(sql),params,new BeanPropertyRowMapper<T>(clazz));
    }

    public  Pager<T> findByPage(int currentPage,String sql,Object ...params){
        return findByPage(currentPage,10,sql,params);
    }

    public  Pager<T> findByPage(int currentPage,int pageSize,String sql,Object ...params) {
        StringBuilder sqlBuilder = new StringBuilder(sql);
        Pager<T> pager = new Pager<T>();
        int firstRuslt = pageSize * (currentPage - 1);
        List<T> dataList = findByRange(firstRuslt,pageSize,sql,params);
        long count = getAllCount(sqlBuilder.toString(),params);
        pager.setDataList(dataList);
        pager.setCurrentPage(currentPage);
        pager.setPageSize(pageSize);
        pager.setTotalCount((int) count);
        return pager;
    }

    public List<T> findByRange(int start,int num,String sql,Object ...params) {
        StringBuilder sqlBuilder = new StringBuilder(sql)
                .append(" limit ").append(start)
                .append(",").append(num);
        return findList(sqlBuilder.toString(),params);
    }

    public long getAllCount(String sql,Object ...params) {
        if (sql.toLowerCase().trim().startsWith("from")) {
            sql = "select count(*) " + sql;
        } else {
            sql = "select count(*) " + sql.replaceFirst("select[\\s\\S]*from", "from") + " ";
        }
        return jdbcTemplate.queryForObject(genSql(sql),params,Long.class);
    }

    public T findById(Serializable id){
        StringBuilder sql = new StringBuilder("select * from ")
                .append(instance._tableName())
                .append(" where ")
                .append(instance._idFieldName())
                .append(" = ?");
        return findSingle(sql.toString(),id);
    }

    public void delete(T t) throws EntryExeption {
        deleteById((Serializable) t._idValue());
    }

    public void update(String sql,Object ...params){
        jdbcTemplate.update(genSql(sql),params);
    }

    public void deleteById(Serializable id) throws EntryExeption {
        StringBuilder sql = new StringBuilder("delete from ")
                .append(instance._tableName()).append(" where ")
                .append(instance._idFieldName()).append(" = ?");
        update(sql.toString(),id);
    }

    private String genSql(String sql){
        return sql.replaceFirst("tbl",instance._tableName());
    }
}
