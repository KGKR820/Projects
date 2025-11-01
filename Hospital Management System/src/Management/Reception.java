package Management;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

// --- Placeholder Classes for Compilation (User must replace these with full implementations) ---
// These classes must be implemented fully in separate files in the 'Management' package.

// -------------------------------------------------------------------------------------------------

public class Reception extends JFrame {

    public Reception() {
        // Set up the main JFrame properties
        setTitle("Hospital Reception");
        setSize(1200, 800); // Reduced size for better desktop view, use pack() for flexibility
        // Use BorderLayout for the main JFrame to organize large sections
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --- Header Panel (North) ---
        JPanel headerPanel = new JPanel();
        // Use BorderLayout or FlowLayout within the header for internal components
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1, 100)); // Set height
        headerPanel.setBackground(new Color(70, 130, 180));
        add(headerPanel, BorderLayout.NORTH); // Add to the NORTH of JFrame's BorderLayout

        // Title Container (West in Header)
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 30));
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel title = new JLabel("Hospital Management System");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        titlePanel.add(title);
        headerPanel.add(titlePanel, BorderLayout.WEST);

        // Logo Container (East in Header)
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        logoPanel.setBackground(new Color(70, 130, 180));
        try {
            // Path is assumed to be correct: Icons/Logo.jpg
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/Logo.jpg"));
            Image i2 = i1.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(i2));
            logoPanel.add(logo);
        } catch (Exception e) {
            // System.out.println("Logo not found: " + e.getMessage()); // Debugging
            // Logo not found, continue without it
        }
        headerPanel.add(logoPanel, BorderLayout.EAST);

        // --- Main Content Panel (Center) ---
        JPanel mainPanel = new JPanel();
        // Use GridLayout for the buttons
        mainPanel.setLayout(new GridLayout(3, 3, 30, 30));
        mainPanel.setBackground(Color.WHITE); // White background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Padding
        add(mainPanel, BorderLayout.CENTER); // Add to the CENTER of JFrame's BorderLayout

        // Buttons
        mainPanel.add(createButton("➕ Add New Patient"));
        mainPanel.add(createButton("🛏️ Room"));
        mainPanel.add(createButton("🏢 Department"));
        mainPanel.add(createButton("👥 All Employee Info"));
        mainPanel.add(createButton("📄 Patient Info"));
        mainPanel.add(createButton("🚶 Patient Discharge"));
        mainPanel.add(createButton("✏️ Update Patient Details"));
        mainPanel.add(createButton("🚑 Ambulance"));
        mainPanel.add(createButton("🔍 Search Room"));

        // --- Footer Panel (South) ---
        // This panel is added to manage the logout button and fix the bottom color.
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 100, 20)); // FlowLayout for positioning
        footerPanel.setPreferredSize(new Dimension(1, 100)); // Set height
        footerPanel.setBackground(Color.WHITE); // **Set to same background color (White)**
        add(footerPanel, BorderLayout.SOUTH); // Add to the SOUTH of JFrame's BorderLayout

        // Logout Button
        JButton logout = new JButton("🚪 Logout");
        logout.setFont(new Font("Arial", Font.BOLD, 18));
        logout.setPreferredSize(new Dimension(200, 50));
        logout.setBackground(new Color(220, 20, 60)); // Crimson
        logout.setForeground(Color.WHITE);
        logout.setFocusPainted(false);
        footerPanel.add(logout); // Add to the footer panel

        logout.addActionListener(e -> {
            setVisible(false);
            new Login();


        });

        // Finalize frame settings
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(new Color(240, 248, 255)); // Alice Blue
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(200, 230, 255)); // Light blue on hover
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(240, 248, 255));
            }
        });

        // --- Core Functional Logic Added Here ---
        button.addActionListener(e -> {


            try {

                // Launch the appropriate module class based on the button text
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
                } else if (text.equals("✏️ Update Patient")) {
                    new UpdatePatient().setVisible(true);
                } else if (text.equals("🚑 Ambulance")) {
                    new Ambulance().setVisible(true);
                } else if (text.equals("🔍 Search Room")) {
                    new SearchRoom().setVisible(true);
                }
            } catch (Exception ex) {
                // If a module class is missing or fails to load, show an error and re-show Reception
                setVisible(true);
                String moduleName = text.replaceFirst("^[^\s]+\\s", "");
                JOptionPane.showMessageDialog(this,
                        "Error: The module for **" + moduleName + "** is not found. " +
                                "Please implement the corresponding class in the 'Management' package.",
                        "Module Missing", JOptionPane.ERROR_MESSAGE);
            }
        });
        // ----------------------------------------

        return button;
    }

    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater for thread safety in Swing
        SwingUtilities.invokeLater(() -> new Reception());
    }
}