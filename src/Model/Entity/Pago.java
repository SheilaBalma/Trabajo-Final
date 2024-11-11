package Model.Entity;

public class Pago {
    public enum MetodoPago {
        EFECTIVO, TARJETA
    }

    private MetodoPago metodo;
    private double monto;

    public Pago(MetodoPago metodo, double monto) {
        this.metodo = metodo;
        this.monto = monto;
    }

    public MetodoPago getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoPago metodo) {
        this.metodo = metodo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "metodo=" + metodo +
                ", monto=" + monto +
                '}';
    }
}

