package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Empleado;
import Model.Repository.EmpleadoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements EmpleadoRepository {

    // Método para agregar un empleado a la base de datos
    public boolean agregarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO empleados (nombre, apellido, dirección, telefono, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDirección());
            preparedStatement.setString(4, empleado.getTelefono());
            preparedStatement.setString(5, empleado.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Método para modificar un empleado existente en la base de datos
    public boolean modificarEmpleado(Empleado empleado) throws SQLException {
        String query = "UPDATE empleados SET dirección = ?, telefono = ?, email = ? WHERE nombre = ? AND apellido = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, empleado.getDirección());
            preparedStatement.setString(2, empleado.getTelefono());
            preparedStatement.setString(3, empleado.getEmail());
            preparedStatement.setString(4, empleado.getNombre());
            preparedStatement.setString(5, empleado.getApellido());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Método para buscar empleados por nombre y apellido
    public List<Empleado> buscarEmpleados(String nombre, String apellido) throws SQLException {
        String query = "SELECT * FROM empleados WHERE nombre = ? AND apellido = ?";
        List<Empleado> empleados = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Empleado empleado = new Empleado(
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("dirección"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email")
                );
                empleados.add(empleado);
            }
        }
        return empleados;
    }

    // Método para eliminar un empleado por nombre y apellido
    public boolean eliminarEmpleado(String nombre, String apellido) throws SQLException {
        String query = "DELETE FROM empleados WHERE nombre = ? AND apellido = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Método para listar todos los empleados
    @Override
    public List<Empleado> listarEmpleados() throws SQLException {
        String query = "SELECT * FROM empleados";
        List<Empleado> empleados = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Empleado empleado = new Empleado(
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("dirección"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email")
                );
                empleados.add(empleado);
            }
        }
        return empleados;
    }
}
