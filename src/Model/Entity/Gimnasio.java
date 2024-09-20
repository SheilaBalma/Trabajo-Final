package Model.Entity;

import java.util.List;

public class Gimnasio {
    private String nombreGimnasio;
    private List<Cliente> clientes;
    private List<Empleado> empleados;
    private List<Membresia> membresias;
    private List<Pago> pagos;
    private List<Actividad> actividades;

    public Gimnasio(List<Cliente> clientes, List<Empleado> empleados,
                    List<Membresia> membresias,List<Pago> pagos,
                    List<Actividad> actividades) {
        this.clientes = clientes;
        this.empleados = empleados;
        this.membresias = membresias;
        this.pagos = pagos;
        this.actividades = actividades;
    }

    public String getNombreGimnasio() {
        return nombreGimnasio;
    }

    public void setNombreGimnasio(String nombreGimnasio) {
        this.nombreGimnasio = nombreGimnasio;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Membresia> getMembresias() {
        return membresias;
    }

    public void setMembresias(List<Membresia> membresias) {
        this.membresias = membresias;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void AgregarCliente (Cliente cliente){

    }

    public void EliminarCliente (Cliente cliente){

    }

    public void ModificarCliente (Cliente cliente){

    }

    public void AgregarEmpleado (Empleado empleado){

    }

    public void EliminarEmpleado (Empleado empleado){

    }

    public void ModificarEmpleado (Empleado empleado){

    }

    public void AgregarActividad (Actividad actividad){

    }

    public void EliminarActividad (Actividad actividad){

    }

    public void ModificarActividad (Actividad actividad){

    }

    public void RegistrarPago(Pago pago){

    }

}
