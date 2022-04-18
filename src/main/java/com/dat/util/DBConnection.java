package com.dat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection connect() {
        String url = "jdbc:mysql://127.0.0.1:3306/employee_manager";
        String user = "root";
        String password = "123456a@";
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        Connection conn = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected successfully!!!");
        return conn;
    }
}
