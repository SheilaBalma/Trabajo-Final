package Controller;

import Model.Entity.Cliente;
import Model.Repository.ClienteRepository;

public class ClienteController {
    private ClienteRepository clienteRepository;

    public Cliente crear (String nombre, String dni){
        Cliente cliente=new Cliente();
        cliente.setNombre(nombre);
        cliente.setDni(dni);
        this.clienteRepository.crear(cliente);
        return null;
    }
}

