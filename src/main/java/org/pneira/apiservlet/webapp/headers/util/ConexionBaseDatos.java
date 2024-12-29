package org.pneira.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/java_curso"; // ?serverTimezone=Europe/Berlin
    private static String user="root";
    private static String pass = "root";


    public static Connection getCoonectionPeter() throws SQLException {
        return DriverManager.getConnection(url,user, pass);
    }
}
