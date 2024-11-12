// EmpleadoRepository.java
package Model.Repository;

import Model.Entity.Empleado;
import java.sql.SQLException;
import java.util.List;
/**
 * Interfaz que define los métodos de operaciones CRUD para empleados.
 */
public interface EmpleadoRepository {
    /**
     * Agrega un nuevo empleado a la base de datos.
     *
     * @param empleado El empleado a agregar.
     * @return El empleado agregado con su ID asignado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    Empleado agregarEmpleado(Empleado empleado) throws SQLException;
    /**
     * Modifica un empleado en la base de datos.
     *
     * @param empleado El empleado con los datos actualizados.
     * @return El empleado actualizado.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    Empleado modificarEmpleado(Empleado empleado) throws SQLException;
    /**
     * Busca un empleado en la base de datos por su DNI.
     *
     * @param dni El DNI del empleado a buscar.
     * @return El empleado encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    Empleado buscarEmpleadoPorDni(String dni) throws SQLException;
    /**
     * Elimina un empleado de la base de datos por su DNI.
     *
     * @param dni El DNI del empleado a eliminar.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    void eliminarEmpleadoPorDni(String dni) throws SQLException;
    /**
     * Lista todos los empleados en la base de datos.
     *
     * @return Una lista de empleados.
     * @throws SQLException Si ocurre un error en la base de datos.
     */
    List<Empleado> listarEmpleados() throws SQLException;
}



