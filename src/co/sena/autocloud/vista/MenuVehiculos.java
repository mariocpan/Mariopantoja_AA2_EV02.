package co.sena.autocloud.vista;

import co.sena.autocloud.dao.VehiculoDAO;
import co.sena.autocloud.modelo.Vehiculo;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuVehiculos {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static final VehiculoDAO VEHICULO_DAO = new VehiculoDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                ejecutarOpcion(opcion);
            } catch (SQLException excepcion) {
                System.out.println("Error: " + excepcion.getMessage());
            }
        } while (opcion != 0);
        System.out.println("Hasta pronto.");
    }

    private static void mostrarMenu() {
        System.out.println("=== AutoCloud Parking ===");
        System.out.println("1. Registrar vehiculo");
        System.out.println("2. Listar vehiculos");
        System.out.println("3. Buscar por placa");
        System.out.println("4. Actualizar vehiculo");
        System.out.println("5. Eliminar vehiculo");
        System.out.println("0. Salir");
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String texto = ENTRADA.nextLine();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException error) {
            return -1;
        }
    }

    private static void ejecutarOpcion(int opcion) throws SQLException {
        switch (opcion) {
            case 1 -> registrarVehiculo();
            case 2 -> listarVehiculos();
            case 3 -> buscarVehiculo();
            case 4 -> actualizarVehiculo();
            case 5 -> eliminarVehiculo();
            case 0 -> { }
            default -> System.out.println("Opcion no valida.");
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return ENTRADA.nextLine().trim().toUpperCase();
    }

    private static void listarVehiculos() throws SQLException {
        List<Vehiculo> vehiculos = VEHICULO_DAO.consultarTodos();
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
        }
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo.obtenerDatos());
        }
    }

    private static void registrarVehiculo() throws SQLException {
        String placa = leerTexto("Placa: ");
        String tipoVehiculo = leerTexto("Tipo de vehiculo: ");
        Vehiculo vehiculo = new Vehiculo(placa, tipoVehiculo);
        if (VEHICULO_DAO.insertar(vehiculo)) {
            System.out.println("Registro exitoso.");
        }
    }

    private static void buscarVehiculo() throws SQLException {
        String placa = leerTexto("Placa a buscar: ");
        Vehiculo vehiculo = VEHICULO_DAO.consultarPorPlaca(placa);
        if (vehiculo == null) {
            System.out.println("No se encontro vehiculo.");
        } else {
            System.out.println(vehiculo.obtenerDatos());
        }
    }

    private static void actualizarVehiculo() throws SQLException {
        String placa = leerTexto("Placa del vehiculo a actualizar: ");
        String tipo = leerTexto("Nuevo tipo de vehiculo: ");
        Vehiculo vehiculo = new Vehiculo(placa, tipo);
        if (VEHICULO_DAO.actualizar(vehiculo)) {
            System.out.println("Vehiculo actualizado.");
        } else {
            System.out.println("No se encontro vehiculo.");
        }
    }

    private static void eliminarVehiculo() throws SQLException {
        String placa = leerTexto("Placa del vehiculo a eliminar: ");
        if (VEHICULO_DAO.eliminar(placa)) {
            System.out.println("Vehiculo eliminado.");
        } else {
            System.out.println("No se encontro el vehiculo.");
        }
    }
}