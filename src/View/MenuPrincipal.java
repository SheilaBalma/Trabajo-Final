//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        this.setTitle("Menú Principal - Gestión de ABM");
        this.setSize(900, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("ABM Empleado", new ABMEmpleado()); // Ahora es JPanel
        tabbedPane.addTab("ABM Cliente", new ABMCliente());   // Ahora es JPanel

       // tabbedPane.addTab("ABM Membresía", new ABMMembresia()); // Aquí también agregamos el panel de membresía

        this.add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
    }
}
