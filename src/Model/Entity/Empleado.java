// Empleado.java
package Model.Entity;
/**
 * Clase que representa a un empleado.
 */
public class Empleado extends Persona {
    private final int idEmpleado;
    private String dni;

    /**
     * Constructor para crear un empleado con ID asignado.
     *
     * @param idEmpleado El ID del empleado.
     * @param nombre El nombre del empleado.
     * @param apellido El apellido del empleado.
     * @param direccion La dirección del empleado.
     * @param telefono El teléfono del empleado.
     * @param email El email del empleado.
     * @param dni El DNI del empleado.
     */
    public Empleado(int idEmpleado, String nombre, String apellido, String direccion, String telefono, String email, String dni) {
        super(nombre, apellido, direccion, telefono, email);
        this.idEmpleado = idEmpleado;
        this.dni = dni;
    }

    /**
     * Constructor para crear un empleado sin ID asignado (para inserciones).
     *
     * @param nombre El nombre del empleado.
     * @param apellido El apellido del empleado.
     * @param direccion La dirección del empleado.
     * @param telefono El teléfono del empleado.
     * @param email El email del empleado.
     * @param dni El DNI del empleado.
     */
    public Empleado(String nombre, String apellido, String direccion, String telefono, String email, String dni) {
        super(nombre, apellido, direccion, telefono, email);
        this.idEmpleado = -1; // Valor por defecto cuando aún no se asigna desde la base de datos
        this.dni = dni;
    }
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



