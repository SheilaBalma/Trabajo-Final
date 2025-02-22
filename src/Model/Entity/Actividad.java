package Model.Entity;

public class Actividad {
    public enum TipoActividad {
        FUNCIONAL, PILATES, RUNNING, MUSCULACION
    }

    private TipoActividad tipo;

    public Actividad(TipoActividad tipo) {
        this.tipo = tipo;
    }

    public TipoActividad getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "tipo=" + tipo +
                '}';
    }
}
