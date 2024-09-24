package Model.Repository;

import Model.Entity.Cliente;

import java.util.List;

public interface ClienteRepository {
    Cliente crear(Cliente cliente);
    List<Cliente> list();
    List<Cliente> buscar(String nombre, String dni); // No es necesario que pase los dos
}
