package Management;

import javax.swing.*;
import java.awt.*;

class UpdatePatient extends JFrame {
    public UpdatePatient() {
        setTitle("✏️ Update Patient Details");
        setSize(800, 600);
        getContentPane().setBackground(new Color(240, 250, 255));
        add(new JLabel("  This is the Update Patient Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
