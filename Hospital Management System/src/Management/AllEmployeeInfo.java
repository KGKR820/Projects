package Management;

import javax.swing.*;
import java.awt.*;

class AllEmployeeInfo extends JFrame {
    public AllEmployeeInfo() {
        setTitle("👥 All Employee Information");
        setSize(1200, 700);
        getContentPane().setBackground(new Color(253, 245, 230));
        add(new JLabel("  This is the All Employee Info Module. Implement logic here.", JLabel.CENTER));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
