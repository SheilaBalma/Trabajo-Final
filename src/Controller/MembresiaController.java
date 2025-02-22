package Controller;

import Model.DAO.MembresiaDAO;
import Model.Entity.Membresia;

/**
 * Controlador para gestionar las operaciones relacionadas con las membresías.
 */
public class MembresiaController {
    private MembresiaDAO membresiaDAO;

    /**
     * Constructor de la clase MembresiaController.
     * Inicializa la instancia de MembresiaDAO.
     */
    public MembresiaController() {
        this.membresiaDAO = new MembresiaDAO();
    }

    /**
     * Guarda una nueva membresía en la base de datos.
     *
     * @param membresia Objeto Membresia a guardar.
     *                  Debe ser un objeto válido y no nulo.
     */
    public void guardarMembresia(Membresia membresia) {
        if (membresia != null) {
            membresiaDAO.guardarMembresia(membresia);
        } else {
            System.out.println("No se puede guardar una membresía nula.");
        }
    }
}
