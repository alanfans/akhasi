package com.luosl.akhasi.domain.base;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23.
 */
@Component
public class DaoContext {
    private Map<Class,EntryDao> daoMap = new HashMap<Class, EntryDao>();
    private Dao defaultDao;

    @Resource private JdbcTemplate jdbcTemplate;

    @SuppressWarnings("unchecked")
    public <T extends Entry> EntryDao<T> getDao(Class<T> clazz) {
        EntryDao<T> dao = daoMap.get(clazz);
        if(null == dao){
            dao = new EntryDao<T>(clazz,jdbcTemplate);
            daoMap.put(clazz,dao);
        }
        return dao;
    }

    public Dao getDefaultDao(){
        if(null==defaultDao){
            defaultDao = new Dao(jdbcTemplate);
        }
        return defaultDao;
    }
}
