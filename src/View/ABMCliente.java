package View;

import Controller.ClienteController;
import Model.Entity.Cliente;
import Model.Entity.Membresia;
import Model.Entity.Pago;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ABMCliente extends JPanel {

    private JTextField idClienteField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField emailField;
    private JTextField dniField;
    private JTextField edadField;
    private JComboBox<Membresia.TipoMembresia> tipoMembresiaComboBox;
    private JComboBox<Pago.MetodoPago> metodoPagoComboBox;
    private JCheckBox estadoPagoCheck;
    private ClienteController clienteController;

    public ABMCliente() {
        clienteController = new ClienteController();
        setLayout(null);

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
        tipoMembresiaComboBox = new JComboBox<>(Membresia.TipoMembresia.values());
        tipoMembresiaComboBox.setBounds(180, 350, 150, 25);

        JLabel metodoPagoLabel = new JLabel("Método de Pago:");
        metodoPagoLabel.setBounds(30, 390, 150, 25);
        metodoPagoComboBox = new JComboBox<>(Pago.MetodoPago.values());
        metodoPagoComboBox.setBounds(180, 390, 150, 25);

        JLabel estadoPagoLabel = new JLabel("Estado de Pago:");
        estadoPagoLabel.setBounds(30, 430, 150, 25);
        estadoPagoCheck = new JCheckBox();
        estadoPagoCheck.setBounds(180, 430, 150, 25);

        JButton addButton = new JButton("Agregar Cliente");
        addButton.setBounds(30, 470, 150, 25);
        JButton updateButton = new JButton("Actualizar Cliente");
        updateButton.setBounds(190, 470, 150, 25);
        JButton deleteButton = new JButton("Eliminar Cliente");
        deleteButton.setBounds(350, 470, 150, 25);
        JButton listButton = new JButton("Listar Clientes");
        listButton.setBounds(510, 470, 150, 25);
        JButton searchButton = new JButton("Buscar Cliente");
        searchButton.setBounds(670, 470, 150, 25);

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
        add(metodoPagoLabel);
        add(metodoPagoComboBox);
        add(estadoPagoLabel);
        add(estadoPagoCheck);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(listButton);
        add(searchButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniField.getText();

                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa un DNI.");
                    return;
                }

                try {
                    if (clienteController.existeClientePorDNI(dni)) {
                        JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese DNI.");
                        return;
                    }

                    Cliente cliente = new Cliente(
                            nombreField.getText(),
                            apellidoField.getText(),
                            direccionField.getText(),
                            telefonoField.getText(),
                            emailField.getText(),
                            dni,
                            Integer.parseInt(edadField.getText()),
                            (Membresia.TipoMembresia) tipoMembresiaComboBox.getSelectedItem(),
                            (Pago.MetodoPago) metodoPagoComboBox.getSelectedItem(),
                            estadoPagoCheck.isSelected()
                    );

                    clienteController.agregarCliente(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente agregado correctamente.");
                    limpiarCampos();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el cliente: " + ex.getMessage());
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente cliente = new Cliente(
                        Integer.parseInt(idClienteField.getText()),
                        nombreField.getText(),
                        apellidoField.getText(),
                        direccionField.getText(),
                        telefonoField.getText(),
                        emailField.getText(),
                        dniField.getText(),
                        Integer.parseInt(edadField.getText()),
                        (Membresia.TipoMembresia) tipoMembresiaComboBox.getSelectedItem(),
                        (Pago.MetodoPago) metodoPagoComboBox.getSelectedItem(),
                        estadoPagoCheck.isSelected()
                );
                try {
                    clienteController.modificarCliente(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + ex.getMessage());
                }
                limpiarCampos();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clienteController.eliminarClientePorDNI(dniField.getText());
                    JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + ex.getMessage());
                }
                limpiarCampos();
            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Cliente> clientes;
                try {
                    clientes = clienteController.listarClientes();
                    StringBuilder listado = new StringBuilder("Lista de Clientes:\n");
                    for (Cliente cliente : clientes) {
                        listado.append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, listado.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al listar los clientes: " + ex.getMessage());
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dni = dniField.getText();
                if (!dni.isEmpty()) {
                    try {
                        List<Cliente> clientes = clienteController.buscarClientePorDNI(dni);
                        if (!clientes.isEmpty()) {
                            StringBuilder resultados = new StringBuilder("Resultados de la búsqueda:\n");
                            for (Cliente cliente : clientes) {
                                resultados.append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, resultados.toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontraron clientes con ese DNI.");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al buscar el cliente: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa un DNI para buscar.");
                }
            }
        });
    }

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
        metodoPagoComboBox.setSelectedIndex(0);
        estadoPagoCheck.setSelected(false);
    }
}

