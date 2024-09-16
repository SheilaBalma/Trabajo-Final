package View;

import javax.swing.*;
import java.awt.*;

public class ABMCliente extends JFrame {

    // Constructor de la ventana
    public ABMCliente() {
        setTitle("ABM Cliente");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Componentes visuales
        add(new JLabel("ID Cliente:"));
        JTextField idClienteField = new JTextField();
        add(idClienteField);

        add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        add(nombreField);

        add(new JLabel("Edad:"));
        JTextField edadField = new JTextField();
        add(edadField);

        add(new JLabel("Teléfono:"));
        JTextField telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel("DNI:"));
        JTextField dniField = new JTextField();
        add(dniField);

        add(new JLabel("Tipo de Membresía:"));
        JTextField tipoMembresiaField = new JTextField();
        add(tipoMembresiaField);

        add(new JLabel("Estado de Pago:"));
        JCheckBox estadoPagoCheck = new JCheckBox();
        add(estadoPagoCheck);

        add(new JLabel("Lista de Actividades:"));
        JTextField listaActividadesField = new JTextField();
        add(listaActividadesField);

        // Botones
        JButton agregarButton = new JButton("Agregar");
        add(agregarButton);

        JButton modificarButton = new JButton("Modificar");
        add(modificarButton);

        JButton eliminarButton = new JButton("Eliminar");
        add(eliminarButton);

        JButton buscarButton = new JButton("Buscar");
        add(buscarButton);

        setVisible(true);
    }

    // Main para mostrar la ventana
    public static void main(String[] args) {
        new ABMCliente();
    }
}
