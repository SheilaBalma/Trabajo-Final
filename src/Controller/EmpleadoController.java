package Controller;

import Model.DAO.EmpleadoDAO;
import Model.Entity.Cliente;
import Model.Entity.Empleado;
import Model.Repository.EmpleadoRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con los empleados.
 * Proporciona métodos para agregar, modificar, eliminar, buscar y listar empleados.
 */
public class EmpleadoController {
    private EmpleadoRepository empleadoDAO;

    /**
     * Constructor que inicializa el controlador y la instancia del DAO de Empleado.
     */
    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }

    /**
     * Agrega un empleado a la base de datos después de realizar validaciones.
     *
     * @param empleado El empleado que se desea agregar.
     * @return Un mensaje de éxito o error.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
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

    /**
     * Modifica la información de un empleado en la base de datos.
     *
     * @param empleado El empleado con la información actualizada.
     * @return Un mensaje de éxito o error.
     */
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

    /**
     * Busca un empleado en la base de datos por su DNI.
     *
     * @param dni El DNI del empleado a buscar.
     * @return Una lista de empleados que coincidan con el DNI proporcionado.
     */
    public List<Empleado> buscarEmpleados(String dni) {
        try {
            return empleadoDAO.buscarEmpleado(dni);
        } catch (Exception e) {
            System.err.println("Error al buscar el empleado: " + e.getMessage());
            return null;
        }
    }

    /**
     * Elimina un empleado de la base de datos basado en su DNI.
     *
     * @param dni El DNI del empleado a eliminar.
     * @return Un mensaje de éxito o error.
     */
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

    /**
     * Obtiene la lista de todos los empleados registrados.
     *
     * @return Una lista de empleados o null si ocurre un error.
     */
    public List<Empleado> listarEmpleados() {
        try {
            return empleadoDAO.listarEmpleados();
        } catch (Exception e) {
            System.err.println("Error al listar los empleados: " + e.getMessage());
            return null;
        }
    }
}
