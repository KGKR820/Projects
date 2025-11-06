package Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PatientDischarge extends JFrame implements ActionListener {

    JComboBox<String> cPatientId;
    JTextField tfRoom, tfCheckIn, tfCheckOut;
    JButton bDischarge, bCheck, bBack;

    private static final Color FORM_BG = Color.WHITE;
    private static final Color BUTTON_BG = new Color(240, 248, 255);
    private static final Color HEADER_BG = new Color(70, 130, 180);
    private static final String FONT_NAME = "Arial";

    public PatientDischarge() {
        bDischarge = new JButton("✔️ Discharge");
        styleButton(bDischarge);
        bDischarge.addActionListener(this);

        bCheck = new JButton("🔍 Check");
        styleButton(bCheck, 120, 40);
        bCheck.addActionListener(this);

        bBack = new JButton("🚪 Back");
        styleButton(bBack);
        bBack.addActionListener(this);

        setTitle("Patient Discharge");
        setSize(900, 650);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(FORM_BG);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setPreferredSize(new Dimension(1, 80));
        headerPanel.setBackground(HEADER_BG);
        add(headerPanel, BorderLayout.NORTH);

        JLabel title = new JLabel("Patient Discharge");
        title.setFont(new Font(FONT_NAME, Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(FORM_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(createLabel("Patient ID:"), gbc);

        cPatientId = new JComboBox<>();
        styleComponent(cPatientId, 180);
        populatePatientIds();
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(cPatientId, gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        mainPanel.add(bCheck, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(createLabel("Room Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        tfRoom = new JTextField(15);
        styleComponent(tfRoom, 180);
        tfRoom.setEditable(false);
        mainPanel.add(tfRoom, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(createLabel("Check-in Time:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        tfCheckIn = new JTextField(30);
        styleComponent(tfCheckIn, 300);
        tfCheckIn.setEditable(false);
        mainPanel.add(tfCheckIn, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(createLabel("Check-out Time:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        tfCheckOut = new JTextField(new Date().toString());
        styleComponent(tfCheckOut, 300);
        tfCheckOut.setEditable(false);
        mainPanel.add(tfCheckOut, gbc);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        footerPanel.setPreferredSize(new Dimension(1, 100));
        footerPanel.setBackground(FORM_BG);
        add(footerPanel, BorderLayout.SOUTH);

        footerPanel.add(bDischarge);
        footerPanel.add(bBack);

        setVisible(true);
    }

    private void populatePatientIds() {
        cPatientId.removeAllItems();
        try {
            Conn c = new Conn();
            String query = "SELECT number FROM patient_info";
            ResultSet rs = c.statement.executeQuery(query);

            while (rs.next()) {
                cPatientId.addItem(rs.getString("number"));
            }
            cPatientId.setSelectedIndex(-1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading patient IDs: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        return label;
    }

    private void styleComponent(JComponent component, int width) {
        component.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
        component.setPreferredSize(new Dimension(width, 30));
        component.setForeground(Color.BLACK);
        component.setBackground(BUTTON_BG);

        if (component instanceof JTextField) {
            ((JTextField) component).setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        } else if (component instanceof JComboBox) {
            ((JComboBox<?>) component).setFocusable(false);
            ((JComboBox<?>) component).setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        }
    }

    private void styleButton(JButton button) {
        styleButton(button, 220, 50);
    }

    private void styleButton(JButton button, int width, int height) {
        button.setFont(new Font(FONT_NAME, Font.BOLD, 18));
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(BUTTON_BG);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 230, 255));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_BG);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bBack) {
            dispose();

        } else if (ae.getSource() == bCheck) {
            String patientId = (String) cPatientId.getSelectedItem();
            if (patientId == null) {
                JOptionPane.showMessageDialog(this, "Please select a patient ID.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn c = new Conn();
                String query = "SELECT room_number, time FROM patient_info WHERE number = '" + patientId + "'";
                ResultSet rs = c.statement.executeQuery(query);

                if (rs.next()) {
                    tfRoom.setText(rs.getString("room_number"));
                    tfCheckIn.setText(rs.getString("time"));
                } else {
                    JOptionPane.showMessageDialog(this, "Patient ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (ae.getSource() == bDischarge) {
            String patientId = (String) cPatientId.getSelectedItem();
            String roomNumber = tfRoom.getText();

            if (patientId == null || roomNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please check patient details first.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to discharge patient " + patientId + "?", "Confirm Discharge", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                try {
                    Conn c = new Conn();
                    String q1 = "DELETE FROM patient_info WHERE number = '" + patientId + "'";
                    String q2 = "UPDATE room SET available = 'Available' WHERE room_number = '" + roomNumber + "'";

                    c.statement.executeUpdate(q1);
                    c.statement.executeUpdate(q2);

                    JOptionPane.showMessageDialog(this, "Patient Discharged Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    tfRoom.setText("");
                    tfCheckIn.setText("");
                    tfCheckOut.setText(new Date().toString());
                    populatePatientIds();

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "System Error: " + e.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientDischarge());
    }
}