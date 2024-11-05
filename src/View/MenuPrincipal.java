package View;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menú Principal - Gestión de ABM");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear el JTabbedPane para las pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Agregar cada ABM a una pestaña
        tabbedPane.addTab("ABM Empleado", new ABMEmpleado());
        tabbedPane.addTab("ABM Cliente", new ABMCliente());

        // Agregar el JTabbedPane a la ventana principal
        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana principal
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
    }
}
