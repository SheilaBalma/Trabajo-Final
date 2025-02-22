package Controller;
import Model.DAO.ClienteDAO;
import Model.Entity.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con los clientes.
 * Proporciona métodos para agregar, modificar, eliminar, buscar y listar clientes.
 */
public class ClienteController {
    private ClienteDAO clienteDAO;

    /**
     * Constructor que inicializa el controlador y la instancia del DAO de Cliente.
     */
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Agrega un cliente a la base de datos después de realizar validaciones.
     *
     * @param cliente El cliente que se desea agregar.
     * @return Un mensaje de éxito o error.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public String agregarCliente(Cliente cliente) throws SQLException {
        if (cliente == null || cliente.getDni() == null || cliente.getDni().isEmpty()) {
            return "Error: El cliente o el DNI no pueden ser nulos o vacíos.";
        }
        if (clienteDAO.existeDni(cliente.getDni())) {
            return "Error: El DNI ya está registrado en el sistema.";
        }

        try {
            clienteDAO.crear(cliente);
            return "Cliente creado exitosamente.";
        } catch (Exception e) {
            return "Error al crear el cliente: " + e.getMessage();
        }
    }

    /**
     * Modifica la información de un cliente en la base de datos.
     *
     * @param cliente El cliente con la información actualizada.
     * @return Un mensaje de éxito o error.
     */
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

    /**
     * Elimina un cliente de la base de datos basado en su DNI.
     *
     * @param dni El DNI del cliente a eliminar.
     * @return Un mensaje de éxito o error.
     */
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

    /**
     * Obtiene la lista de todos los clientes registrados.
     *
     * @return Una lista de clientes o null si ocurre un error.
     */
    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.list();
        } catch (Exception e) {
            System.err.println("Error al listar los clientes: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un cliente en la base de datos por su DNI.
     *
     * @param dni El DNI del cliente a buscar.
     * @return Una lista de clientes que coincidan con el DNI proporcionado.
     */
    public List<Cliente> buscarClientes(String dni) {
        try {
            return clienteDAO.buscar(dni);
        } catch (Exception e) {
            System.err.println("Error al buscar el cliente: " + e.getMessage());
            return null;
        }
    }
}
