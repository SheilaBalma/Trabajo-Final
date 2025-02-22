package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Actividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActividadDAO {
    public void guardarActividad(Actividad actividad) {
        // Consulta SQL para insertar el tipo de actividad
        String query = "INSERT INTO actividades (tipo) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection(); // Establece la conexión
             PreparedStatement preparedStatement = connection.prepareStatement(query)) { // Prepara la consulta

            preparedStatement.setString(1, actividad.getTipo().name()); // Asigna el valor del tipo de actividad
            preparedStatement.executeUpdate(); // Ejecuta la inserción en la base de datos
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error en la inserción
            System.out.println("Error al guardar la actividad: " + e.getMessage());
        }
    }
}
