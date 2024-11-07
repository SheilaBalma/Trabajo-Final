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

    public void agregarCliente(Cliente cliente) throws SQLException {
        clienteDAO.crear(cliente);
    }

    public void modificarCliente(Cliente cliente) throws SQLException {
        clienteDAO.modificar(cliente);
    }

    public void eliminarClientePorDNI(String dni) throws SQLException {
        clienteDAO.eliminarPorDNI(dni);
    }

    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.list();
    }

    public List<Cliente> buscarClientePorDNI(String dni) throws SQLException {
        return clienteDAO.buscar(dni);  // Llamamos al método 'buscar' de ClienteDAO
    }

    public boolean verificarExistenciaPorDNI(String dni) throws SQLException {
        return clienteDAO.buscarPorDNI(dni).size() > 0;
    }

}
