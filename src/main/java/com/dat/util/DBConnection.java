package com.dat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static String url = "jdbc:mysql://127.0.0.1:3306/employee_manager";
    public static String user = "root";
    public static String password = "123456a@";
    public static String driverClassName = "com.mysql.cj.jdbc.Driver";

    public static Connection connect() {

        Connection conn = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
