package Model.Repository;

import Model.Entity.Empleado;
import java.sql.SQLException;
import java.util.List;

public interface EmpleadoRepository {
    Empleado agregarEmpleado(Empleado empleado) throws SQLException;
    Empleado modificarEmpleado(Empleado empleado) throws SQLException;
    List<Empleado> buscarEmpleados(String nombre, String apellido) throws SQLException;
    void eliminarEmpleado(String nombre, String apellido) throws SQLException;
    List<Empleado> listarEmpleados() throws SQLException;
}

