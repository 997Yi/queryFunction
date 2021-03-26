package com.wwq.util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class JdbcUtil {
    private JdbcUtil(){}

    private static DataSource dataSource = null;

    static{
        try {
            //读取数据库资源文件
            Properties properties = new Properties();
            properties.load(JdbcUtil.class.getResourceAsStream("/dataSource.properties"));

            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setDriverClassName(properties.getProperty("db.driver"));
            druidDataSource.setUrl(properties.getProperty("db.url"));
            druidDataSource.setUsername(properties.getProperty("db.username"));
            druidDataSource.setPassword(properties.getProperty("db.password"));

            dataSource = druidDataSource;
        } catch (IOException e) {
            System.out.println("读取dataSource.properties配置文件失败! ");
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet){
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Connection connection, Statement statement){
        try {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static int update(String sql){
        Connection connection = null;
        Statement statement = null;
        int i = 0;
        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            i = statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, statement);
        }
        return i;
    }

    public static <T> List<T> query(String sql, T t){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        List<T> list = null;

        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            list = TransformUtil.transform(rs, t);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(connection, statement);
            JdbcUtil.close(rs);
        }
        return list;
    }


}
