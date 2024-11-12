package Controller;

import Model.DAO.EmpleadoDAO;
import Model.Entity.Empleado;

import java.sql.SQLException;
import java.util.List;
/**
 * Controlador para gestionar las operaciones relacionadas con empleados.
 */
public class EmpleadoController {

    private EmpleadoDAO empleadoDAO;
    /**
     * Constructor que inicializa el DAO de empleados.
     */
    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }
    /**
     * Agrega un nuevo empleado después de validar sus datos y verificar que no exista otro con el mismo DNI.
     *
     * @param empleado El empleado a agregar.
     * @return El empleado agregado con su ID asignado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Empleado agregarEmpleado(Empleado empleado) throws SQLException {
        validarEmpleado(empleado);
        if (empleadoDAO.buscarEmpleadoPorDni(empleado.getDni()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con este DNI.");
        }
        return empleadoDAO.agregarEmpleado(empleado);
    }
    /**
     * Modifica un empleado existente.
     *
     * @param empleado El empleado con los datos modificados.
     * @return El empleado actualizado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Empleado modificarEmpleado(Empleado empleado) throws SQLException {
        validarEmpleado(empleado);
        return empleadoDAO.modificarEmpleado(empleado);
    }
    /**
     * Elimina un empleado de la base de datos utilizando su DNI.
     *
     * @param dni El DNI del empleado a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public void eliminarEmpleadoPorDni(String dni) throws SQLException {
        if (dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        empleadoDAO.eliminarEmpleadoPorDni(dni);
    }
    /**
     * Busca un empleado por su DNI.
     *
     * @param dni El DNI del empleado a buscar.
     * @return El empleado encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public Empleado buscarEmpleadoPorDni(String dni) throws SQLException {
        if (dni.isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        return empleadoDAO.buscarEmpleadoPorDni(dni);
    }
    /**
     * Lista todos los empleados en la base de datos.
     *
     * @return Una lista de empleados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    public List<Empleado> listarEmpleados() throws SQLException {
        return empleadoDAO.listarEmpleados();
    }
    /**
     * Valida los datos de un empleado.
     *
     * @param empleado El empleado a validar.
     * @throws IllegalArgumentException Si algún campo está vacío.
     */
    private void validarEmpleado(Empleado empleado) {
        if (empleado.getNombre().isEmpty() || empleado.getApellido().isEmpty() ||
                empleado.getDireccion().isEmpty() || empleado.getTelefono().isEmpty() ||
                empleado.getEmail().isEmpty() || empleado.getDni().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
    }
}





