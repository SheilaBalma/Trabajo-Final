package Model.DAO;

import Model.Entity.Cliente;
import Model.Repository.ClienteRepository;

import java.util.List;

public class ClienteDAO implements ClienteRepository {

    @Override
    public Cliente crear(Cliente cliente) {
        return null;
    }

    @Override
    public List<Cliente> list() {
        return List.of();
    }

    @Override
    public List<Cliente> buscar(String nombre, String dni) {
        return List.of();
    }
}
