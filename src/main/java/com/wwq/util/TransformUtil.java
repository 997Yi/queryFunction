package com.wwq.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransformUtil {
    //将resultSet封装到list中
    public static <T> List<T> transform(ResultSet rs, T t){
        List<T> list = new ArrayList<T>();
        //获取当前类
        Class<?> c = t.getClass();

        try{
            while (rs.next()) {
                T obj= (T)c.getDeclaredConstructor().newInstance();
                //获取当前类全部属性
                Field[] fields=c.getDeclaredFields();

                for(Field f:fields){
                    //获取当前属性的属性名子
                    String fname=f.getName();
                    //获取当前的属性的类型(简称)  java.lang.String
                    String type=f.getType().getSimpleName();

                    //在此方法名中要求类的属性名和数据库的字段名相同
                    Object value=null;
                    if(type.equalsIgnoreCase("string")){
                        value=rs.getString(fname);
                    }else if(type.equalsIgnoreCase("int")){
                        value=rs.getInt(fname);
                    }else if(type.equalsIgnoreCase("Integer")){
                        value=rs.getInt(fname);
                    }else if(type.equalsIgnoreCase("Double")){
                        value=rs.getDouble(fname);
                    }else if(type.equalsIgnoreCase("Float")){
                        value=rs.getFloat(fname);
                    }else if(type.equalsIgnoreCase("date")){
                        value=rs.getDate(fname);
                    }else if(type.equalsIgnoreCase("long")){
                        value=rs.getLong(fname);
                    }

                    //获取当前对象的所有set方法，并找到当前取出来的属性对应的set方法
                    Method[] methods=c.getDeclaredMethods();//获取所有的方法
                    for(Method m:methods){
                        //获取当前方法名
                        String methodName=m.getName();
                        //判断是不是当前属性
                        if(methodName.equalsIgnoreCase("set"+fname)){
                            //执行该方法
                            m.invoke(obj, value);
                        }
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
