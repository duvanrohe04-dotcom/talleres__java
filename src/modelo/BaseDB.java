/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class BaseDB {
    private static final String URL = "jdbc:postgresql://178.238.238.248:5054/master_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "julyanna231101755878";
    private static Connection connection = null;
    private BaseDB() {}
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}