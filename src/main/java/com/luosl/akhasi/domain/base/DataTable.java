package com.luosl.akhasi.domain.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/7.
 */
public class DataTable {

    private Map<String, Object> dbDataMap = new HashMap<String, Object>(5);

    public DataTable(Map<String, Object> dbDataMap) {
        this.dbDataMap = dbDataMap;
    }

    public DataTable(){}

    public <T> T get(String colName){
        return get(colName,null);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String colName,T defaultVal){
        return dbDataMap.containsKey(colName)?(T)dbDataMap.get(colName):defaultVal;
    }

    public void set(String key,Object value){
        dbDataMap.put(key,value);
    }

}
