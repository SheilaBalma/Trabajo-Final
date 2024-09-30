package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Cliente;
import Model.Repository.ClienteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO implements ClienteRepository {

    @Override
    public Cliente crear(Cliente cliente) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, email, dni, edad, tipoMembresia, estadoPago) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Obtener la conexión
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // Establecer los valores en la consulta
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getTelefono());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getDni());
            preparedStatement.setInt(7, cliente.getEdad());
            preparedStatement.setString(8, cliente.getTipoMembresia());
            preparedStatement.setBoolean(9, cliente.isEstadoPago());

            // Ejecutar la consulta
            preparedStatement.executeUpdate();
            System.out.println("Cliente creado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cliente; // Retorna el cliente que se acaba de crear
    }

    // Implementación de otros métodos...
    @Override
    public List<Cliente> list() {
        // Similarmente implementas la consulta SELECT
        return null;
    }

    @Override
    public List<Cliente> buscar(String nombre, String dni) {
        // Similarmente implementas la consulta SELECT con WHERE
        return null;
    }
}

