package Management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Reception extends JFrame {

    public Reception() {
        setTitle("Hospital Reception");
        setSize(1200, 800);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1, 100));
        headerPanel.setBackground(new Color(70, 130, 180));
        add(headerPanel, BorderLayout.NORTH);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 30));
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel title = new JLabel("Hospital Management System");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        logoPanel.setBackground(new Color(70, 130, 180));
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/Logo.jpg"));
            Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(i2));
            logoPanel.add(logo);
        } catch (Exception e) {
        }
        headerPanel.add(logoPanel, BorderLayout.EAST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3, 30, 30));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(createButton("➕ Add New Patient"));
        mainPanel.add(createButton("🛏️ Room"));
        mainPanel.add(createButton("🏢 Department"));
        mainPanel.add(createButton("👥 All Employee Info"));
        mainPanel.add(createButton("📄 Patient Info"));
        mainPanel.add(createButton("🚶 Patient Discharge"));
        mainPanel.add(createButton("✏️ Update Patient Details"));
        mainPanel.add(createButton("🚑 Ambulance"));
        mainPanel.add(createButton("🔍 Search Room"));

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 100, 20));
        footerPanel.setPreferredSize(new Dimension(1, 100));
        footerPanel.setBackground(Color.WHITE);
        add(footerPanel, BorderLayout.SOUTH);

        JButton logout = new JButton("🚪 Logout");
        logout.setFont(new Font("Arial", Font.BOLD, 18));
        logout.setPreferredSize(new Dimension(200, 50));
        logout.setBackground(new Color(220, 20, 60));
        logout.setForeground(Color.WHITE);
        logout.setFocusPainted(false);
        footerPanel.add(logout);

        logout.addActionListener(e -> {
            setVisible(false);
            // Assuming Login class exists and handles the login screen logic
            // new Login();
            // Since Login class is not provided, we just exit for safety
            System.exit(0);

        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(new Color(240, 248, 255));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 230, 255));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(240, 248, 255));
            }
        });

        button.addActionListener(e -> {
            try {
                if (text.equals("➕ Add New Patient")) {
                    new AddPatient().setVisible(true);
                } else if (text.equals("🛏️ Room")) {
                    new Room().setVisible(true);
                } else if (text.equals("🏢 Department")) {
                    new Department().setVisible(true);
                } else if (text.equals("👥 All Employee Info")) {
                    new AllEmployeeInfo().setVisible(true);
                } else if (text.equals("📄 Patient Info")) {
                    new PatientInfo().setVisible(true);
                } else if (text.equals("🚶 Patient Discharge")) {
                    new PatientDischarge().setVisible(true);
                } else if (text.equals("✏️ Update Patient Details")) {
                    // Corrected text comparison to match the button text
                    new UpdatePatient().setVisible(true);
                } else if (text.equals("🚑 Ambulance")) {
                    new Ambulance().setVisible(true);
                } else if (text.equals("🔍 Search Room")) {
                    new SearchRoom().setVisible(true);
                }
            } catch (Exception ex) {
                setVisible(true);
                String moduleName = text.replaceFirst("^[^\s]+\\s", "");
                JOptionPane.showMessageDialog(this,
                        "Error: The module for " + moduleName + " is not found. ",
                        "Module Missing", JOptionPane.ERROR_MESSAGE);
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Reception());
    }
}