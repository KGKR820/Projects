package Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Date;
import java.sql.ResultSet;

public class AddPatient extends JFrame implements ActionListener {

    JTextField tfNumber, tfName, tfDisease, tfDeposit;
    JComboBox<String> cId, cRoom;
    JRadioButton rMale, rFemale;
    ButtonGroup genderGroup;
    JLabel labelTime;
    JButton bAdd, bBack;

    private static final Color FORM_BG = Color.WHITE;
    private static final Color BUTTON_BG = new Color(240, 248, 255);
    private static final Color HEADER_BG = new Color(70, 130, 180);
    private static final String FONT_NAME = "Arial";

    public AddPatient() {

        bAdd = new JButton("➕ Add Patient");
        styleButton(bAdd);
        bAdd.addActionListener(this);

        bBack = new JButton("🚪 Back to Reception");
        styleButton(bBack);
        bBack.addActionListener(this);

        setTitle("➕ Add New Patient Details");
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

        JLabel title = new JLabel("Add New Patient Details");
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
        mainPanel.add(createLabel("ID Proof:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        String[] ids = {"Aadhar Card", "Voter ID", "Driving License"};
        cId = new JComboBox<>(ids);
        styleComponent(cId, 150);
        mainPanel.add(cId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(createLabel("ID Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        tfNumber = new JTextField(15);
        styleComponent(tfNumber, 150);
        mainPanel.add(tfNumber, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(createLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        tfName = new JTextField(15);
        styleComponent(tfName, 150);
        mainPanel.add(tfName, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(createLabel("Gender:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setBackground(FORM_BG);
        rMale = new JRadioButton("Male");
        rFemale = new JRadioButton("Female");
        styleRadio(rMale);
        styleRadio(rFemale);
        genderPanel.add(rMale);
        genderPanel.add(rFemale);

        genderGroup = new ButtonGroup();
        genderGroup.add(rMale);
        genderGroup.add(rFemale);
        mainPanel.add(genderPanel, gbc);

        rMale.setSelected(true);

        gbc.gridx = 2; gbc.gridy = 0;
        mainPanel.add(createLabel("Disease/Problem:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0;
        tfDisease = new JTextField(15);
        styleComponent(tfDisease, 150);
        mainPanel.add(tfDisease, gbc);

        gbc.gridx = 2; gbc.gridy = 1;
        mainPanel.add(createLabel("Room No.:"), gbc);
        gbc.gridx = 3; gbc.gridy = 1;

        cRoom = new JComboBox<>();
        styleComponent(cRoom, 150);

        populateRoomList();

        mainPanel.add(cRoom, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        mainPanel.add(createLabel("Check-in Time:"), gbc);
        gbc.gridx = 3; gbc.gridy = 2;
        labelTime = createValueLabel(new Date().toString());
        mainPanel.add(labelTime, gbc);

        gbc.gridx = 2; gbc.gridy = 3;
        mainPanel.add(createLabel("Deposit:"), gbc);
        gbc.gridx = 3; gbc.gridy = 3;
        tfDeposit = new JTextField(15);
        styleComponent(tfDeposit, 150);
        mainPanel.add(tfDeposit, gbc);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        footerPanel.setPreferredSize(new Dimension(1, 100));
        footerPanel.setBackground(FORM_BG);
        add(footerPanel, BorderLayout.SOUTH);

        footerPanel.add(bAdd);
        footerPanel.add(bBack);

        setVisible(true);
    }

    private void populateRoomList() {
        cRoom.removeAllItems();
        try {
            Conn c = new Conn();
            String query = "SELECT room_number FROM room WHERE available = 'Available'";
            ResultSet rs = c.statement.executeQuery(query);

            boolean roomsFound = false;
            while (rs.next()) {
                cRoom.addItem(rs.getString("room_number"));
                roomsFound = true;
            }

            if (!roomsFound) {
                cRoom.addItem("No Rooms Available");
                bAdd.setEnabled(false);
            } else {
                bAdd.setEnabled(true);
            }

        } catch (Exception e) {
            cRoom.addItem("Error Fetching Rooms");
            bAdd.setEnabled(false);
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        return label;
    }

    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
        label.setForeground(Color.DARK_GRAY);
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

    private void styleRadio(JRadioButton button) {
        button.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
        button.setBackground(FORM_BG);
        button.setForeground(Color.BLACK);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font(FONT_NAME, Font.BOLD, 18));
        button.setPreferredSize(new Dimension(220, 50));
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
        } else if (ae.getSource() == bAdd) {
            String idType = (String) cId.getSelectedItem();
            String idNumber = tfNumber.getText();
            String patientName = tfName.getText();
            String gender = rMale.isSelected() ? "Male" : (rFemale.isSelected() ? "Female" : "");
            String disease = tfDisease.getText();
            String roomNumber = (String) cRoom.getSelectedItem();
            String checkinTime = labelTime.getText();
            String deposit = tfDeposit.getText();

            if (idNumber.isEmpty() || patientName.isEmpty() || disease.isEmpty() || deposit.isEmpty() || gender.isEmpty() || roomNumber == null || roomNumber.contains("Error") || roomNumber.contains("No Rooms")) {
                JOptionPane.showMessageDialog(this, "Please fill all fields, select a gender, and ensure a valid room is selected.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Conn c = new Conn();

                String q = "INSERT INTO patient_info (id, number, name, gender, disease, room_number, time, deposit) VALUES ('"
                        + idType + "', '" + idNumber + "', '" + patientName + "', '" + gender + "', '" + disease + "', '"
                        + roomNumber + "', '" + checkinTime + "', '" + deposit + "')";

                String q1 = "UPDATE room SET available = 'Occupied' WHERE room_number = '" + roomNumber + "'";

                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);

                JOptionPane.showMessageDialog(this,
                        "Patient Added Successfully & Room Status Updated!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                tfNumber.setText("");
                tfName.setText("");
                tfDisease.setText("");
                tfDeposit.setText("");
                genderGroup.clearSelection();

                populateRoomList();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Database Error\n" + e.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "System Error: " + e.getMessage(),
                        "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPatient());
    }
}