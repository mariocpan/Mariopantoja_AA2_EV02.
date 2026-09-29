package co.sena.autocloud.dao;

import co.sena.autocloud.modelo.Vehiculo;
import co.sena.autocloud.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    private static final String SQL_INSERTAR =
            "INSERT INTO vehiculo (id_placa, tipo_vehiculo) VALUES (?, ?)";
    private static final String SQL_CONSULTAR_TODOS =
            "SELECT id_placa, tipo_vehiculo FROM vehiculo ORDER BY id_placa";
    private static final String SQL_CONSULTAR_POR_PLACA =
            "SELECT id_placa, tipo_vehiculo FROM vehiculo WHERE id_placa = ?";
    private static final String SQL_ACTUALIZAR =
            "UPDATE vehiculo SET tipo_vehiculo = ? WHERE id_placa = ?";
    private static final String SQL_ELIMINAR =
            "DELETE FROM vehiculo WHERE id_placa = ?";

    public boolean insertar(Vehiculo vehiculo) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_INSERTAR)) {

            sentencia.setString(1, vehiculo.getIdPlaca());
            sentencia.setString(2, vehiculo.getTipoVehiculo());

            return sentencia.executeUpdate() > 0;
        }
    }

    public List<Vehiculo> consultarTodos() throws SQLException {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_CONSULTAR_TODOS);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                vehiculos.add(mapearVehiculo(resultado));
            }
        }
        return vehiculos;
    }

    public Vehiculo consultarPorPlaca(String idPlaca) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_CONSULTAR_POR_PLACA)) {

            sentencia.setString(1, idPlaca);
            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next() ? mapearVehiculo(resultado) : null;
            }
        }
    }

    public boolean actualizar(Vehiculo vehiculo) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_ACTUALIZAR)) {

            sentencia.setString(1, vehiculo.getTipoVehiculo());
            sentencia.setString(2, vehiculo.getIdPlaca());
            return sentencia.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String idPlaca) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(SQL_ELIMINAR)) {

            sentencia.setString(1, idPlaca);
            return sentencia.executeUpdate() > 0;
        }
    }

    private Vehiculo mapearVehiculo(ResultSet resultado) throws SQLException {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdPlaca(resultado.getString("id_placa"));
        vehiculo.setTipoVehiculo(resultado.getString("tipo_vehiculo"));
        return vehiculo;
    }
}
