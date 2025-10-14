package Management;

import javax.swing.*;
import java.awt.*;

class PatientDischarge extends JFrame {
    public PatientDischarge() {
        setTitle("🚶 Patient Discharge");
        setSize(800, 600);
        getContentPane().setBackground(new Color(255, 230, 230));
        add(new JLabel("  This is the Patient Discharge Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
