package Controller;

import Model.DAO.ClienteDAO;
import Model.Entity.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }

    // Método para agregar un cliente
    public Cliente agregarCliente(Cliente cliente) throws SQLException {
        validarCliente(cliente);
        return clienteDAO.crear(cliente);
    }

    // Método para modificar un cliente
    public Cliente modificarCliente(Cliente cliente) throws SQLException {
        validarCliente(cliente);
        clienteDAO.modificar(cliente);
        return cliente;
    }

    // Método para eliminar un cliente por DNI
    public void eliminarClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para eliminar.");
        }
        clienteDAO.eliminarPorDNI(dni);
    }

    // Método para listar todos los clientes
    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.list();
    }


    // Método para buscar un cliente por DNI (devuelve lista para casos múltiples)
    public List<Cliente> obtenerClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para buscar.");
        }
        return clienteDAO.buscar(dni); // Suponiendo que `buscar` devuelve una lista de clientes
    }


    // Validación de los datos del cliente
    private void validarCliente(Cliente cliente) {
        if (cliente.getDni() == null || cliente.getDni().isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (cliente.getApellido() == null || cliente.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
    }

    // Método para verificar si existe un cliente por DNI
    public boolean existeClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para verificar la existencia.");
        }
        return clienteDAO.validarExistenciaPorDNI(dni);
    }
}




