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

        addButton.addActionListener(e -> agregarCliente());
        updateButton.addActionListener(e -> actualizarCliente());
        deleteButton.addActionListener(e -> eliminarCliente());
        listButton.addActionListener(e -> listarClientes());
        searchButton.addActionListener(e -> buscarCliente());
    }

    private void agregarCliente() {
        String dni = dniField.getText().trim();

        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingresa un DNI.");
            return;
        }

        try {
            if (clienteController.existeClientePorDNI(dni)) {
                JOptionPane.showMessageDialog(this, "Ya existe un cliente con ese DNI.");
                return;
            }

            Cliente cliente = new Cliente(
                    nombreField.getText().trim(),
                    apellidoField.getText().trim(),
                    direccionField.getText().trim(),
                    telefonoField.getText().trim(),
                    emailField.getText().trim(),
                    dni,
                    Integer.parseInt(edadField.getText().trim()), // Validar este campo
                    (Membresia.TipoMembresia) tipoMembresiaComboBox.getSelectedItem(),
                    (Pago.MetodoPago) metodoPagoComboBox.getSelectedItem(),
                    estadoPagoCheck.isSelected()
            );

            clienteController.agregarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado correctamente.");
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un número válido.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar el cliente: " + ex.getMessage());
        }
    }

    private void listarClientes() {
        try {
            List<Cliente> clientes = clienteController.listarClientes();
            StringBuilder listado = new StringBuilder("Lista de Clientes:\n");
            for (Cliente cliente : clientes) {
                listado.append("ID: ").append(cliente.getIdCliente()).append(", ")
                        .append("Nombre: ").append(cliente.getNombre()).append(" ")
                        .append(cliente.getApellido()).append("\n");
            }
            JOptionPane.showMessageDialog(this, listado.toString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al listar los clientes: " + ex.getMessage());
        }
    }
    private void eliminarCliente() {
        String dni = dniField.getText();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingresa el DNI del cliente a eliminar.");
            return;
        }

        try {
            clienteController.eliminarClientePorDNI(dni);  // Usa el nombre correcto del método en ClienteController
            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + ex.getMessage());
        }
    }


    private void buscarCliente() {
        String dni = dniField.getText().trim();
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el DNI del cliente a buscar.");
            return;
        }

        try {
            List<Cliente> clientes = clienteController.obtenerClientePorDNI(dni); // Devuelve una lista (normalmente 1 o 0 resultados)
            if (!clientes.isEmpty()) {
                Cliente cliente = clientes.get(0); // Tomar el primer resultado
                // Llenar los campos con los datos del cliente
                idClienteField.setText(String.valueOf(cliente.getIdCliente()));
                nombreField.setText(cliente.getNombre());
                apellidoField.setText(cliente.getApellido());
                direccionField.setText(cliente.getDireccion());
                telefonoField.setText(cliente.getTelefono());
                emailField.setText(cliente.getEmail());
                dniField.setText(cliente.getDni());
                edadField.setText(String.valueOf(cliente.getEdad()));
                tipoMembresiaComboBox.setSelectedItem(cliente.getTipoMembresia());
                metodoPagoComboBox.setSelectedItem(cliente.getMetodoPago());
                estadoPagoCheck.setSelected(cliente.isEstadoPago());

                JOptionPane.showMessageDialog(this, "Cliente encontrado. Puedes actualizar los datos.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ningún cliente con el DNI ingresado.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar el cliente: " + ex.getMessage());
        }
    }

    private void actualizarCliente() {
        String dni = dniField.getText().trim();

        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, busca un cliente por DNI antes de actualizar.");
            return;
        }

        try {
            // Crear un cliente con los datos modificados
            Cliente cliente = new Cliente(
                    Integer.parseInt(idClienteField.getText().trim()), // ID
                    nombreField.getText().trim(),
                    apellidoField.getText().trim(),
                    direccionField.getText().trim(),
                    telefonoField.getText().trim(),
                    emailField.getText().trim(),
                    dni,
                    Integer.parseInt(edadField.getText().trim()),
                    (Membresia.TipoMembresia) tipoMembresiaComboBox.getSelectedItem(),
                    (Pago.MetodoPago) metodoPagoComboBox.getSelectedItem(),
                    estadoPagoCheck.isSelected()
            );

            clienteController.modificarCliente(cliente); // Actualizar en la base de datos
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
            limpiarCampos(); // Limpiar el formulario
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente: " + ex.getMessage());
        }
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


