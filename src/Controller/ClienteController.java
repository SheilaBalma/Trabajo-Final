package Controller;

import Model.DAO.ClienteDAO;
import Model.Entity.Cliente;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void agregarCliente(Cliente cliente) {
        clienteDAO.crear(cliente);
    }

    public void modificarCliente(Cliente cliente) {
        clienteDAO.modificar(cliente);
    }

    public void eliminarCliente(int idCLiente) {
        clienteDAO.eliminar(idCLiente);
    }

    public List<Cliente> buscarClientes(String nombre, String dni) {
        return clienteDAO.buscar(nombre, dni);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.list();
    }
}



