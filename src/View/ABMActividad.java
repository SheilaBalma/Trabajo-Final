package View;

import Model.DAO.ActividadDAO;
import Model.Entity.Actividad;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ABMActividad extends JFrame {
    private ActividadDAO actividadDAO = new ActividadDAO();

    private JComboBox<Actividad.TipoActividad> tipoComboBox;
    private JTextArea descripcionTextArea;
    private JButton guardarButton;

    public ABMActividad() {
        setTitle("ABM Actividad");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        tipoComboBox = new JComboBox<>(Actividad.TipoActividad.values());
        descripcionTextArea = new JTextArea(5, 20);
        guardarButton = new JButton("Guardar");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Tipo de Actividad:"));
        panel.add(tipoComboBox);
        panel.add(new JLabel("Descripcion:"));
        panel.add(new JScrollPane(descripcionTextArea));
        panel.add(guardarButton);

        add(panel);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarActividad();
            }
        });
    }

    private void guardarActividad() {
        Actividad.TipoActividad tipo = (Actividad.TipoActividad) tipoComboBox.getSelectedItem();
        Actividad actividad = new Actividad(tipo);
        actividadDAO.guardarActividad(actividad);
        JOptionPane.showMessageDialog(this, "Actividad guardada exitosamente");
    }
}

