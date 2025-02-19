package Model.DAO;

import Model.Database.DatabaseConnection;
import Model.Entity.Membresia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MembresiaDAO {
    public void guardarMembresia(Membresia membresia) {
        String query = "INSERT INTO membresias (tipo, descripcion, limiteAcceso) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, membresia.getTipo().name());
            preparedStatement.setString(2, membresia.getDescripcion());
            preparedStatement.setInt(3, membresia.getLimiteAcceso());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar la membresía: " + e.getMessage());
        }
    }
}

