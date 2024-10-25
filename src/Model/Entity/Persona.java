package Model.Entity;

import java.util.Objects;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dirección;
    protected String telefono;
    protected String email;
public Persona(){

}
    // Constructor
    public Persona(String nombre, String apellido, String dirección, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dirección = dirección;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método equals para comparar objetos Persona
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(nombre, persona.nombre) &&
                Objects.equals(apellido, persona.apellido) &&
                Objects.equals(email, persona.email);
    }

    // Método hashCode para generar hash basado en los atributos relevantes
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, email);
    }

    // Método toString para imprimir información de la Persona
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + dirección + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
