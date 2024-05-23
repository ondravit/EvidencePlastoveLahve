import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainFrame extends JFrame {
    JTextArea txt = new JTextArea();
    Evidence evidence = new Evidence();
    public MainFrame() {
        setTitle("Evidence plastových lahví");
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLayout(new BorderLayout());
        setVisible(true);

        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);

        JButton btnNovaPolozka = new JButton("Nova polozka");
        btnNovaPolozka.addActionListener((e -> {
            novaPolozka();
        }));
        JButton btnVypispolozek = new JButton("Vypsat");
        btnVypispolozek.addActionListener((e -> {
            vypisPolozek();
        }));

        JButton btnSmazat = new JButton("Smazat");
        btnSmazat.addActionListener((e -> {
            smazatPolozku();
        }));

        JButton btnCenaLahvi = new JButton("Prumerna Cena");
        btnCenaLahvi.addActionListener((e -> {
            spocitejCenu();
        }));

        JButton btnCenaLahviSDph = new JButton("Prumerna Cena s DPH");
        btnCenaLahviSDph.addActionListener((e -> {
            spocitejCenuSDPH();
        }));

        JButton exportTxt = new JButton("Exportovat");
        exportTxt.addActionListener((e -> {
            export();
        }));

        JButton exportJSON = new JButton("Exportovat JSON");
        exportJSON.addActionListener((e -> {
            JSONCode.saveJSON(evidence.getPolozky());
        }));


        toolBar.add(btnNovaPolozka);
        toolBar.add(btnVypispolozek);
        toolBar.add(btnSmazat);
        toolBar.add(Box.createRigidArea(new Dimension(40, 0)));
        toolBar.add(btnCenaLahvi);
        toolBar.add(btnCenaLahviSDph);
        toolBar.add(Box.createRigidArea(new Dimension(40, 0)));
        toolBar.add(exportTxt);
        toolBar.add(exportJSON);



        add(toolBar, BorderLayout.NORTH);
        add(txt, BorderLayout.CENTER);


    }



    private void export() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save As");
        fileChooser.setFileFilter(new FileNameExtensionFilter("TXT Files (*.txt)", "txt"));
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".txt")) {
                filePath += ".txt";
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                String svgCode = generateTxt();
                writer.write(svgCode);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String generateTxt() {
        StringBuilder text = new StringBuilder();
        for (Lahev a:evidence.getPolozky()) {
            text.append(String.format("%dml\t %s\t %d,-\t %d", a.getObjem(), a.getBarva(), a.getCena(), a.getDph())+"%\n");
        }
        return text.toString();
    }


    public void novaPolozka() {
        PolozkaDialog dlg = new PolozkaDialog(MainFrame.this);
        Lahev p = dlg.vytvorNovouPolozku();
        if (p != null) {
            evidence.pridatPolozku(p);
        }

        txt.setText("");
        for (Lahev l:evidence.getPolozky()) {
            txt.append(String.format("%dml\t %s\t %d,-\t %d", l.getObjem(), l.getBarva(), l.getCena(), l.getDph())+"%\n");
        }
    }

    public void vypisPolozek() {
        txt.setText("");
        for (Lahev l:evidence.getPolozky()) {
            txt.append(String.format("%dml\t %s\t %d,-\t %d", l.getObjem(), l.getBarva(), l.getCena(), l.getDph())+"%\n");
        }
    }

    private void smazatPolozku() {
        evidence.smazPolozku();
        vypisPolozek();
    }

    public void spocitejCenu(){
        int cena = 0;
        for (Lahev l: evidence.getPolozky()) {
            cena += l.getCena();
        }
        JOptionPane.showMessageDialog(null, "Průměrná cena lahví je: "+ cena/evidence.getPolozky().size());
    }

    public void spocitejCenuSDPH(){
        int cena = 0;
        for (Lahev l: evidence.getPolozky()) {
            cena += l.getCena()+l.getCena()*l.getDph()/100;
        }
        JOptionPane.showMessageDialog(null, "Průměrná cena lahví s DPH je: "+ cena/evidence.getPolozky().size());
    }
}
