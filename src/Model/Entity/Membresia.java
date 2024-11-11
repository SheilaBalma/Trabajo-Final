package Model.Entity;

public class Membresia {
    public enum TipoMembresia {
        SILVER, GOLDEN, DIAMANT
    }

    private TipoMembresia tipo;
    private String descripcion;
    private int limiteAcceso;

    public Membresia(TipoMembresia tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case SILVER:
                this.descripcion = "Acceso al gimnasio 3 veces por semana";
                this.limiteAcceso = 3;
                break;
            case GOLDEN:
                this.descripcion = "Acceso libre al gimnasio";
                this.limiteAcceso = -1; // Sin límite
                break;
            case DIAMANT:
                this.descripcion = "Acceso libre al gimnasio y a todas las actividades";
                this.limiteAcceso = -1; // Sin límite
                break;
        }
    }

    public TipoMembresia getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getLimiteAcceso() {
        return limiteAcceso;
    }

    @Override
    public String toString() {
        return "Membresia{" +
                "tipo=" + tipo +
                ", descripcion='" + descripcion + '\'' +
                ", limiteAcceso=" + (limiteAcceso == -1 ? "Ilimitado" : limiteAcceso) +
                '}';
    }
}

