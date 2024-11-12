package Model.DAO;

import Model.Entity.Cliente;
import Model.Entity.Membresia;
import Model.Entity.Pago;
import Model.Repository.ClienteRepository;
import Model.Database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements ClienteRepository {

    // Crear un nuevo cliente
    @Override
    public Cliente crear(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni, edad, tipoMembresia, metodoPago, estadoPago) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDireccion());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getEmail());
            statement.setString(6, cliente.getDni());
            statement.setInt(7, cliente.getEdad());
            statement.setString(8, cliente.getTipoMembresia().name());
            statement.setString(9, cliente.getMetodoPago().name());
            statement.setBoolean(10, cliente.isEstadoPago());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    cliente.setIdCliente(generatedKeys.getInt(1));
                }
            }
            return cliente;
        }
    }

    // Modificar un cliente existente
    @Override
    public void modificar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, email = ?, edad = ?, tipoMembresia = ?, metodoPago = ?, estadoPago = ? WHERE dni = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getDireccion());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getEmail());
            statement.setInt(6, cliente.getEdad());
            statement.setString(7, cliente.getTipoMembresia().name());
            statement.setString(8, cliente.getMetodoPago().name());
            statement.setBoolean(9, cliente.isEstadoPago());
            statement.setString(10, cliente.getDni());

            statement.executeUpdate();
        }
    }

    // Eliminar un cliente por DNI
    @Override
    public void eliminarPorDNI(String dni) throws SQLException {
        String sql = "DELETE FROM clientes WHERE dni = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, dni);
            statement.executeUpdate();
        }
    }

    // Buscar un cliente por DNI
    @Override
    public List<Cliente> buscar(String dni) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
            while (resultSet.next()) {
                try {
                    // Convertir el String de la base de datos a un valor del enum correspondiente
                    Membresia.TipoMembresia tipoMembresia = Membresia.TipoMembresia.valueOf(resultSet.getString("tipoMembresia"));
                    Pago.MetodoPago metodoPago = Pago.MetodoPago.valueOf(resultSet.getString("metodoPago"));

                    // Crear el cliente con los valores obtenidos
                    Cliente cliente = new Cliente(
                            resultSet.getInt("idCliente"),
                            resultSet.getString("nombre"),
                            resultSet.getString("apellido"),
                            resultSet.getString("direccion"),
                            resultSet.getString("telefono"),
                            resultSet.getString("email"),
                            resultSet.getString("dni"),
                            resultSet.getInt("edad"),
                            tipoMembresia,  // Usamos el valor convertido de tipoMembresia
                            metodoPago,     // Usamos el valor convertido de metodoPago
                            resultSet.getBoolean("estadoPago")
                    );
                    clientes.add(cliente);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al convertir los valores de Enum: " + e.getMessage());
                    // Aquí puedes manejar el error si es necesario (por ejemplo, omitir el cliente o agregarlo con valores predeterminados)
                }
            }
            return clientes;
        }
    }

    // Listar todos los clientes
    @Override
    public List<Cliente> list() throws SQLException {
        String sql = "SELECT * FROM clientes";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                        resultSet.getInt("idCliente"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("dni"),
                        resultSet.getInt("edad"),
                        Membresia.TipoMembresia.valueOf(resultSet.getString("tipoMembresia")),
                        Pago.MetodoPago.valueOf(resultSet.getString("metodoPago")),
                        resultSet.getBoolean("estadoPago")
                );
                clientes.add(cliente);
            }
            return clientes;
        }
    }
    // Verificar si un cliente con un DNI específico existe
    public boolean validarExistenciaPorDNI(String dni) throws SQLException {
        String sql = "SELECT 1 FROM clientes WHERE dni = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Retorna true si el DNI existe, false si no
        }
    }
}
