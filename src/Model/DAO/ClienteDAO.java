package Model.DAO;

import Model.Entity.Cliente;
import Model.Repository.ClienteRepository;
import Model.Database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements ClienteRepository {

    @Override
    public Cliente crear(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni, edad, tipoMembresia, estadoPago) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros para el INSERT
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getDni());
            preparedStatement.setInt(7, cliente.getEdad());
            preparedStatement.setString(8, cliente.getTipoMembresia());
            preparedStatement.setBoolean(9, cliente.isEstadoPago());

            // Ejecutar el INSERT
            int affectedRows = preparedStatement.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setIdCliente(generatedKeys.getInt(1)); // Obtener el ID generado automáticamente
                        System.out.println("Cliente creado correctamente.");
                    }
                }
            } else {
                System.out.println("Error al crear el cliente. No se insertaron filas.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en la inserción del cliente.");
        }
        return cliente;
    }


    @Override
    public void modificar(Cliente cliente) {
        // Modificación basada en idCliente en lugar de dni para mayor fiabilidad
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, email = ?, edad = ?, tipoMembresia = ?, estadoPago = ? "
                + "WHERE idCliente = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (connection == null || connection.isClosed()) {
                throw new SQLException("Error: La conexión a la base de datos está cerrada o no es válida.");
            }

            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setInt(6, cliente.getEdad());
            preparedStatement.setString(7, cliente.getTipoMembresia());
            preparedStatement.setBoolean(8, cliente.isEstadoPago());
            preparedStatement.setInt(9, cliente.getIdCliente());  // Ahora basado en idCliente

            preparedStatement.executeUpdate();
            System.out.println("Cliente modificado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al modificar el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idCliente) {
        String sql = "DELETE FROM clientes WHERE idCliente = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idCliente);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cliente eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con el ID proporcionado.");
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> buscar(String nombre, String dni) {
        List<Cliente> clientes = new ArrayList<>();

        // Modificación para manejar búsqueda flexible con nombre y DNI
        String sql = "SELECT * FROM clientes WHERE (? = '' OR nombre LIKE ?) AND (? = '' OR dni = ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Si nombre o DNI no se proporcionan, se consideran vacíos
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, "%" + nombre + "%");
            preparedStatement.setString(3, dni);
            preparedStatement.setString(4, dni);

            // Ejecutar la consulta
            ResultSet resultSet = preparedStatement.executeQuery();

            // Procesar los resultados
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
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public List<Cliente> list() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (connection == null || connection.isClosed()) {
                throw new SQLException("Error: La conexión a la base de datos está cerrada o no es válida.");
            }

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




