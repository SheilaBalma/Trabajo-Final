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
    private JTextArea resultadoArea;
    private EmpleadoController empleadoController;

    public ABMEmpleado() {
        empleadoController = new EmpleadoController();
        setLayout(null);

        // Configuración de etiquetas y campos de texto
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

        // Botones para acciones
        JButton addButton = new JButton("Agregar Empleado");
        addButton.setBounds(30, 230, 150, 25);
        JButton updateButton = new JButton("Actualizar Empleado");
        updateButton.setBounds(190, 230, 150, 25);
        JButton deleteButton = new JButton("Eliminar Empleado");
        deleteButton.setBounds(350, 230, 150, 25);
        JButton listButton = new JButton("Listar Empleados");
        listButton.setBounds(510, 230, 150, 25);
        JButton searchButton = new JButton("Buscar Empleado");
        searchButton.setBounds(670, 230, 150, 25);

        // Área de texto para mostrar resultados
        resultadoArea = new JTextArea();
        resultadoArea.setBounds(30, 270, 790, 150);
        resultadoArea.setEditable(false);

        // Añadir componentes al panel
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
        add(searchButton);
        add(resultadoArea);

        // Acción para agregar empleado
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombreField.getText().isEmpty() || apellidoField.getText().isEmpty() ||
                        direccionField.getText().isEmpty() || telefonoField.getText().isEmpty() ||
                        emailField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }

                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();

                try {
                    empleadoController.agregarEmpleado(new Empleado(nombre, apellido, direccion, telefono, email));
                    JOptionPane.showMessageDialog(null, "Empleado agregado con éxito.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el empleado: " + ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                limpiarCampos();
            }
        });

        // Acción para actualizar empleado
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombreField.getText().isEmpty() || apellidoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nombre y Apellido son obligatorios para actualizar.");
                    return;
                }

                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();

                try {
                    empleadoController.modificarEmpleado(new Empleado(nombre, apellido, direccion, telefono, email));
                    JOptionPane.showMessageDialog(null, "Empleado actualizado con éxito.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar el empleado: " + ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                limpiarCampos();
            }
        });

        // Acción para eliminar empleado
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombreField.getText().isEmpty() || apellidoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nombre y Apellido son obligatorios para eliminar.");
                    return;
                }

                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();

                try {
                    empleadoController.eliminarEmpleado(nombre, apellido);
                    JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el empleado: " + ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                limpiarCampos();
            }
        });

        // Acción para listar empleados
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Empleado> empleados = empleadoController.listarEmpleados();
                    if (empleados.isEmpty()) {
                        resultadoArea.setText("No se encontraron empleados.");
                    } else {
                        mostrarResultados(empleados);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al listar empleados: " + ex.getMessage());
                }
            }
        });

        // Acción para buscar empleado
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                try {
                    List<Empleado> empleados = empleadoController.buscarEmpleado(nombre, apellido);
                    if (empleados.isEmpty()) {
                        resultadoArea.setText("No se encontró ningún empleado con ese nombre y apellido.");
                    } else {
                        mostrarResultados(empleados);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar empleado: " + ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
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

    // Método para mostrar los resultados en resultadoArea
    private void mostrarResultados(List<Empleado> empleados) {
        StringBuilder resultados = new StringBuilder();
        for (Empleado empleado : empleados) {
            resultados.append("Nombre: ").append(empleado.getNombre())
                    .append(", Apellido: ").append(empleado.getApellido())
                    .append(", Dirección: ").append(empleado.getDireccion())
                    .append(", Teléfono: ").append(empleado.getTelefono())
                    .append(", Email: ").append(empleado.getEmail())
                    .append("\n");
        }
        resultadoArea.setText(resultados.toString());
    }
}

