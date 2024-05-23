import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolozkaDialog extends JDialog {
    private final JTextField objem = new JTextField(10);
    private final JTextField barva = new JTextField(15);
    private final JTextField cena = new JTextField(10);
    private final JTextField dph = new JTextField(10);
    private final JButton btnOK;
    private boolean ok = false;

    public PolozkaDialog(Frame owner) {
        super(owner, "Edituj polozku", true);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel labelObjem = new JLabel("Objem (ml)");
        labelObjem.setLabelFor(objem);
        center.add(labelObjem);
        center.add(objem);

        JLabel labelBarva = new JLabel("Barva");
        labelObjem.setLabelFor(barva);
        center.add(labelBarva);
        center.add(barva);

        JLabel labelCena = new JLabel("Cena");
        labelObjem.setLabelFor(cena);
        center.add(labelCena);
        center.add(cena);

        JLabel labelDPH = new JLabel("DPH (%)");
        labelObjem.setLabelFor(dph);
        center.add(labelDPH);
        center.add(dph);



        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnOK = new JButton("OK");
        JButton btnStorno = new JButton("Storno");

        buttons.add(btnOK);
        buttons.add(btnStorno);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok = e.getSource() == btnOK;
                setVisible(false);
            }
        };

        btnOK.addActionListener(listener);
        btnStorno.addActionListener(listener);


        getRootPane().setDefaultButton(btnOK);

        add(buttons, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);


        pack();
    }

    public Lahev vytvorNovouPolozku() {
        objem.setText("");
        barva.setText("");
        cena.setText("");
        dph.setText("");
        setVisible(true);
        if (ok) {
            return new Lahev(Integer.parseInt(objem.getText()), barva.getText(), Integer.parseInt(cena.getText()), Integer.parseInt(dph.getText()));
        } else {
            return null;
        }
    }
}
