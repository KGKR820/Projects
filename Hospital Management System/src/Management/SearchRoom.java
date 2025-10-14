package Management;

import javax.swing.*;
import java.awt.*;

class SearchRoom extends JFrame {
    public SearchRoom() {
        setTitle("🔍 Search Room Availability");
        setSize(800, 600);
        getContentPane().setBackground(new Color(245, 255, 250));
        add(new JLabel("  This is the Search Room Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
