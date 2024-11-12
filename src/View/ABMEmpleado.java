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
    private JTextField dniField;  // Nuevo campo para el DNI
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

        JLabel dniLabel = new JLabel("DNI:"); // Etiqueta para DNI
        dniLabel.setBounds(30, 230, 150, 25);
        dniField = new JTextField();
        dniField.setBounds(180, 230, 150, 25);

        // Botones para acciones
        JButton addButton = new JButton("Agregar Empleado");
        addButton.setBounds(30, 270, 150, 25);
        JButton updateButton = new JButton("Actualizar Empleado");
        updateButton.setBounds(190, 270, 150, 25);
        JButton deleteButton = new JButton("Eliminar Empleado");
        deleteButton.setBounds(350, 270, 150, 25);
        JButton listButton = new JButton("Listar Empleados");
        listButton.setBounds(510, 270, 150, 25);
        JButton searchButton = new JButton("Buscar Empleado");
        searchButton.setBounds(670, 270, 150, 25);

        // Área de texto para mostrar resultados
        resultadoArea = new JTextArea();
        resultadoArea.setBounds(30, 310, 790, 150);
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
        add(dniLabel);
        add(dniField);
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
                        emailField.getText().isEmpty() || dniField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    return;
                }

                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();
                String dni = dniField.getText();

                try {
                    Empleado nuevoEmpleado = new Empleado(nombre, apellido, direccion, telefono, email, dni);
                    empleadoController.agregarEmpleado(nuevoEmpleado);
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
                if (dniField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El DNI es obligatorio para actualizar.");
                    return;
                }

                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String email = emailField.getText();
                String dni = dniField.getText();

                try {
                    Empleado empleadoActualizado = new Empleado(nombre, apellido, direccion, telefono, email, dni);
                    empleadoController.modificarEmpleado(empleadoActualizado);
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
                if (dniField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El DNI es obligatorio para eliminar.");
                    return;
                }

                String dni = dniField.getText();

                try {
                    empleadoController.eliminarEmpleadoPorDni(dni);
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
                    resultadoArea.setText(""); // Limpiar el área de resultados
                    for (Empleado emp : empleados) {
                        resultadoArea.append(emp.toString() + "\n");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al listar empleados: " + ex.getMessage());
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniField.getText();
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un DNI para buscar.");
                    return;
                }

                try {
                    Empleado empleado = empleadoController.buscarEmpleadoPorDni(dni);
                    if (empleado != null) {
                        // Rellenar los campos con los datos del empleado encontrado
                        nombreField.setText(empleado.getNombre());
                        apellidoField.setText(empleado.getApellido());
                        direccionField.setText(empleado.getDireccion());
                        telefonoField.setText(empleado.getTelefono());
                        emailField.setText(empleado.getEmail());
                        dniField.setText(empleado.getDni());  // Asegúrate de que el DNI también se muestra

                        // Mostrar los detalles del empleado en el área de resultados
                        resultadoArea.setText("Empleado encontrado:\n" + empleado.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró un empleado con ese DNI.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar el empleado: " + ex.getMessage());
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
        dniField.setText(""); // Limpiar el campo DNI también
    }
}


