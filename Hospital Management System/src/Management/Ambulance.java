package Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class Ambulance extends JFrame implements ActionListener {

    JTable ambulanceTable;
    JButton bBack;

    private static final Color FORM_BG = Color.WHITE;
    private static final Color BUTTON_BG = new Color(240, 248, 255);
    private static final Color HEADER_BG = new Color(70, 130, 180);
    private static final String FONT_NAME = "Arial";

    public Ambulance() {
        setTitle("🚑 Ambulance Information");
        setSize(1000, 700);
        setLayout(new BorderLayout());
        getContentPane().setBackground(FORM_BG);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setPreferredSize(new Dimension(1, 80));
        headerPanel.setBackground(HEADER_BG);
        add(headerPanel, BorderLayout.NORTH);

        JLabel title = new JLabel("Ambulance Information");
        title.setFont(new Font(FONT_NAME, Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(FORM_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        ambulanceTable = new JTable();
        styleTable(ambulanceTable);

        JScrollPane scrollPane = new JScrollPane(ambulanceTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        footerPanel.setPreferredSize(new Dimension(1, 100));
        footerPanel.setBackground(FORM_BG);
        add(footerPanel, BorderLayout.SOUTH);

        bBack = new JButton("🚪 Back to Reception");
        styleButton(bBack);
        bBack.addActionListener(this);
        footerPanel.add(bBack);

        loadAmbulanceData();

        setVisible(true);
    }

    private void loadAmbulanceData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM ambulance";
            ResultSet rs = c.statement.executeQuery(query);

            ambulanceTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error: Could not load ambulance data.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleTable(JTable table) {
        table.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(200, 230, 255));
        table.setSelectionForeground(Color.BLACK);

        table.getTableHeader().setFont(new Font(FONT_NAME, Font.BOLD, 16));
        table.getTableHeader().setBackground(BUTTON_BG);
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setPreferredSize(new Dimension(100, 40));
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
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ambulance());
    }
}