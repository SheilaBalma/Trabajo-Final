package Model.Repository;

import Model.Entity.Empleado;
import java.sql.SQLException;
import java.util.List;

public interface EmpleadoRepository {
    boolean agregarEmpleado(Empleado empleado) throws SQLException;
    boolean modificarEmpleado(Empleado empleado) throws SQLException;  // Agregar throws SQLException
    List<Empleado> buscarEmpleados(String nombre, String apellido) throws SQLException;
    boolean eliminarEmpleado(String nombre, String apellido) throws SQLException;
    List<Empleado> listarEmpleados() throws SQLException;
}
