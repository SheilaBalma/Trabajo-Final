package Model.Entity;

public class Cliente extends Persona {
    private int idCliente;
    private String dni;
    private int edad;
    private String direccion; // Se agrega aquí
    private String tipoMembresia;
    private String actividad;
    private boolean estadoPago;

    // Constructor por defecto
    public Cliente() {
        super();
    }

    // Constructor con parámetros
    public Cliente(int idCliente, String nombre, String apellido, String direccion, String telefono, String email,
                   String dni, int edad, String tipoMembresia, String actividad, boolean estadoPago) {
        super(nombre, apellido, telefono, email);
        this.idCliente = idCliente;
        this.direccion = direccion;
        this.dni = dni;
        this.edad = edad;
        this.tipoMembresia = tipoMembresia;
        this.actividad = actividad;
        this.estadoPago = estadoPago;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    // Método toString para imprimir información del Cliente
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' + // Se incluye aquí
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                ", tipoMembresia='" + tipoMembresia + '\'' +
                ", actividad='" + actividad + '\'' +
                ", estadoPago=" + estadoPago +
                '}';
    }
}
