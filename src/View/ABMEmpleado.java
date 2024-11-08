package View;

import Controller.EmpleadoController;
import Model.Entity.Empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ABMEmpleado extends JPanel {

    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField emailField;
    private EmpleadoController empleadoController;

    public ABMEmpleado() {
        empleadoController = new EmpleadoController();

        setLayout(null);

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

        JButton addButton = new JButton("Agregar Empleado");
        addButton.setBounds(30, 230, 150, 25);
        JButton updateButton = new JButton("Actualizar Empleado");
        updateButton.setBounds(190, 230, 150, 25);
        JButton deleteButton = new JButton("Eliminar Empleado");
        deleteButton.setBounds(350, 230, 150, 25);
        JButton listButton = new JButton("Listar Empleados");
        listButton.setBounds(510, 230, 150, 25);
        JButton searchButton = new JButton("Buscar Empleado");  // Agregar botón de búsqueda
        searchButton.setBounds(670, 230, 150, 25);

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
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(listButton);
        add(searchButton);  // Añadir el botón de búsqueda al panel

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();
                try {
                    String message = empleadoController.agregarEmpleado(new Empleado(nombre, apellido, direccion, telefono, email));
                    JOptionPane.showMessageDialog(null, message);  // Mostrar mensaje de éxito o error
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el empleado: ");
                }
                limpiarCampos();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();
                try {
                    String message = empleadoController.modificarEmpleado(new Empleado(nombre, apellido, direccion, telefono, email));
                    JOptionPane.showMessageDialog(null, message);  // Mostrar mensaje de éxito o error
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el empleado: " + ex.getMessage());
                }
                limpiarCampos();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                try {
                    String message = empleadoController.eliminarEmpleado(nombre, apellido);
                    JOptionPane.showMessageDialog(null, message);  // Mostrar mensaje de éxito o error
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el empleado: " + ex.getMessage());
                }
                limpiarCampos();
            }
        });


        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Empleado> empleados = null;
                try {
                    empleados = empleadoController.listarEmpleados();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                StringBuilder listado = new StringBuilder("Lista de Empleados:\n");
                for (Empleado empleado : empleados) {
                    listado.append(empleado).append("\n");
                }
                JOptionPane.showMessageDialog(null, listado.toString());
            }
        });

        searchButton.addActionListener(new ActionListener() {  // Acción para el botón de búsqueda
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText().trim();
                String apellido = apellidoField.getText().trim();

                // Verificar si los campos están vacíos
                if (nombre.isEmpty() && apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre y apellido para buscar.");
                    return;  // Salir del método si no hay datos para buscar
                }

                List<Empleado> empleados = null;
                try {
                    empleados = empleadoController.buscarEmpleados(nombre, apellido);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar el empleado: " + ex.getMessage());
                    return;  // Salir del método si hay un error de búsqueda
                }

                if (empleados == null || empleados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontraron empleados.");
                } else {
                    StringBuilder resultados = new StringBuilder("Resultados de la búsqueda:\n");
                    for (Empleado empleado : empleados) {
                        resultados.append(empleado).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, resultados.toString());
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
}
