// EmpleadoRepository.java
package Model.Repository;

import Model.Entity.Empleado;
import java.sql.SQLException;
import java.util.List;

public interface EmpleadoRepository {
    Empleado agregarEmpleado(Empleado empleado) throws SQLException;
    Empleado modificarEmpleado(Empleado empleado) throws SQLException;
    Empleado buscarEmpleadoPorDni(String dni) throws SQLException;
    void eliminarEmpleadoPorDni(String dni) throws SQLException;
    List<Empleado> listarEmpleados() throws SQLException;
}



