package Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatePatient extends JFrame implements ActionListener {

    JComboBox<String> cPatientId;
    JTextField tfRoom, tfName, tfAge, tfDisease, tfDeposit;
    JButton bUpdate, bCheck, bBack;

    private static final Color FORM_BG = Color.WHITE;
    private static final Color BUTTON_BG = new Color(240, 248, 255);
    private static final Color HEADER_BG = new Color(70, 130, 180);
    private static final String FONT_NAME = "Arial";

    public UpdatePatient() {
        bUpdate = new JButton("💾 Update");
        styleButton(bUpdate);
        bUpdate.addActionListener(this);

        bCheck = new JButton("🔍 Check");
        styleButton(bCheck, 120, 40);
        bCheck.addActionListener(this);

        bBack = new JButton("🚪 Back");
        styleButton(bBack);
        bBack.addActionListener(this);

        setTitle("Update Patient Details");
        setSize(1200, 800);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(FORM_BG);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setPreferredSize(new Dimension(1, 80));
        headerPanel.setBackground(HEADER_BG);
        add(headerPanel, BorderLayout.NORTH);

        JLabel title = new JLabel("Update Patient Details");
        title.setFont(new Font(FONT_NAME, Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2, 40, 10));
        mainPanel.setBackground(FORM_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        add(mainPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(FORM_BG);
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(15, 10, 15, 10);
        gbcLeft.anchor = GridBagConstraints.WEST;

        gbcLeft.gridx = 0; gbcLeft.gridy = 0;
        leftPanel.add(createLabel("Patient ID:"), gbcLeft);

        cPatientId = new JComboBox<>();
        styleComponent(cPatientId, 180);
        populatePatientIds();
        gbcLeft.gridx = 1; gbcLeft.gridy = 0;
        leftPanel.add(cPatientId, gbcLeft);

        gbcLeft.gridx = 2; gbcLeft.gridy = 0;
        leftPanel.add(bCheck, gbcLeft);

        gbcLeft.gridx = 0; gbcLeft.gridy = 1;
        leftPanel.add(createLabel("Room Number:"), gbcLeft);
        gbcLeft.gridx = 1; gbcLeft.gridy = 1; gbcLeft.gridwidth = 2;
        tfRoom = new JTextField(15);
        styleComponent(tfRoom, 180);
        leftPanel.add(tfRoom, gbcLeft);
        gbcLeft.gridwidth = 1;

        gbcLeft.gridx = 0; gbcLeft.gridy = 2;
        leftPanel.add(createLabel("Name:"), gbcLeft);
        gbcLeft.gridx = 1; gbcLeft.gridy = 2; gbcLeft.gridwidth = 2;
        tfName = new JTextField(15);
        styleComponent(tfName, 180);
        leftPanel.add(tfName, gbcLeft);
        gbcLeft.gridwidth = 1;

        gbcLeft.gridx = 0; gbcLeft.gridy = 3;
        leftPanel.add(createLabel("Age:"), gbcLeft);
        gbcLeft.gridx = 1; gbcLeft.gridy = 3; gbcLeft.gridwidth = 2;
        tfAge = new JTextField(15);
        styleComponent(tfAge, 180);
        leftPanel.add(tfAge, gbcLeft);
        gbcLeft.gridwidth = 1;

        gbcLeft.gridx = 0; gbcLeft.gridy = 4;
        gbcLeft.weightx = 1.0; gbcLeft.weighty = 1.0;
        leftPanel.add(new JLabel(""), gbcLeft);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(FORM_BG);
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(15, 10, 15, 10);
        gbcRight.anchor = GridBagConstraints.WEST;

        gbcRight.gridx = 0; gbcRight.gridy = 0;
        rightPanel.add(createLabel("Disease:"), gbcRight);
        gbcRight.gridx = 1; gbcRight.gridy = 0;
        tfDisease = new JTextField(15);
        styleComponent(tfDisease, 180);
        rightPanel.add(tfDisease, gbcRight);

        gbcRight.gridx = 0; gbcRight.gridy = 1;
        rightPanel.add(createLabel("Deposit (Pending):"), gbcRight);
        gbcRight.gridx = 1; gbcRight.gridy = 1;
        tfDeposit = new JTextField(15);
        styleComponent(tfDeposit, 180);
        rightPanel.add(tfDeposit, gbcRight);

        gbcRight.gridx = 0; gbcRight.gridy = 2;
        gbcRight.weightx = 1.0; gbcRight.weighty = 1.0;
        rightPanel.add(new JLabel(""), gbcRight);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);


        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        footerPanel.setPreferredSize(new Dimension(1, 100));
        footerPanel.setBackground(FORM_BG);
        add(footerPanel, BorderLayout.SOUTH);

        footerPanel.add(bUpdate);
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
                String query = "SELECT * FROM patient_info WHERE number = '" + patientId + "'";
                ResultSet rs = c.statement.executeQuery(query);

                if (rs.next()) {
                    tfRoom.setText(rs.getString("room_number"));
                    tfName.setText(rs.getString("name"));
                    tfAge.setText(rs.getString("age"));
                    tfDisease.setText(rs.getString("disease"));
                    tfDeposit.setText(rs.getString("deposit"));
                } else {
                    JOptionPane.showMessageDialog(this, "Patient ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (ae.getSource() == bUpdate) {
            String patientId = (String) cPatientId.getSelectedItem();
            String room = tfRoom.getText();
            String name = tfName.getText();
            String age = tfAge.getText();
            String disease = tfDisease.getText();
            String deposit = tfDeposit.getText();

            if (patientId == null || room.isEmpty() || name.isEmpty() || age.isEmpty() || disease.isEmpty() || deposit.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select a patient and fill all fields.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn c = new Conn();

                String query = "UPDATE patient_info SET " +
                        "room_number = '" + room + "', " +
                        "name = '" + name + "', " +
                        "age = '" + age + "', " +
                        "disease = '" + disease + "', " +
                        "deposit = '" + deposit + "' " +
                        "WHERE number = '" + patientId + "'";

                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Patient Details Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                tfRoom.setText("");
                tfName.setText("");
                tfAge.setText("");
                tfDisease.setText("");
                tfDeposit.setText("");
                cPatientId.setSelectedIndex(-1);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "System Error: " + e.getMessage(), "System Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdatePatient());
    }
}