package Model.Repository;

import Model.Entity.Cliente;
import java.sql.SQLException;
import java.util.List;

public interface ClienteRepository {
    Cliente crear(Cliente cliente) throws SQLException;
    void modificar(Cliente cliente) throws SQLException;
    void eliminar(int idCliente) throws SQLException;
    void eliminarPorDNI(String dni) throws SQLException;
    List<Cliente> buscar( String dni) throws SQLException;
    List<Cliente> list() throws SQLException;
}






