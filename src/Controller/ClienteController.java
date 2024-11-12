package Controller;

import Model.DAO.ClienteDAO;
import Model.Entity.Cliente;

import java.sql.SQLException;
import java.util.List;
/**
 * Controlador para gestionar las acciones y eventos relacionados con los clientes.
 * Proporciona métodos para agregar, modificar, eliminar, listar y buscar clientes.
 */
public class ClienteController {
    /**
     * Controlador para gestionar las acciones y eventos relacionados con los clientes.
     * Proporciona métodos para agregar, modificar, eliminar, listar y buscar clientes.
     */
    private ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }

    /**
     * Agrega un nuevo cliente después de validar sus datos.
     *
     * @param cliente El cliente a agregar.
     * @return El cliente agregado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Cliente agregarCliente(Cliente cliente) throws SQLException {
        validarCliente(cliente);
        return clienteDAO.crear(cliente);
    }

    /**
     * Modifica un cliente existente después de validar sus datos.
     *
     * @param cliente El cliente a modificar.
     * @return El cliente modificado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Cliente modificarCliente(Cliente cliente) throws SQLException {
        validarCliente(cliente);
        clienteDAO.modificar(cliente);
        return cliente;
    }

    /**
     * Elimina un cliente basado en su DNI.
     *
     * @param dni El DNI del cliente a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws IllegalArgumentException Si el DNI es nulo o vacío.
     */
    public void eliminarClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para eliminar.");
        }
        clienteDAO.eliminarPorDNI(dni);
    }

    /**
     * Lista todos los clientes en el sistema.
     *
     * @return Una lista de todos los clientes.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public List<Cliente> listarClientes() throws SQLException {
        return clienteDAO.list();
    }


    /**
     * Busca clientes por su DNI, devolviendo una lista de coincidencias.
     *
     * @param dni El DNI del cliente a buscar.
     * @return Una lista de clientes que coinciden con el DNI.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws IllegalArgumentException Si el DNI es nulo o vacío.
     */
    public List<Cliente> obtenerClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para buscar.");
        }
        return clienteDAO.buscar(dni); // Suponiendo que `buscar` devuelve una lista de clientes
    }
    /**
     * Verifica si existe un cliente en el sistema con el DNI especificado.
     *
     * @param dni El DNI del cliente a verificar.
     * @return true si existe un cliente con el DNI, de lo contrario false.
     * @throws SQLException Si ocurre un error en la base de datos.
     * @throws IllegalArgumentException Si el DNI es nulo o vacío.
     */
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

    /**
     * Valida los datos del cliente antes de realizar una operación.
     *
     * @param cliente El cliente a validar.
     * @throws IllegalArgumentException Si algún dato obligatorio (DNI, nombre, apellido) es nulo o vacío.
     */
    public boolean existeClientePorDNI(String dni) throws SQLException {
        if (dni == null || dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI es obligatorio para verificar la existencia.");
        }
        return clienteDAO.validarExistenciaPorDNI(dni);
    }
}




