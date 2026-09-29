package co.sena.autocloud.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexionBD {

private static final String URL =  "jdbc:mysql://localhost:3306/autocloudparking";
private static final String USUARIO = "root";
private static final String CLAVE =  "Autocloud2026*";

private ConexionBD(){

}

public static Connection obtenerConexion() throws SQLException {
    return DriverManager.getConnection(URL, USUARIO, CLAVE);
}

}