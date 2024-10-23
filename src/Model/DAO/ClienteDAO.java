package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Cliente;
import Model.Repository.ClienteRepository;
import java.sql.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements ClienteRepository {

    @Override
    public Cliente crear(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni, edad, tipoMembresia, estadoPago) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getDni());
            preparedStatement.setInt(7, cliente.getEdad());
            preparedStatement.setString(8, cliente.getTipoMembresia());
            preparedStatement.setBoolean(9, cliente.isEstadoPago());

            preparedStatement.executeUpdate();

            // Obtener la clave generada automáticamente (idCliente)
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setIdCliente(generatedKeys.getInt(1));
            }
        }
        return cliente;
    }

    @Override
    public void modificar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre=?, apellido=?, direccion=?, telefono=?, email=?, dni=?, edad=?, tipoMembresia=?, estadoPago=? WHERE idCliente=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getDni());
            preparedStatement.setInt(7, cliente.getEdad());
            preparedStatement.setString(8, cliente.getTipoMembresia());
            preparedStatement.setBoolean(9, cliente.isEstadoPago());
            preparedStatement.setInt(10, cliente.getIdCliente());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idCliente) throws SQLException {
        String sql = "DELETE FROM clientes WHERE idCliente = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idCliente);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el cliente con id: " + idCliente);
            }
        }
    }

    @Override
    public void eliminarPorDNI(String dni) throws SQLException {
        String sql = "DELETE FROM clientes WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dni);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se encontró el cliente con DNI: " + dni);
            }
        }
    }

    @Override
    public List<Cliente> buscar(String dni) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(2, dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setDni(resultSet.getString("dni"));
                cliente.setEdad(resultSet.getInt("edad"));
                cliente.setTipoMembresia(resultSet.getString("tipoMembresia"));
                cliente.setEstadoPago(resultSet.getBoolean("estadoPago"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    @Override
    public List<Cliente> list() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setDni(resultSet.getString("dni"));
                cliente.setEdad(resultSet.getInt("edad"));
                cliente.setTipoMembresia(resultSet.getString("tipoMembresia"));
                cliente.setEstadoPago(resultSet.getBoolean("estadoPago"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar los clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return clientes;
    }
}









