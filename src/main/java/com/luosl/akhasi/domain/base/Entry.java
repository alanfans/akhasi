package com.luosl.akhasi.domain.base;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.*;

/**
 *
 * Created by Administrator on 2016/5/23.
 */
public class Entry implements Serializable{

    private static final Map<Class,Map<String,PropertyDescriptor>> FILED_MAP = new HashMap<Class,Map<String,PropertyDescriptor>>();
    private static final Map<Class,Object> INSTANCE_MAP = new HashMap<Class, Object>();

    @SuppressWarnings("unchecked")
    public static <T extends Entry> T getInstance(Class<T> clazz){
        T t = (T) INSTANCE_MAP.get(clazz);
        if(null==t){
            try {
                t = clazz.newInstance();
            } catch (Exception e) {
                throw new EntryExeption(clazz.getName()+"没有公开的无参构造方法");
            }
            INSTANCE_MAP.put(clazz,t);
        }
        return t;
    }

    public String _idFieldName(){
        return "id";
    }

    public String _tableName(){
        String clazzName = this.getClass().getSimpleName();
        String first = clazzName.charAt(0)+"";
        return "tb_"+this.getClass().getSimpleName().replace(first,first.toLowerCase());
    }

    public Map<String,PropertyDescriptor> _genAllField() throws EntryExeption {
        Map<String,PropertyDescriptor> allField = new HashMap<String, PropertyDescriptor>();
        PropertyDescriptor[] pds;
        try {
            pds = Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors();
        } catch (IntrospectionException e) {
            throw new EntryExeption("不是标准的javabean",e);
        }
        Set<String> filter = new HashSet<String>();
        filter.add("class");
        if(null!= _notSqlField()){
            for(String s: _notSqlField().split(",|，")){
                filter.add(s);
            }
        }
        for (PropertyDescriptor pd:pds){
            if(filter.contains(pd.getName())){
                continue;
            }
            allField.put(pd.getName(),pd);
        }
        return allField;
    }

    public Map<String,PropertyDescriptor> _allField() throws EntryExeption {
        Map<String,PropertyDescriptor> af = FILED_MAP.get(this.getClass());
        if(null == af){
            af = _genAllField();
            System.out.println("----生成字段----");
            FILED_MAP.put(this.getClass(),af);
        }
        return af;
    }

    public String _notSqlField(){
        return null;
    }

    public Object _value(String fieldName) throws EntryExeption {
        try {
            return _allField().get(fieldName).getReadMethod().invoke(this);
        } catch (Exception e) {
            throw new EntryExeption("不是标准的javabean，无法获取"+fieldName+"的read方法",e);
        }
    }

    public Object _idValue() throws EntryExeption {
        return _value(_idFieldName());
    }

}
