/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDB {

    private static final String DRIVER   = "com.mysql.cj.jdbc.Driver";
    private static final String URL      = "jdbc:mysql://178.238.238.248:3307/sql_sq";
    private static final String USUARIO  = "admin";
    private static final String CLAVE    = "mi_password";
    private static Connection conexionCache = null;

    public static Connection getConnection() throws SQLException {
        if (conexionCache != null && !conexionCache.isClosed()) {
            return conexionCache;
        }
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Driver no encontrado: " + ex.getMessage());
        }
        Properties props = new Properties();
        props.setProperty("user", USUARIO);
        props.setProperty("password", CLAVE);
        props.setProperty("connectTimeout", "2000");
        props.setProperty("socketTimeout", "2000");
        conexionCache = DriverManager.getConnection(URL, props);
        return conexionCache;
    }

    public static void inicializar() {
        String[] tablas = {
            "CREATE TABLE IF NOT EXISTS citas (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "cliente VARCHAR(100)," +
            "moto VARCHAR(100)," +
            "fecha VARCHAR(20)," +
            "estado VARCHAR(20))",
            "CREATE TABLE IF NOT EXISTS clientes (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "nombre VARCHAR(100)," +
            "documento VARCHAR(50)," +
            "direccion VARCHAR(200)," +
            "celular VARCHAR(20))",
            "CREATE TABLE IF NOT EXISTS motos (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "marca VARCHAR(50)," +
            "modelo VARCHAR(50)," +
            "placa VARCHAR(20)," +
            "color VARCHAR(30)," +
            "cliente VARCHAR(100))",
            "CREATE TABLE IF NOT EXISTS trabajadores (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "nombre VARCHAR(100)," +
            "cargo VARCHAR(50)," +
            "telefono VARCHAR(20)," +
            "email VARCHAR(100))"
        };
        try {
            Connection cn = getConnection();
            for (String sql : tablas) {
                try (var st = cn.createStatement()) {
                    st.execute(sql);
                }
            }
            System.out.println("Tablas verificadas/creadas correctamente.");
        } catch (SQLException ex) {
            System.err.println("Error al inicializar BD: " + ex.getMessage());
        }
    }
}