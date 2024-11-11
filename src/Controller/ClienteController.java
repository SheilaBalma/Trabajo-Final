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

    public Cliente agregarCliente(Cliente cliente) throws SQLException {
        validarCliente(cliente);
        return clienteDAO.crear(cliente);
    }

    public Cliente modificarCliente(Cliente cliente) throws SQLException {
        validarCliente(cliente);
        clienteDAO.modificar(cliente);
        return cliente;
    }

    public void eliminarClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para eliminar.");
        }
        clienteDAO.eliminarPorDNI(dni);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.list();
    }

    public List<Cliente> buscarClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para buscar.");
        }
        return clienteDAO.buscar(dni);
    }

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

    public boolean existeClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para verificar la existencia.");
        }
        return clienteDAO.validarExistenciaPorDNI(dni);
    }
}



