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
        if (empleadoDAO.buscarEmpleadoPorDni(empleado.getDni()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con este DNI.");
        }
        return empleadoDAO.agregarEmpleado(empleado);
    }

    public Empleado modificarEmpleado(Empleado empleado) throws SQLException {
        validarEmpleado(empleado);
        return empleadoDAO.modificarEmpleado(empleado);
    }

    public void eliminarEmpleadoPorDni(String dni) throws SQLException {
        if (dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        empleadoDAO.eliminarEmpleadoPorDni(dni);
    }

    public Empleado buscarEmpleadoPorDni(String dni) throws SQLException {
        if (dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        return empleadoDAO.buscarEmpleadoPorDni(dni);
    }

    public List<Empleado> listarEmpleados() throws SQLException {
        return empleadoDAO.listarEmpleados();
    }

    private void validarEmpleado(Empleado empleado) {
        if (empleado.getNombre().isEmpty() || empleado.getApellido().isEmpty() ||
                empleado.getDireccion().isEmpty() || empleado.getTelefono().isEmpty() ||
                empleado.getEmail().isEmpty() || empleado.getDni().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
    }
}





