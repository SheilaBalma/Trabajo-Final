package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Cliente;
import Model.Entity.Empleado;
import Model.Repository.EmpleadoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements EmpleadoRepository {

    public boolean ExisteDni(String dni) throws SQLException {
        String query = "SELECT COUNT(*) FROM empleados WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0;
        }
    }

    // Método para agregar un empleado a la base de datos
    public Empleado crear(Empleado empleado) throws SQLException {

        String sql = "INSERT INTO empleados (nombre, apellido, dirección, telefono, email, dni) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDirección());
            preparedStatement.setString(4, empleado.getTelefono());
            preparedStatement.setString(5,empleado.getEmail());
            preparedStatement.setString(6, empleado.getDni());
            preparedStatement.executeUpdate();

        }
        return empleado;
    }
    // Método para modificar un empleado existente en la base de datos
    @Override
    public void modificarEmpleado(Empleado empleado) throws SQLException {
        String sql = "UPDATE empleados SET nombre=?, apellido=?, dirección=?, telefono=?, email=?, dni=? WHERE  dni=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getApellido());
            preparedStatement.setString(3, empleado.getDirección());
            preparedStatement.setString(4, empleado.getTelefono());
            preparedStatement.setString(5, empleado.getEmail());
            preparedStatement.setString(6, empleado.getDni());

            // El último parámetro es el que busca el empleado por su DNI
            preparedStatement.setString(7, empleado.getDni());  // Usamos el DNI como clave

            preparedStatement.executeUpdate();  // Ejecutar la consulta
        }
    }

    // Método para buscar empleados por dni
    @Override
    public List<Empleado> buscarEmpleado(String dni) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE dni = ?";
        List<Empleado> empleados = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dni);  // Se establece el DNI en la consulta
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setApellido(resultSet.getString("apellido"));
                empleado.setDirección(resultSet.getString("dirección"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setEmail(resultSet.getString("email"));
                empleado.setDni(resultSet.getString("dni"));
                empleados.add(empleado);
            }
        }
        return empleados;
    }


    // Método para eliminar un empleado por dni
    @Override
    public void eliminarEmpleado(String dni) throws SQLException {
        String sql = "DELETE FROM empleados WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dni);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el empleado con DNI: " + dni);
            }
        }
    }

    // Método para listar todos los empleados
    public List<Empleado> listarEmpleados() {
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
                        resultSet.getString("email"),
                        resultSet.getString("dni")
                );
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
