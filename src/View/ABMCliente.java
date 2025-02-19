package View;

import Controller.ClienteController;
import Model.Entity.Cliente;
import Model.Entity.Membresia;
import Model.Entity.Membresia.TipoMembresia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ABMCliente extends JPanel {

    private JTextField idClienteField, nombreField, apellidoField, direccionField, telefonoField, emailField, dniField, edadField;
    private JCheckBox estadoPagoCheck;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private ClienteController clienteController;
    private JComboBox<TipoMembresia> tipoMembresiaComboBox;
    private JTextArea descripcionTextArea;

    public ABMCliente() {
        clienteController = new ClienteController();

        setSize(800, 600);
        setLayout(null);

        // Etiquetas y campos de texto
        JLabel idClienteLabel = new JLabel("ID Cliente:");
        idClienteLabel.setBounds(30, 30, 150, 25);
        idClienteField = new JTextField();
        idClienteField.setBounds(180, 30, 150, 25);
        idClienteField.setEditable(false);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(30, 70, 150, 25);
        nombreField = new JTextField();
        nombreField.setBounds(180, 70, 150, 25);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(30, 110, 150, 25);
        apellidoField = new JTextField();
        apellidoField.setBounds(180, 110, 150, 25);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionLabel.setBounds(30, 150, 150, 25);
        direccionField = new JTextField();
        direccionField.setBounds(180, 150, 150, 25);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoLabel.setBounds(30, 190, 150, 25);
        telefonoField = new JTextField();
        telefonoField.setBounds(180, 190, 150, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(30, 230, 150, 25);
        emailField = new JTextField();
        emailField.setBounds(180, 230, 150, 25);

        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setBounds(30, 270, 150, 25);
        dniField = new JTextField();
        dniField.setBounds(180, 270, 150, 25);

        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setBounds(30, 310, 150, 25);
        edadField = new JTextField();
        edadField.setBounds(180, 310, 150, 25);

        JLabel tipoMembresiaLabel = new JLabel("Tipo de Membresía:");
        tipoMembresiaLabel.setBounds(30, 350, 150, 25);

        // JComboBox para seleccionar tipo de membresía
        tipoMembresiaComboBox = new JComboBox<>(TipoMembresia.values());
        tipoMembresiaComboBox.setBounds(180, 350, 150, 25);

        // JTextArea para mostrar la descripción de la membresía
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionLabel.setBounds(30, 390, 150, 25);

        descripcionTextArea = new JTextArea();
        descripcionTextArea.setBounds(180, 390, 200, 50);
        descripcionTextArea.setEditable(false);
        descripcionTextArea.setLineWrap(true);
        descripcionTextArea.setWrapStyleWord(true);

        JLabel estadoPagoLabel = new JLabel("Estado de Pago:");
        estadoPagoLabel.setBounds(30, 460, 150, 25);
        estadoPagoCheck = new JCheckBox();
        estadoPagoCheck.setBounds(180, 460, 150, 25);

        // Botones
        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBounds(400, 70, 150, 40);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(400, 150, 150, 40);

        JButton modificarButton = new JButton("Modificar");
        modificarButton.setBounds(400, 230, 150, 40);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(400, 310, 150, 40);

        JButton listarButton = new JButton("Listar");
        listarButton.setBounds(400, 390, 150, 40);

        // Agregar tabla para listar clientes
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Direccion");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Membresia");
        modeloTabla.addColumn("Pago");

        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setBounds(30, 500, 700, 100);

        // Agregar componentes al panel
        add(idClienteLabel);
        add(idClienteField);
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
        add(edadLabel);
        add(edadField);
        add(tipoMembresiaLabel);
        add(tipoMembresiaComboBox);
        add(descripcionLabel);
        add(descripcionTextArea);
        add(estadoPagoLabel);
        add(estadoPagoCheck);
        add(agregarButton);
        add(buscarButton);
        add(modificarButton);
        add(eliminarButton);
        add(listarButton);
        add(scrollPane);

        // Acción para mostrar la descripción cuando cambie la selección en el JComboBox
        tipoMembresiaComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDescripcionMembresia();
            }
        });

        // Acción del botón listar
        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        // Acción del botón agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(nombreField.getText());
                    cliente.setApellido(apellidoField.getText());
                    cliente.setDni(dniField.getText());
                    cliente.setDireccion(direccionField.getText());
                    cliente.setTelefono(telefonoField.getText());
                    cliente.setEmail(emailField.getText());
                    cliente.setEdad(Integer.parseInt(edadField.getText()));
                    cliente.setEstadoPago(estadoPagoCheck.isSelected());

                    // Obtener tipo de membresía seleccionado
                    TipoMembresia tipoMembresiaSeleccionado = (TipoMembresia) tipoMembresiaComboBox.getSelectedItem();
                    cliente.setTipoMembresia(tipoMembresiaSeleccionado.toString()); // Asignar tipo de membresía al cliente

                    String resultado = clienteController.agregarCliente(cliente);
                    JOptionPane.showMessageDialog(null, resultado);
                    limpiarCampos();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + ex.getMessage());
                }
            }
        });

        // Acción del botón buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniField.getText().trim();
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un DNI para buscar.");
                    return;
                }

                List<Cliente> clientes = clienteController.buscarClientes(dni);
                if (clientes.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún cliente.");
                } else {
                    Cliente cliente = clientes.get(0);
                    idClienteField.setText(String.valueOf(cliente.getIdCliente()));
                    nombreField.setText(cliente.getNombre());
                    apellidoField.setText(cliente.getApellido());
                    dniField.setText(cliente.getDni());
                    direccionField.setText(cliente.getDireccion());
                    telefonoField.setText(cliente.getTelefono());
                    emailField.setText(cliente.getEmail());
                    edadField.setText(String.valueOf(cliente.getEdad()));

                }
            }
        });


        // Acción del botón modificar
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Verificar que el ID de cliente no sea nulo o vacío
                    if (idClienteField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Debe seleccionar un cliente antes de modificar.");
                        return;
                    }

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(Integer.parseInt(idClienteField.getText().trim())); // Conversión segura
                    cliente.setNombre(nombreField.getText().trim());
                    cliente.setApellido(apellidoField.getText().trim());
                    cliente.setDireccion(direccionField.getText().trim());
                    cliente.setTelefono(telefonoField.getText().trim());
                    cliente.setEmail(emailField.getText().trim());
                    cliente.setDni(dniField.getText().trim());
                    cliente.setEdad(Integer.parseInt(edadField.getText().trim()));
                    TipoMembresia tipoSeleccionado = (TipoMembresia) tipoMembresiaComboBox.getSelectedItem();
                    cliente.setTipoMembresia(tipoSeleccionado.name()); // Convierte a String el nombre de la enum

                    cliente.setEstadoPago(estadoPagoCheck.isSelected());

                    // Llamar al método de modificar en el controlador
                    String resultado = clienteController.modificarCliente(cliente);
                    JOptionPane.showMessageDialog(null, resultado);
                    limpiarCampos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: ID Cliente o Edad inválido.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar cliente: " + ex.getMessage());
                }
            }
        });

        // Acción del botón eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniField.getText().trim();
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un DNI para eliminar.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    String resultado = clienteController.eliminarClientePorDNI(dni);
                    JOptionPane.showMessageDialog(null, resultado);
                    limpiarCampos();
                }
            }
        });

    }

    // Limpiar campos después de agregar/modificar/eliminar
    private void limpiarCampos() {
        idClienteField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        emailField.setText("");
        dniField.setText("");
        edadField.setText("");
        tipoMembresiaComboBox.setSelectedIndex(0);
        descripcionTextArea.setText(""); // Limpiar la descripción
        estadoPagoCheck.setSelected(false);
    }

    // Listar todos los clientes
    private void listarClientes() {
        List<Cliente> clientes = clienteController.listarClientes();
        // Ordenar la lista de clientes por apellido en orden ascendente
        Collections.sort(clientes, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return c1.getApellido().compareTo(c2.getApellido());  // Ordenar por apellido
            }
        });
        // Limpiar tabla antes de cargar los datos
        modeloTabla.setRowCount(0);

        for (Cliente cliente : clientes) {
            modeloTabla.addRow(new Object[]{
                    cliente.getIdCliente(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getDni(),
                    cliente.getDireccion(),
                    cliente.getTelefono(),
                    cliente.getEmail(),
                    cliente.getEdad(),
                    cliente.getTipoMembresia(),
                    cliente.isEstadoPago() ? "Pagado" : "Pendiente"
            });
        }
    }

    // Actualiza la descripción en función del tipo de membresía seleccionado
    private void actualizarDescripcionMembresia() {
        TipoMembresia tipoSeleccionado = (TipoMembresia) tipoMembresiaComboBox.getSelectedItem();
        String descripcion = "";
        switch (tipoSeleccionado) {
            case SILVER:
                descripcion = "Acceso al gimnasio 3 veces por semana";
                break;
            case GOLDEN:
                descripcion = "Acceso libre al gimnasio";
                break;
            case DIAMANT:
                descripcion = "Acceso libre al gimnasio y a todas las actividades";
                break;
        }
        descripcionTextArea.setText(descripcion); // Actualiza el área de texto con la descripción
    }
}
