package Controller;

import Model.DAO.EmpleadoDAO;
import Model.Entity.Empleado;
import Model.Repository.EmpleadoRepository;

import java.util.List;

public class EmpleadoController {
    private EmpleadoRepository empleadoDAO;

    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }

    public String agregarEmpleado(Empleado empleado) {
        if (empleadoDAO.agregarEmpleado(empleado)) {
            return "Empleado agregado exitosamente.";
        } else {
            return "Error al agregar el empleado.";
        }
    }

    public String modificarEmpleado(Empleado empleado) {
        if (empleadoDAO.modificarEmpleado(empleado)) {
            return "Empleado modificado exitosamente.";
        } else {
            return "Error al modificar el empleado.";
        }
    }

    public List<Empleado> buscarEmpleados(String nombre, String apellido) {
        return empleadoDAO.buscarEmpleados(nombre, apellido);
    }

    public List<Empleado> listarEmpleados() {
        return empleadoDAO.listarEmpleados();
    }

    public String eliminarEmpleado(String nombre, String apellido) {
        if (empleadoDAO.eliminarEmpleado(nombre, apellido)) {
            return "Empleado eliminado exitosamente.";
        } else {
            return "Error al eliminar el empleado.";
        }
    }
}
