package Model.Entity;

import java.util.List;

public class Cliente extends Persona{

    // Atributos
    private int idCliente;
    private String nombre;
    private int edad;
    private String telefono;
    private String dni;
    private String tipoMembresia;
    private boolean estadoPago;
    private List<String> listaActividades; //Cuando haya clase actividadaes es una lista de actividades

    // Constructor vacío
    public Cliente() {}

    // Constructor con todos los atributos
    public Cliente(int idCliente, String nombre, int edad, String telefono, String dni, String tipoMembresia, boolean estadoPago, List<String> listaActividades) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.dni = dni;
        this.tipoMembresia = tipoMembresia;
        this.estadoPago = estadoPago;
        this.listaActividades = listaActividades;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public List<String> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(List<String> listaActividades) {
        this.listaActividades = listaActividades;
    }

    // Método equals para comparar objetos Cliente
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return idCliente == cliente.idCliente && dni.equals(cliente.dni);
    }

    // Método toString para representar el objeto como cadena
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", dni='" + dni + '\'' +
                ", tipoMembresia='" + tipoMembresia + '\'' +
                ", estadoPago=" + estadoPago +
                ", listaActividades=" + listaActividades +
                '}';
    }
}

