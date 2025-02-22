package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Membresia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembresiaDAO {
    public void guardarMembresia(Membresia membresia) {
        // Consulta SQL para insertar el tipo de actividad
        String query = "INSERT INTO membresias (tipo, descripcion, limiteAcceso) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();// Establece la conexión
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {// Prepara la consulta

            preparedStatement.setString(1, membresia.getTipo().name());
            preparedStatement.setString(2, membresia.getDescripcion());
            preparedStatement.setInt(3, membresia.getLimiteAcceso());
            preparedStatement.executeUpdate(); // Ejecuta la inserción en la base de datos

        } catch (SQLException e) {
            // Manejo de excepciones en caso de error en la inserción
            System.out.println("Error al guardar la membresía: " + e.getMessage());
        }
    }
}

