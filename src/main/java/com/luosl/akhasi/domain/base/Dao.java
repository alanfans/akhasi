package com.luosl.akhasi.domain.base;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * Created by Administrator on 2016/6/7.
 */
public class Dao {

    protected JdbcTemplate jdbcTemplate;

    public Dao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <V extends Entry> Integer save(V v) throws EntryExeption {
        StringBuilder sql = new StringBuilder("insert into ").append(v._tableName()).append("(");
        int count = 0;
        Set<String> fs = v._allField().keySet();
        Object[] params = new Object[fs.size()];
        for(String fName:fs){
            params[count] = v._value(fName); //参数赋值
            sql.append(fName);
            if(count<fs.size()-1){
                sql.append(",");
            }
            count++;
        }
        sql.append(") values(");
        for(int i=1;i<=count;i++){
            sql.append("?");
            if(i!=count){
                sql.append(",");
            }
        }
        sql.append(")");
        update(sql.toString(),params);
        return findObject("SELECT LAST_INSERT_ID()",Integer.class);
    }

    public <V extends Entry>void update(V v) throws EntryExeption {
        StringBuilder sql = new StringBuilder("update ")
                .append(v._tableName());
        int count = 0;
        Set<String> fs = v._allField().keySet();
        Object[] params = new Object[fs.size()];
        for(String fName:fs){
            params[count] = v._value(fName); //参数赋值
            if(count==0){
                sql.append(" set ");
            }
            sql.append(fName).append(" =? ");
            if(count<fs.size()-1){
                sql.append(",");
            }
            count++;
        }
        sql.append("where ").append(v._idFieldName()).append(" = ? ");
        update(sql.toString(), ArrayUtils.add(params,v._idValue()));
    }

    public List<DataTable> find(String sql, Object ...params){
        return this.jdbcTemplate.query(sql,params,new DataTableRowMapper());
    }

    public <V extends Entry> Integer save(DataTable dataTable, Class<V> clazz) {
        V v = Entry.getInstance(clazz);
        Map<String, PropertyDescriptor> af = v._allField();
        for(Map.Entry<String, PropertyDescriptor> e:af.entrySet()){
            try {
                e.getValue().getWriteMethod().invoke(v,dataTable.get(e.getKey()));
            } catch (Exception ex) {
                throw new DataAccessException("无法注入参数",ex);
            }
        }
        return save(v);
    }

    public <T> T findObject(String sql, Class<T> clazz, Object ...params){
        return jdbcTemplate.queryForObject(sql,clazz,params);
    }

    public void update(String sql,Object ...params){
        jdbcTemplate.update(sql,params);
    }

}
