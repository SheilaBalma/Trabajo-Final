package Model.Entity;
/**
 * Representa un cliente en el sistema, con información personal y datos
 * específicos como su tipo de membresía, método de pago, y estado de pago.
 */
public class Cliente extends Persona {
    private int idCliente;
    private String dni;
    private int edad;
    private Membresia.TipoMembresia tipoMembresia;
    private Pago.MetodoPago metodoPago;
    private boolean estadoPago;

    /**
     * Constructor completo para inicializar un cliente con todos sus atributos.
     *
     * @param idCliente       Identificador único del cliente.
     * @param nombre          Nombre del cliente.
     * @param apellido        Apellido del cliente.
     * @param direccion       Dirección del cliente.
     * @param telefono        Teléfono de contacto del cliente.
     * @param email           Email del cliente.
     * @param dni             DNI del cliente.
     * @param edad            Edad del cliente.
     * @param tipoMembresia   Tipo de membresía del cliente.
     * @param metodoPago      Método de pago utilizado por el cliente.
     * @param estadoPago      Estado del pago (activo/inactivo).
     */
    public Cliente(int idCliente, String nombre, String apellido, String direccion, String telefono, String email, String dni, int edad, Membresia.TipoMembresia tipoMembresia, Pago.MetodoPago metodoPago, boolean estadoPago) {
        super(nombre, apellido, direccion, telefono, email);  // Inicialización de Persona
        this.idCliente = idCliente;
        this.dni = dni;
        this.edad = edad;
        this.tipoMembresia = tipoMembresia;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }

    /**
     * Constructor para inicializar un cliente sin ID (para clientes nuevos).
     *
     * @param nombre          Nombre del cliente.
     * @param apellido        Apellido del cliente.
     * @param direccion       Dirección del cliente.
     * @param telefono        Teléfono de contacto del cliente.
     * @param email           Email del cliente.
     * @param dni             DNI del cliente.
     * @param edad            Edad del cliente.
     * @param tipoMembresia   Tipo de membresía del cliente.
     * @param metodoPago      Método de pago utilizado por el cliente.
     * @param estadoPago      Estado del pago (activo/inactivo).
     */
    public Cliente(String nombre, String apellido, String direccion, String telefono, String email, String dni, int edad, Membresia.TipoMembresia tipoMembresia, Pago.MetodoPago metodoPago, boolean estadoPago) {
        super(nombre, apellido, direccion, telefono, email);  // Inicialización de Persona
        this.dni = dni;
        this.edad = edad;
        this.tipoMembresia = tipoMembresia;
        this.metodoPago = metodoPago;
        this.estadoPago = estadoPago;
    }
    // Getters y Setters para campos propios de Cliente
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Membresia.TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(Membresia.TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public Pago.MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Pago.MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }
}

