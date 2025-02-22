package View;

import Controller.MembresiaController;
import Model.Entity.Membresia;
import Model.Entity.Membresia.TipoMembresia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ABMMembresia extends JPanel {

    private JComboBox<TipoMembresia> tipoComboBox;
    private JTextArea descripcionTextArea;
    private JButton guardarButton;
    private MembresiaController membresiaController;

    public ABMMembresia() {
        this.setSize(400, 300);
        this.initComponents();
    }

    private void initComponents() {
        // Crear el JComboBox con los valores de TipoMembresia
        this.tipoComboBox = new JComboBox<>(TipoMembresia.values());

        // Crear el JTextArea para mostrar la descripción de la membresía
        this.descripcionTextArea = new JTextArea(5, 20);
        this.descripcionTextArea.setEditable(false); // Para que no sea editable

        // Crear el botón de guardar
        this.guardarButton = new JButton("Guardar");

        // Crear un panel para agregar los componentes
        JPanel panel = new JPanel();
        panel.add(new JLabel("Tipo de Membresía:"));
        panel.add(this.tipoComboBox);
        panel.add(new JLabel("Descripción:"));

        // Agregar el JTextArea dentro de un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(this.descripcionTextArea);
        panel.add(scrollPane);

        panel.add(this.guardarButton);
        this.add(panel);

        // Acción del botón de Guardar
        this.guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarMembresia();
            }
        });

        // Actualizar la descripción según el tipo de membresía seleccionado
        this.tipoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDescripcion();
            }
        });
    }

    private void guardarMembresia() {
        // Obtener el tipo seleccionado del JComboBox
        TipoMembresia tipo = (TipoMembresia) this.tipoComboBox.getSelectedItem();

        // Crear la nueva membresía con el tipo seleccionado
        Membresia membresia = new Membresia(tipo);

        // Guardar la membresía en la base de datos
        this.membresiaController.guardarMembresia(membresia);

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Membresía guardada exitosamente");
    }

    // Actualiza la descripción del JTextArea según el tipo de membresía seleccionado
    private void actualizarDescripcion() {
        TipoMembresia tipo = (TipoMembresia) this.tipoComboBox.getSelectedItem();
        Membresia membresia = new Membresia(tipo);
        this.descripcionTextArea.setText(membresia.getDescripcion());
    }
}
