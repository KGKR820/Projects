package Management;

import javax.swing.*;
import java.awt.*;

class AddPatient extends JFrame {
    public AddPatient() {
        setTitle("➕ Add New Patient");
        setSize(800, 600);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        add(new JLabel("  This is the Add New Patient Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
