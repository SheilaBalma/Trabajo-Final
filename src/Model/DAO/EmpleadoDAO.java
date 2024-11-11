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

    @Override
    public Empleado agregarEmpleado(Empleado empleado) throws SQLException {
        String query = "INSERT INTO empleados (nombre, apellido, direccion, telefono, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDireccion());
            preparedStatement.setString(4, empleado.getTelefono());
            preparedStatement.setString(5, empleado.getEmail());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return empleado;
            } else {
                throw new SQLException("No se pudo insertar el empleado.");
            }
        }
    }

    @Override
    public Empleado modificarEmpleado(Empleado empleado) throws SQLException {
        String query = "UPDATE empleados SET direccion = ?, telefono = ?, email = ? WHERE nombre = ? AND apellido = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, empleado.getDireccion());
            preparedStatement.setString(2, empleado.getTelefono());
            preparedStatement.setString(3, empleado.getEmail());
            preparedStatement.setString(4, empleado.getNombre());
            preparedStatement.setString(5, empleado.getApellido());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return empleado;
            } else {
                throw new SQLException("No se pudo actualizar el empleado.");
            }
        }
    }

    @Override
    public List<Empleado> buscarEmpleados(String nombre, String apellido) throws SQLException {
        String query = "SELECT * FROM empleados WHERE nombre = ? AND apellido = ?";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Empleado empleado = new Empleado(
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("direccion"),
                            resultSet.getString("telefono"),
                            resultSet.getString("email")
                    );
                    empleados.add(empleado);
                }
            }
        }
        return empleados;
    }

    @Override
    public void eliminarEmpleado(String nombre, String apellido) throws SQLException {
        String query = "DELETE FROM empleados WHERE nombre = ? AND apellido = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el empleado para eliminar.");
            }
        }
    }

    @Override
    public List<Empleado> listarEmpleados() throws SQLException {
        String query = "SELECT * FROM empleados";
        List<Empleado> empleados = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Empleado empleado = new Empleado(
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email")
                );
                empleados.add(empleado);
            }
        }
        return empleados;
    }
    public boolean existeEmpleado(String nombre, String apellido) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM empleados WHERE nombre = ? AND apellido = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("count") > 0;
                }
            }
        }
        return false;
    }

}
