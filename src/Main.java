import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        JFrame fenster = new JFrame("Eingabefenster");
        fenster.getContentPane().setBackground(Color.WHITE);
        fenster.setLayout(new GridBagLayout());

        Image bild = new ImageIcon("ID.card.jpg").getImage();

        JPanel karte = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bild, 0, 0, 500, 500, this);
            }
        };
        karte.setPreferredSize(new Dimension(500, 500));
        fenster.add(karte);

        JLabel frage = new JLabel("Bitte gib deine ID ein:");
        JTextField eingabeFeld = new JTextField();
        JLabel antwort = new JLabel("");
        Map<Integer, String> Names = Map.of(
                27799,"Leonhard",
                34598,"Markus" ,
                49931,"Louis"
        );

        JComponent[] felder = {frage, eingabeFeld, antwort};
        for (int i = 0; i < felder.length; i++) {
            felder[i].setBounds(120, 262 + i * 33, 265, 24);
            felder[i].setFont(felder[i].getFont().deriveFont(16f));
            karte.add(felder[i]);
        }

        eingabeFeld.setOpaque(false);
        eingabeFeld.setBorder(BorderFactory.createEmptyBorder());

        JLabel uhr = new JLabel(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), JLabel.RIGHT);
        uhr.setBounds(280, 188, 135, 24);
        uhr.setFont(uhr.getFont().deriveFont(13f));
        karte.add(uhr);



        eingabeFeld.addActionListener(e -> {
            try {
                int id = Integer.parseInt(eingabeFeld.getText());
                if (Names.containsKey(id)) {
                    antwort.setText("Zugang gewährt, hallo " + Names.get(id) + " \u2713");
                } else {
                    antwort.setText("Zugang verweigert \u2717");
                }
            } catch (NumberFormatException ex) {
                antwort.setText("Bitte nur Zahlen eingeben!");
            }
        });

        fenster.pack();
        fenster.setVisible(true);

    }
}
