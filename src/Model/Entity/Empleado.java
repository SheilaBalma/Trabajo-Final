package Model.Entity;

public class Empleado extends Persona {
    public Empleado(String nombre, String apellido, String direccion, String telefono, String email) {
        super(nombre, apellido, direccion, telefono, email);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", direccion='" + getDireccion() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}


