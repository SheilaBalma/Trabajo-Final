package Model.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gym_management"; // Cambia 'tu_base_de_datos' por el nombre de tu base de datos
    private static final String USER = "root"; // Cambia 'tu_usuario' por tu usuario de MySQL
    private static final String PASSWORD = "santyggz"; // Cambia 'tu_contraseña' por tu contraseña de MySQL

    private static Connection connection;

    // Método para establecer la conexión con la base de datos
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Error al conectar a la base de datos");
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
