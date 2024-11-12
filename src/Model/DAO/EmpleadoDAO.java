package Model.DAO;
import Model.Database.DatabaseConnection;
import Model.Entity.Empleado;
import Model.Repository.EmpleadoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Data Access Object (DAO) para realizar operaciones de base de datos con empleados.
 */
public class EmpleadoDAO implements EmpleadoRepository {

    /**
     * Agrega un nuevo empleado a la base de datos.
     *
     * @param empleado El empleado a agregar.
     * @return El empleado agregado con su ID asignado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Override
    public Empleado agregarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO empleados (nombre, apellido, direccion, telefono, email, dni) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDireccion());
            preparedStatement.setString(4, empleado.getTelefono());
            preparedStatement.setString(5, empleado.getEmail());
            preparedStatement.setString(6, empleado.getDni());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idEmpleado = generatedKeys.getInt(1); // Obtener el ID generado
                        return new Empleado(idEmpleado, empleado.getNombre(), empleado.getApellido(),
                                empleado.getDireccion(), empleado.getTelefono(), empleado.getEmail(), empleado.getDni());
                    }
                }
                throw new SQLException("No se generó ID para el empleado.");
            } else {
                throw new SQLException("No se pudo insertar el empleado.");
            }
        }
    }

    /**
     * Modifica los datos de un empleado en la base de datos.
     *
     * @param empleado El empleado con los datos actualizados.
     * @return El empleado actualizado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Override
    public Empleado modificarEmpleado(Empleado empleado) throws SQLException {
        String query = "UPDATE empleados SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, email = ? WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDireccion());
            preparedStatement.setString(4, empleado.getTelefono());
            preparedStatement.setString(5, empleado.getEmail());
            preparedStatement.setString(6, empleado.getDni());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return empleado;
            } else {
                throw new SQLException("No se pudo actualizar el empleado.");
            }
        }
    }

    /**
     * Busca un empleado en la base de datos utilizando su DNI.
     *
     * @param dni El DNI del empleado a buscar.
     * @return El empleado encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Override
    public Empleado buscarEmpleadoPorDni(String dni) throws SQLException {
        String query = "SELECT * FROM empleados WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, dni);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Empleado(
                            resultSet.getInt("id_empleado"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("direccion"),
                            resultSet.getString("telefono"),
                            resultSet.getString("email"),
                            resultSet.getString("dni")
                    );
                }
                return null;
            }
        }
    }

    /**
     * Elimina un empleado de la base de datos utilizando su DNI.
     *
     * @param dni El DNI del empleado a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Override
    public void eliminarEmpleadoPorDni(String dni) throws SQLException {
        String query = "DELETE FROM empleados WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, dni);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el empleado con el DNI especificado.");
            }
        }
    }

    /**
     * Lista todos los empleados almacenados en la base de datos.
     *
     * @return Una lista de empleados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    @Override
    public List<Empleado> listarEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM empleados";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Empleado empleado = new Empleado(
                        resultSet.getInt("id_empleado"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("dni")
                );
                empleados.add(empleado);
            }
        }
        return empleados;
    }
}



