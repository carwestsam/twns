package org.nightschool.dao;

import ch.qos.logback.core.db.DriverManagerConnectionSource;

import java.sql.*;

public class JDBCService
{
    public ResultSet select(String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Class.forName( "org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
            /*
            *  JDBC 的方式来连接数据库
            *  这里的 postgresql 是数据库的驱动程序,需要导入
            *  然后是 url/database
            * */
                "jdbc:postgresql://localhost/shopping_mall",
                "twer", // 用户名
                "123456"/*密码*/  ); // 创建一个数据库链接,会抛出SQLException异常
        ResultSet resultSet = connection.createStatement().executeQuery(sql);
        connection.close();
        return resultSet;
    }

    public boolean insert(String sql) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException
    {
        Class.forName( "org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
            /*
            *  JDBC 的方式来连接数据库
            *  这里的 postgresql 是数据库的驱动程序,需要导入
            *  然后是 url/database
            * */
                "jdbc:postgresql://localhost/shopping_mall",
                "twer", // 用户名
                "123456"/*密码*/  ); // 创建一个数据库链接,会抛出SQLException异常
        connection.createStatement().executeUpdate(sql);
        connection.close();
        return true;
    }


}
