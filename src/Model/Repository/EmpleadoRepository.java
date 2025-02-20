package Model.Repository;

import Model.Entity.Empleado;

import java.sql.SQLException;
import java.util.List;

public interface EmpleadoRepository {
    Empleado crear(Empleado empleado) throws SQLException;
    void modificarEmpleado(Empleado empleado) throws SQLException;
    void eliminarEmpleado(String dni) throws SQLException;
    List<Empleado> buscarEmpleado(String dni) throws SQLException;
    List<Empleado> listarEmpleados() throws SQLException;
    public boolean ExisteDni(String dni) throws SQLException; // Añadir este método
}
