package View;

import Controller.ClienteController;
import Model.Entity.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ABMCliente extends JFrame {

    private JTextField idClienteField;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField emailField;
    private JTextField dniField;
    private JTextField edadField;
    private JTextField tipoMembresiaField;
    private JCheckBox estadoPagoCheck;

    private ClienteController clienteController;

    public ABMCliente() {
        clienteController = new ClienteController(); // Instancia del controlador

        setTitle("ABM Cliente");
        setSize(800, 600); // Ajustamos el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Usamos diseño absoluto para control manual de componentes

        // Inicializar componentes visuales
        JLabel idClienteLabel = new JLabel("ID Cliente:");
        idClienteLabel.setBounds(30, 30, 150, 25);
        idClienteField = new JTextField();
        idClienteField.setBounds(180, 30, 150, 25);
        idClienteField.setEditable(false); // El ID es autogenerado, por eso no es editable.

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
        tipoMembresiaField = new JTextField();
        tipoMembresiaField.setBounds(180, 350, 150, 25);

        JLabel estadoPagoLabel = new JLabel("Estado de Pago:");
        estadoPagoLabel.setBounds(30, 390, 150, 25);
        estadoPagoCheck = new JCheckBox();
        estadoPagoCheck.setBounds(180, 390, 150, 25);

        JButton agregarButton = new JButton("Agregar");
        agregarButton.setBounds(400, 70, 150, 40);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.setBounds(400, 150, 150, 40);

        JButton modificarButton = new JButton("Modificar");
        modificarButton.setBounds(400, 230, 150, 40);

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(400, 310, 150, 40);

        // Añadir componentes al JFrame
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
        add(tipoMembresiaField);
        add(estadoPagoLabel);
        add(estadoPagoCheck);
        add(agregarButton);
        add(buscarButton);
        add(modificarButton);
        add(eliminarButton);

        // Funcionalidad del botón agregar
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(nombreField.getText());
                    cliente.setApellido(apellidoField.getText());
                    cliente.setDireccion(direccionField.getText());
                    cliente.setTelefono(telefonoField.getText());
                    cliente.setEmail(emailField.getText());
                    cliente.setDni(dniField.getText());
                    cliente.setEdad(Integer.parseInt(edadField.getText()));
                    cliente.setTipoMembresia(tipoMembresiaField.getText());
                    cliente.setEstadoPago(estadoPagoCheck.isSelected());

                    String resultado = clienteController.agregarCliente(cliente);
                    JOptionPane.showMessageDialog(null, resultado);

                    limpiarCampos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar cliente: " + ex.getMessage());
                }
            }
        });

        // Funcionalidad del botón buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText().trim();
                String dni = dniField.getText().trim();

                if (nombre.isEmpty() && dni.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre o DNI para buscar.");
                    return;
                }

                List<Cliente> clientes = clienteController.buscarClientes(nombre, dni);

                if (clientes == null || clientes.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún cliente.");
                } else {
                    Cliente cliente = clientes.get(0); // Mostrar el primer resultado encontrado
                    idClienteField.setText(String.valueOf(cliente.getIdCliente()));
                    nombreField.setText(cliente.getNombre());
                    apellidoField.setText(cliente.getApellido());
                    direccionField.setText(cliente.getDireccion());
                    telefonoField.setText(cliente.getTelefono());
                    emailField.setText(cliente.getEmail());
                    dniField.setText(cliente.getDni());
                    edadField.setText(String.valueOf(cliente.getEdad()));
                    tipoMembresiaField.setText(cliente.getTipoMembresia());
                    estadoPagoCheck.setSelected(cliente.isEstadoPago());
                }
            }
        });

        // Funcionalidad del botón modificar
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (idClienteField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe buscar y seleccionar un cliente antes de modificar.");
                        return;
                    }

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(Integer.parseInt(idClienteField.getText())); // Modificación por ID
                    cliente.setNombre(nombreField.getText());
                    cliente.setApellido(apellidoField.getText());
                    cliente.setDireccion(direccionField.getText());
                    cliente.setTelefono(telefonoField.getText());
                    cliente.setEmail(emailField.getText());
                    cliente.setDni(dniField.getText());
                    cliente.setEdad(Integer.parseInt(edadField.getText()));
                    cliente.setTipoMembresia(tipoMembresiaField.getText());
                    cliente.setEstadoPago(estadoPagoCheck.isSelected());

                    String resultado = clienteController.modificarCliente(cliente);
                    JOptionPane.showMessageDialog(null, resultado);

                    limpiarCampos(); // Limpia los campos después de modificar
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al modificar cliente: " + ex.getMessage());
                }
            }
        });

        // Funcionalidad del botón eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dni = dniField.getText().trim();

                    if (dni.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un DNI para eliminar.");
                        return;
                    }

                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el cliente con DNI: " + dni + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        String resultado = clienteController.eliminarClientePorDNI(dni);
                        JOptionPane.showMessageDialog(null, resultado);
                        limpiarCampos(); // Limpia los campos tras la eliminación
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + ex.getMessage());
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
        tipoMembresiaField.setText("");
        estadoPagoCheck.setSelected(false);
    }

    public static void main(String[] args) {
        ABMCliente abmCliente = new ABMCliente();
        abmCliente.setVisible(true);
    }
}









