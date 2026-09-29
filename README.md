cat > README.md << 'EOF'
# AutoCloud Parking 

El proyecto AutoCloud Parking, contiene
el CRUD (Crear, Leer, Actualizar, Eliminar) de vehiculos en Java, conectado a una
base de datos MySQL mediante JDBC.

## Estructura del proyecto

- `src/co/sena/autocloud/modelo/Vehiculo.java` - clase que representa un vehiculo
- `src/co/sena/autocloud/util/ConexionBD.java` - conexion JDBC a MySQL
- `src/co/sena/autocloud/dao/VehiculoDAO.java` - operaciones CRUD sobre la tabla vehiculo
- `src/co/sena/autocloud/vista/MenuVehiculos.java` - menu de consola para usar el modulo


## Autor

Mario Camilo Pantoja Gonzalez - Analisis y Desarrollo de Software
