package Management;

import javax.swing.*;
import java.awt.*;

class Room extends JFrame {
    public Room() {
        setTitle("🛏️ Room Information");
        setSize(1000, 700);
        getContentPane().setBackground(new Color(240, 255, 240));
        add(new JLabel("  This is the Room Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
