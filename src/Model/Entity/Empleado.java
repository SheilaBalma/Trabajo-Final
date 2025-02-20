package Model.Entity;

public class Empleado extends Persona {
    private String dni; // DNI del empleado

    // Constructor para crear un empleado
    public Empleado(String nombre, String apellido, String direccion, String telefono, String email, String dni) {
        super(nombre, apellido, direccion, telefono, email); // Llamada al constructor de Persona
        this.dni = dni;
    }

    public Empleado() {

    }

    // Getter y Setter para DNI
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    // Método para representar el empleado como cadena (opcional)
    @Override
    public String toString() {
        return "Empleado [Nombre: " + getNombre() + ", Apellido: " + getApellido() + ", DNI: " + dni + "]";
    }
}
