package Controller;

import Model.DAO.ClienteDAO;
import Model.Entity.Cliente;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    // Agregar un cliente con validaciones previas
    public String agregarCliente(Cliente cliente) {
        if (cliente == null || cliente.getDni() == null || cliente.getDni().isEmpty()) {
            return "Error: El cliente o el DNI no pueden ser nulos o vacíos.";
        }

        try {
            clienteDAO.crear(cliente);
            return "Cliente creado exitosamente.";
        } catch (Exception e) {
            return "Error al crear el cliente: " + e.getMessage();
        }
    }

    // Modificar cliente con validaciones
    public String modificarCliente(Cliente cliente) {
        if (cliente == null || cliente.getDni() == null || cliente.getDni().isEmpty()) {
            return "Error: El cliente o el DNI no pueden ser nulos o vacíos.";
        }

        try {
            clienteDAO.modificar(cliente);
            return "Cliente modificado exitosamente.";
        } catch (Exception e) {
            return "Error al modificar el cliente: " + e.getMessage();
        }
    }

    // Eliminar cliente por DNI con manejo de errores
    public String eliminarClientePorDNI(String dni) {
        if (dni == null || dni.isEmpty()) {
            return "Error: El DNI no puede ser nulo o vacío.";
        }

        try {
            clienteDAO.eliminarPorDNI(dni);
            return "Cliente eliminado exitosamente.";
        } catch (Exception e) {
            return "Error al eliminar el cliente: " + e.getMessage();
        }
    }

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.list();
        } catch (Exception e) {
            System.err.println("Error al listar los clientes: " + e.getMessage());
            return null;
        }
    }

    // Buscar clientes por nombre o DNI
    public List<Cliente> buscarClientes(String nombre, String dni) {
        try {
            return clienteDAO.buscar(nombre, dni);
        } catch (Exception e) {
            System.err.println("Error al buscar los clientes: " + e.getMessage());
            return null;
        }
    }
}









