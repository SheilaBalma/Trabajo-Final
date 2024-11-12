package Model.DAO;
import Model.Database.DatabaseConnection;
import Model.Entity.Empleado;
import Model.Repository.EmpleadoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements EmpleadoRepository {

    // Agregar un nuevo empleado
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

    // Modificar empleado basado en DNI (sin modificar idEmpleado)
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

    // Buscar empleado por DNI
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

    // Eliminar empleado por DNI
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

    // Listar todos los empleados
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



