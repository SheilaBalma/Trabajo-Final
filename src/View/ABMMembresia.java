package View;

import Model.DAO.MembresiaDAO;
import Model.Entity.Membresia;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ABMMembresia extends JFrame {
    private MembresiaDAO membresiaDAO = new MembresiaDAO();

    private JComboBox<Membresia.TipoMembresia> tipoComboBox;
    private JTextArea descripcionTextArea;
    private JButton guardarButton;

    public ABMMembresia() {
        setTitle("ABM Membresia");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        tipoComboBox = new JComboBox<>(Membresia.TipoMembresia.values());
        descripcionTextArea = new JTextArea(5, 20);
        guardarButton = new JButton("Guardar");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Tipo de Membresia:"));
        panel.add(tipoComboBox);
        panel.add(new JLabel("Descripcion:"));
        panel.add(new JScrollPane(descripcionTextArea));
        panel.add(guardarButton);

        add(panel);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMembresia();
            }
        });
    }

    private void guardarMembresia() {
        Membresia.TipoMembresia tipo = (Membresia.TipoMembresia) tipoComboBox.getSelectedItem();
        Membresia membresia = new Membresia(tipo);
        membresiaDAO.guardarMembresia(membresia);
        JOptionPane.showMessageDialog(this, "Membresia guardada exitosamente");
    }
}

