package Main;

import Model.DAO.ClienteDAO;
import Model.Entity.Cliente;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear un ClienteDAO para realizar las operaciones en la base de datos
        ClienteDAO clienteDAO = new ClienteDAO();

        // Crear un cliente de prueba
        Cliente nuevoCliente = new Cliente(
                "Juan",  // nombre
                "Pérez",  // apellido
                "Calle Falsa 123",  // direccion
                "555-1234",  // telefono
                "juan@example.com",  // email
                1,  // idCliente
                30,  // edad
                "12345678",  // dni
                "Premium",  // tipoMembresia
                true,  // estadoPago
                List.of("Yoga", "Pilates")  // listaActividades
        );

        // Insertar el cliente en la base de datos
        clienteDAO.crear(nuevoCliente);

        // Aquí puedes agregar más pruebas para listar o buscar clientes
    }
}

