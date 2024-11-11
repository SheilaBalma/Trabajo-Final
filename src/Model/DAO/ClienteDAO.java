package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Cliente;
import Model.Entity.Membresia;
import Model.Repository.ClienteRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements ClienteRepository {

    @Override
    public Cliente crear(Cliente cliente) throws SQLException {
        if (validarExistenciaPorDNI(cliente.getDni())) {
            throw new SQLException("El cliente con DNI " + cliente.getDni() + " ya existe.");
        }

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
            preparedStatement.setString(8, cliente.getTipoMembresia().toString());
            preparedStatement.setBoolean(9, cliente.isEstadoPago());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setIdCliente(generatedKeys.getInt(1));
            }
        }
        return cliente;
    }

    @Override
    public void modificar(Cliente cliente) throws SQLException {
        // Verificar si el cliente con el DNI proporcionado existe
        if (!validarExistenciaPorDNI(cliente.getDni())) {
            throw new SQLException("Error: No se encontró un cliente con el DNI: " + cliente.getDni());
        }

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
            preparedStatement.setString(8, cliente.getTipoMembresia().toString());
            preparedStatement.setBoolean(9, cliente.isEstadoPago());
            preparedStatement.setInt(10, cliente.getIdCliente());
            preparedStatement.executeUpdate();
            System.out.println("Cliente actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
            throw e;
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
            } else {
                System.out.println("Cliente eliminado correctamente.");
            }
        }
    }

    @Override
    public List<Cliente> buscar(String dni) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = crearClienteDesdeResultSet(resultSet);
                clientes.add(cliente);
            }

            if (clientes.isEmpty()) {
                System.out.println("No se encontró ningún cliente con el DNI proporcionado.");
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
            throw e;
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
                Cliente cliente = crearClienteDesdeResultSet(resultSet);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los clientes: " + e.getMessage());
            throw e;
        }
        return clientes;
    }

    private Cliente crearClienteDesdeResultSet(ResultSet resultSet) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(resultSet.getInt("idCliente"));
        cliente.setNombre(resultSet.getString("nombre"));
        cliente.setApellido(resultSet.getString("apellido"));
        cliente.setDireccion(resultSet.getString("direccion"));
        cliente.setTelefono(resultSet.getString("telefono"));
        cliente.setEmail(resultSet.getString("email"));
        cliente.setDni(resultSet.getString("dni"));
        cliente.setEdad(resultSet.getInt("edad"));
        cliente.setTipoMembresia(Membresia.TipoMembresia.valueOf(resultSet.getString("tipoMembresia")));
        cliente.setEstadoPago(resultSet.getBoolean("estadoPago"));
        return cliente;
    }

    public boolean validarExistenciaPorDNI(String dni) throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE dni = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, dni);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }
}





