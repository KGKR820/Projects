package Management;

import javax.swing.*;
import java.awt.*;

class PatientInfo extends JFrame {
    public PatientInfo() {
        setTitle("📄 Patient Information");
        setSize(1200, 700);
        getContentPane().setBackground(new Color(255, 250, 205));
        add(new JLabel("  This is the Patient Info Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
