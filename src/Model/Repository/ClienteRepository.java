package Model.Repository;

import Model.Entity.Cliente;
import java.util.List;

public interface ClienteRepository {
    Cliente crear(Cliente cliente);
    void modificar(Cliente cliente);
    void eliminar(int idCLiente);
    List<Cliente> buscar(String nombre, String dni);
    List<Cliente> list();
}

