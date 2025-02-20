package View;

import Controller.EmpleadoController;
import Model.Entity.Actividad;
import Model.Entity.Cliente;
import Model.Entity.Empleado;
import Model.Entity.Membresia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ABMEmpleado extends JPanel {
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField emailField;
    private JTextField dniField;

    private JTable empleadosTable; // Tabla para mostrar los empleados
    private DefaultTableModel tableModel; // Modelo de la tabla

    private EmpleadoController empleadoController;

    public ABMEmpleado() {
        empleadoController = new EmpleadoController(); // Instancia del controlador

        setSize(800, 600);
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

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(30, 230, 150, 25);
        dniField = new JTextField();
        dniField.setBounds(180, 230, 150, 25);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBounds(400, 30, 150, 40);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(400, 90, 150, 40);

        JButton modificarButton = new JButton("Modificar");
        modificarButton.setBounds(400, 150, 150, 40);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(400, 210, 150, 40);

        JButton listarButton = new JButton("Listar Empleados");
        listarButton.setBounds(400, 270, 150, 40); // Botón para listar empleados

        // Botón Limpiar
        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.setBounds(600, 150, 150, 40);

        // Crear la tabla de empleados
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Apellido");
        tableModel.addColumn("Dirección");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Email");
        tableModel.addColumn("DNI");

        empleadosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(empleadosTable);
        scrollPane.setBounds(30, 350, 700, 250); // Ubicación y tamaño de la tabla
        add(scrollPane);

        // Añadir los componentes al JPanel
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
        add(agregarButton);
        add(buscarButton);
        add(modificarButton);
        add(eliminarButton);
        add(listarButton); // Agregar botón de listar empleados
        add(limpiarButton);

        // Funcionalidad del botón agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validar que todos los campos estén completos
                    if (nombreField.getText().trim().isEmpty() || apellidoField.getText().trim().isEmpty() ||
                            direccionField.getText().trim().isEmpty() || telefonoField.getText().trim().isEmpty() ||
                            emailField.getText().trim().isEmpty() || dniField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Todos los campos deben estar completos.");
                        return;
                    }

                    // Validar Teléfono (solo números)
                    String telefono = telefonoField.getText().trim();
                    if (!telefono.matches("\\d+")) {  // Solo números
                        JOptionPane.showMessageDialog(null, "El teléfono debe contener solo números.");
                        return;
                    }

                    // Validar DNI (solo números)
                    String dni = dniField.getText().trim();
                    if (!dni.matches("\\d+")) {  // Solo números
                        JOptionPane.showMessageDialog(null, "El DNI debe contener solo números.");
                        return;
                    }

                    // Crear el objeto Empleado con los datos ingresados
                    Empleado empleado = new Empleado(
                            nombreField.getText(),
                            apellidoField.getText(),
                            direccionField.getText(),
                            telefono,
                            emailField.getText(),
                            dni // Agregar el DNI
                    );

                    // Llamar al controlador para agregar el empleado
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
                String dni = dniField.getText().trim();
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un DNI para buscar.");
                    return;
                }

                List<Empleado> empleados = empleadoController.buscarEmpleados(dni);
                if (empleados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún empleado.");
                } else {
                    Empleado empleado = empleados.get(0);
                    nombreField.setText(empleado.getNombre());
                    apellidoField.setText(empleado.getApellido());
                    dniField.setText(empleado.getDni());
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
                    // Validar que todos los campos estén completos
                    if (nombreField.getText().trim().isEmpty() || apellidoField.getText().trim().isEmpty() ||
                            direccionField.getText().trim().isEmpty() || telefonoField.getText().trim().isEmpty() ||
                            emailField.getText().trim().isEmpty() || dniField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Todos los campos deben estar completos.");
                        return;
                    }

                    // Validar Teléfono (solo números)
                    String telefono = telefonoField.getText().trim();
                    if (!telefono.matches("\\d+")) {  // Solo números
                        JOptionPane.showMessageDialog(null, "El teléfono debe contener solo números.");
                        return;
                    }

                    // Validar DNI (solo números)
                    String dni = dniField.getText().trim();
                    if (!dni.matches("\\d+")) {  // Solo números
                        JOptionPane.showMessageDialog(null, "El DNI debe contener solo números.");
                        return;
                    }

                    // Crear el objeto Empleado con los datos ingresados
                    Empleado empleado = new Empleado(
                            nombreField.getText(),
                            apellidoField.getText(),
                            direccionField.getText(),
                            telefono,
                            emailField.getText(),
                            dni
                    );

                    // Llamar al controlador para modificar el empleado
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
                String dni = dniField.getText().trim();
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un DNI para eliminar.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar empleado?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String resultado = empleadoController.eliminarEmpleado(dni);
                    JOptionPane.showMessageDialog(null, resultado);
                    limpiarCampos();
                }
            }
        });

        // Funcionalidad del botón listar empleados
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                     List<Empleado> empleados = empleadoController.listarEmpleados();
                    // Ordenar la lista de clientes por apellido en orden ascendente
                    Collections.sort(empleados, new Comparator<Empleado>() {
                        @Override
                        public int compare(Empleado c1, Empleado c2) {
                            return c1.getApellido().compareTo(c2.getApellido());  // Ordenar por apellido
                        }
                    });
                    // Limpiar tabla antes de cargar los datos
                    tableModel.setRowCount(0);
                    // Llenar la tabla con los empleados
                    for (Empleado empleado : empleados) {
                        tableModel.addRow(new Object[]{
                                empleado.getNombre(),
                                empleado.getApellido(),
                                empleado.getDirección(),
                                empleado.getTelefono(),
                                empleado.getEmail(),
                                empleado.getDni()
                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al listar empleados: " + ex.getMessage());
                }
            }
        });

        // Acción del botón Limpiar
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();  // Llama a la función que limpia todos los campos
            }
        });
    }

    private void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        emailField.setText("");
        dniField.setText(""); // Limpiar el campo DNI
    }

    public static void main(String[] args) {
        ABMEmpleado abmEmpleado = new ABMEmpleado();
        abmEmpleado.setVisible(true);
    }
}
