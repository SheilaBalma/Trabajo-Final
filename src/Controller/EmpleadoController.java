package Controller;

import Model.DAO.EmpleadoDAO;
import Model.Entity.Empleado;

import java.sql.SQLException;
import java.util.List;

public class EmpleadoController {

    private EmpleadoDAO empleadoDAO;

    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }

    public Empleado agregarEmpleado(Empleado empleado) throws SQLException {
        validarEmpleado(empleado);
        if (empleadoDAO.existeEmpleado(empleado.getNombre(), empleado.getApellido())) {
            throw new IllegalArgumentException("Ya existe un empleado con este nombre y apellido.");
        }
        return empleadoDAO.agregarEmpleado(empleado);
    }

    public Empleado modificarEmpleado(Empleado empleado) throws SQLException {
        validarEmpleado(empleado);
        return empleadoDAO.modificarEmpleado(empleado);  // Cambié a modificarEmpleado
    }

    public void eliminarEmpleado(String nombre, String apellido) throws SQLException {
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido son obligatorios para eliminar.");
        }
        empleadoDAO.eliminarEmpleado(nombre, apellido);  // Cambié a eliminarEmpleado
    }

    public List<Empleado> listarEmpleados() throws SQLException {
        return empleadoDAO.listarEmpleados();
    }

    public List<Empleado> buscarEmpleado(String nombre, String apellido) throws SQLException {
        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido son obligatorios para buscar.");
        }
        return empleadoDAO.buscarEmpleados(nombre, apellido);
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (empleado.getApellido() == null || empleado.getApellido().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }
        if (empleado.getDireccion() == null || empleado.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }
        if (empleado.getTelefono() == null || empleado.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }
        if (empleado.getEmail() == null || empleado.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio.");
        }
    }
}


