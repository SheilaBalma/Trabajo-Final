package View;

import Controller.ActividadController;
import Model.Entity.Actividad;
import Model.Entity.Actividad.TipoActividad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ABMActividad extends JPanel {
    private ActividadController actividadController;
    private JComboBox<TipoActividad> tipoComboBox;
    private JButton guardarButton;

    public ABMActividad() {
        this.setSize(400, 200);
        this.initComponents();
    }

    private void initComponents() {
        this.tipoComboBox = new JComboBox<>(TipoActividad.values());
        this.guardarButton = new JButton("Guardar");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Tipo de Actividad:"));
        panel.add(this.tipoComboBox);
        panel.add(this.guardarButton);
        this.add(panel);

        this.guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarActividad();
            }
        });
    }

    private void guardarActividad() {
        TipoActividad tipo = (TipoActividad) this.tipoComboBox.getSelectedItem();
        Actividad actividad = new Actividad(tipo);

        this.actividadController.guardarActividad(actividad);
        JOptionPane.showMessageDialog(this, "Actividad guardada exitosamente.");
    }
}
