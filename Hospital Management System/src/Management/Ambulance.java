package Management;

import javax.swing.*;
import java.awt.*;

class Ambulance extends JFrame {
    public Ambulance() {
        setTitle("🚑 Ambulance Services");
        setSize(800, 600);
        getContentPane().setBackground(new Color(250, 240, 230));
        add(new JLabel("  This is the Ambulance Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
