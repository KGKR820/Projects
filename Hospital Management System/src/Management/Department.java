package Management;

import javax.swing.*;
import java.awt.*;

class Department extends JFrame {
    public Department() {
        setTitle("🏢 Department Information");
        setSize(800, 600);
        getContentPane().setBackground(new Color(255, 240, 245));
        add(new JLabel("  This is the Department Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
