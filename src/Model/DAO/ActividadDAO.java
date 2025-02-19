package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Actividad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActividadDAO {
    public void guardarActividad(Actividad actividad) {
        String query = "INSERT INTO actividades (tipo) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, actividad.getTipo().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar la actividad: " + e.getMessage());
        }
    }
}
