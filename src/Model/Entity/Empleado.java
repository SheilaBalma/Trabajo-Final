package Model.Entity;

public class Empleado extends Persona {
    private String dni;

    public Empleado() {

    }

    // Constructor para crear un empleado
    public Empleado(String nombre, String apellido, String direccion, String telefono, String email, String dni) {
        super(nombre, apellido, direccion, telefono, email); // Llamada al constructor de Persona
        this.dni = dni;
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
        return "Empleado [Nombre: " + getNombre() + ", Apellido: " + getApellido() + ", DNI: " + dni + "]";
    }
}
