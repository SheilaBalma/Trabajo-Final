// Empleado.java
package Model.Entity;

public class Empleado extends Persona {
    private final int idEmpleado;
    private String dni;

    // Constructor para crear empleado con ID (usado en consultas y al obtener el ID de la BD)
    public Empleado(int idEmpleado, String nombre, String apellido, String direccion, String telefono, String email, String dni) {
        super(nombre, apellido, direccion, telefono, email);
        this.idEmpleado = idEmpleado;
        this.dni = dni;
    }

    // Constructor para crear empleado sin ID (usado antes de insertar en la BD)
    public Empleado(String nombre, String apellido, String direccion, String telefono, String email, String dni) {
        super(nombre, apellido, direccion, telefono, email);
        this.idEmpleado = -1; // Valor por defecto cuando aún no se asigna desde la base de datos
        this.dni = dni;
    }

    // Getter para ID
    public int getIdEmpleado() {
        return idEmpleado;
    }

    // Getter y Setter para DNI
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}



