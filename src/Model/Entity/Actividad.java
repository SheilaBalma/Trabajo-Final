package Model.Entity;

public class Actividad {
    public enum TipoActividad {
        FUNCIONAL, PILATES, RUNNING, MUSCULACION
    }

    private TipoActividad tipo;
    private String descripcion;

    public Actividad(TipoActividad tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case FUNCIONAL:
                this.descripcion = "Entrenamiento funcional";
                break;
            case PILATES:
                this.descripcion = "Clase de Pilates";
                break;
            case RUNNING:
                this.descripcion = "Running en grupo";
                break;
            case MUSCULACION:
                this.descripcion = "Musculación en el gimnasio";
                break;
        }
    }

    public TipoActividad getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "tipo=" + tipo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}

