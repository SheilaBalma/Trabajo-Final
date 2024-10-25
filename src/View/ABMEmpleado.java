package View;

import Controller.EmpleadoController;
import Model.Entity.Empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ABMEmpleado extends JFrame {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField emailField;

    private EmpleadoController empleadoController;

    public ABMEmpleado() {
        empleadoController = new EmpleadoController(); // Instancia del controlador

        setTitle("ABM Empleado");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Inicializar componentes visuales
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(30, 30, 150, 25);
        nombreField = new JTextField();
        nombreField.setBounds(180, 30, 150, 25);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(30, 70, 150, 25);
        apellidoField = new JTextField();
        apellidoField.setBounds(180, 70, 150, 25);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setBounds(30, 110, 150, 25);
        direccionField = new JTextField();
        direccionField.setBounds(180, 110, 150, 25);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(30, 150, 150, 25);
        telefonoField = new JTextField();
        telefonoField.setBounds(180, 150, 150, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 190, 150, 25);
        emailField = new JTextField();
        emailField.setBounds(180, 190, 150, 25);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBounds(400, 30, 150, 40);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(400, 90, 150, 40);

        JButton modificarButton = new JButton("Modificar");
        modificarButton.setBounds(400, 150, 150, 40);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(400, 210, 150, 40);

        // Añadir componentes al JFrame
        add(nombreLabel);
        add(nombreField);
        add(apellidoLabel);
        add(apellidoField);
        add(direccionLabel);
        add(direccionField);
        add(telefonoLabel);
        add(telefonoField);
        add(emailLabel);
        add(emailField);
        add(agregarButton);
        add(buscarButton);
        add(modificarButton);
        add(eliminarButton);

        // Funcionalidad del botón agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Empleado empleado = new Empleado(
                            nombreField.getText(),
                            apellidoField.getText(),
                            direccionField.getText(),
                            telefonoField.getText(),
                            emailField.getText()
                    );

                    String resultado = empleadoController.agregarEmpleado(empleado);
                    JOptionPane.showMessageDialog(null, resultado);
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar empleado: " + ex.getMessage());
                }
            }
        });

        // Funcionalidad del botón buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText().trim();
                String apellido = apellidoField.getText().trim();

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese nombre y apellido para buscar.");
                    return;
                }

                List<Empleado> empleados = empleadoController.buscarEmpleados(nombre, apellido);
                if (empleados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún empleado.");
                } else {
                    Empleado empleado = empleados.get(0);
                    direccionField.setText(empleado.getDirección());
                    telefonoField.setText(empleado.getTelefono());
                    emailField.setText(empleado.getEmail());
                }
            }
        });

        // Funcionalidad del botón modificar
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Empleado empleado = new Empleado(
                            nombreField.getText(),
                            apellidoField.getText(),
                            direccionField.getText(),
                            telefonoField.getText(),
                            emailField.getText()
                    );

                    String resultado = empleadoController.modificarEmpleado(empleado);
                    JOptionPane.showMessageDialog(null, resultado);
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar empleado: " + ex.getMessage());
                }
            }
        });

        // Funcionalidad del botón eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText().trim();
                    String apellido = apellidoField.getText().trim();

                    if (nombre.isEmpty() || apellido.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar nombre y apellido para eliminar.");
                        return;
                    }

                    String resultado = empleadoController.eliminarEmpleado(nombre, apellido);
                    JOptionPane.showMessageDialog(null, resultado);
                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar empleado: " + ex.getMessage());
                }
            }
        });
    }

    private void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        ABMEmpleado abmEmpleado = new ABMEmpleado();
        abmEmpleado.setVisible(true);
    }
}
