package Controller;

import Model.DAO.EmpleadoDAO;
import Model.Entity.Cliente;
import Model.Entity.Empleado;
import Model.Repository.EmpleadoRepository;

import java.sql.SQLException;
import java.util.List;

public class EmpleadoController {
    private EmpleadoRepository empleadoDAO;

    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }

    public String agregarEmpleado(Empleado empleado) throws SQLException {
        if (empleado == null || empleado.getDni() == null || empleado.getDni().isEmpty()) {
            return "Error: El empleado o el DNI no pueden ser nulos o vacíos.";
        }
        // Verificar si el DNI ya existe
        if (empleadoDAO.ExisteDni(empleado.getDni())) {
            return "Error: El DNI ya está registrado en el sistema.";
        }

        try {
            empleadoDAO.crear(empleado);
            return "Empleado creado exitosamente.";
        } catch (Exception e) {
            return "Error al crear el empleado: " + e.getMessage();
        }
    }


    public String modificarEmpleado(Empleado empleado) {
        if (empleado == null || empleado.getDni() == null || empleado.getDni().isEmpty()) {
            return "Error: El empleado o el DNI no pueden ser nulos o vacíos.";
        }

        try {
            empleadoDAO.modificarEmpleado(empleado);
            return "Empleado modificado exitosamente.";
        } catch (Exception e) {
            return "Error al modificar el empleado: " + e.getMessage();
        }
    }

    public List<Empleado> buscarEmpleados(String dni) {
        try {
            return empleadoDAO.buscarEmpleado(dni);
        } catch (Exception e) {
            System.err.println("Error al buscar el empleado: " + e.getMessage());
            return null;
        }
    }

    public String eliminarEmpleado(String dni) {
        if (dni == null || dni.isEmpty()) {
            return "Error: El DNI no puede ser nulo o vacío.";
        }

        try {
            empleadoDAO.eliminarEmpleado(dni);
            return "Empleado eliminado exitosamente.";
        } catch (Exception e) {
            return "Error al eliminar el empleado: " + e.getMessage();
        }
    }

    // Método para listar todos los empleados
    public List<Empleado> listarEmpleados() {
        try {
            return empleadoDAO.listarEmpleados();
        } catch (Exception e) {
            System.err.println("Error al listar los empleados: " + e.getMessage());
            return null;
        }
    }
}
