package Model.Repository;

import Model.Entity.Cliente;
import java.sql.SQLException;
import java.util.List;
/**
 * Interfaz que define las operaciones CRUD para la entidad Cliente.
 */
public interface ClienteRepository {
    /**
     * Crea un nuevo cliente.
     *
     * @param cliente Cliente a crear.
     * @return Cliente creado.
     * @throws SQLException Si ocurre un error en la consulta SQL.
     */
    Cliente crear(Cliente cliente) throws SQLException;
    /**
     * Modifica los datos de un cliente existente.
     *
     * @param cliente Cliente con los datos actualizados.
     * @throws SQLException Si ocurre un error en la consulta SQL.
     */
    void modificar(Cliente cliente) throws SQLException;
    /**
     * Elimina un cliente según su DNI.
     *
     * @param dni DNI del cliente a eliminar.
     * @throws SQLException Si ocurre un error en la consulta SQL.
     */
    void eliminarPorDNI(String dni) throws SQLException;
    /**
     * Busca un cliente específico por su DNI.
     *
     * @param dni DNI del cliente a buscar.
     * @return Lista de clientes encontrados.
     * @throws SQLException Si ocurre un error en la consulta SQL.
     */
    List<Cliente> buscar( String dni) throws SQLException;
    /**
     * Lista todos los clientes en la base de datos.
     *
     * @return Lista de todos los clientes.
     * @throws SQLException Si ocurre un error en la consulta SQL.
     */
    List<Cliente> list() throws SQLException;
}
