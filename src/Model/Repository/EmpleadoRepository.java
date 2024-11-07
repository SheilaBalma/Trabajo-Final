package Model.Repository;

import Model.Entity.Empleado;
import java.util.List;

public interface EmpleadoRepository {
    boolean agregarEmpleado(Empleado empleado);
    boolean modificarEmpleado(Empleado empleado);
    List<Empleado> buscarEmpleados(String nombre, String apellido);
    boolean eliminarEmpleado(String nombre, String apellido);

    List<Empleado> listarEmpleados();
}
