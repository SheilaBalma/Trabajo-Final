package Controller;

import Model.DAO.EmpleadoDAO;
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
        if (!empleadoDAO.agregarEmpleado(empleado)) {
            return "Error al agregar el empleado.";
        }
        return "Empleado agregado exitosamente.";
    }

    public String modificarEmpleado(Empleado empleado) throws SQLException {
        if (!empleadoDAO.modificarEmpleado(empleado)) {
            return "Error al modificar el empleado.";
        }
        return "Empleado modificado exitosamente.";
    }

    public List<Empleado> buscarEmpleados(String nombre, String apellido) throws SQLException {
        return empleadoDAO.buscarEmpleados(nombre, apellido);
    }

    public List<Empleado> listarEmpleados() throws SQLException {
        return empleadoDAO.listarEmpleados();
    }

    public String eliminarEmpleado(String nombre, String apellido) throws SQLException {
        if (!empleadoDAO.eliminarEmpleado(nombre, apellido)) {
            return "Error al eliminar el empleado.";
        }
        return "Empleado eliminado exitosamente.";
    }
}

