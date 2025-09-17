package com.lms;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL =
        "jdbc:mysql://localhost:3306/online_lms?useSSL=false&serverTimezone=UTC";
    private static final String USER = "lms_user";   // <— from step 1
    private static final String PASSWORD = "lms_pass"; // <— from step 1

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
