package Controller;

import Model.DAO.ActividadDAO;
import Model.Entity.Actividad;

/**
 * Controlador para gestionar las operaciones relacionadas con las actividades.
 */
public class ActividadController {
    private ActividadDAO actividadDAO;

    /**
     * Constructor de la clase ActividadController.
     * Inicializa la instancia de ActividadDAO.
     */
    public ActividadController() {
        this.actividadDAO = new ActividadDAO();
    }

    /**
     * Guarda una nueva actividad en la base de datos.
     *
     * @param actividad Objeto Actividad a guardar.
     *                  No debe ser nulo para evitar errores en la persistencia.
     */
    public void guardarActividad(Actividad actividad) {
        if (actividad != null) {
            actividadDAO.guardarActividad(actividad);
        } else {
            System.out.println("No se puede guardar una actividad nula.");
        }
    }
}
