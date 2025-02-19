package Model.Entity;

public class Actividad {
    private String nombre;

    public Actividad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    // Definir las actividades posibles, por ejemplo:
    public static Actividad[] values() {
        return new Actividad[]{
                new Actividad("Yoga"),
                new Actividad("Spinning"),
                new Actividad("CrossFit")
        };
    }
}
