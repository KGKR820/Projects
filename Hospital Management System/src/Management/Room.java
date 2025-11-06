package Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class Room extends JFrame implements ActionListener {

    JTable roomTable;
    JButton bBack;

    private static final Color FORM_BG = Color.WHITE;
    private static final Color BUTTON_BG = new Color(240, 248, 255);
    private static final Color HEADER_BG = new Color(70, 130, 180);
    private static final String FONT_NAME = "Arial";

    public Room() {
        setTitle("🛏️ Room Information");
        setSize(800, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(FORM_BG);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setPreferredSize(new Dimension(1, 80));
        headerPanel.setBackground(HEADER_BG);
        add(headerPanel, BorderLayout.NORTH);

        JLabel title = new JLabel("Room Information & Status");
        title.setFont(new Font(FONT_NAME, Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(FORM_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        roomTable = new JTable();
        styleTable(roomTable);

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        footerPanel.setPreferredSize(new Dimension(1, 100));
        footerPanel.setBackground(FORM_BG);
        add(footerPanel, BorderLayout.SOUTH);

        bBack = new JButton("🚪 Back to Reception");
        styleButton(bBack);
        bBack.addActionListener(this);
        footerPanel.add(bBack);

        loadRoomData();

        setVisible(true);
    }

    private void loadRoomData() {
        try {
            Conn c = new Conn();
            String query = "SELECT room_number, available, price, room_type FROM room";
            ResultSet rs = c.statement.executeQuery(query);

            roomTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error: Could not load room data.\n" + e.getMessage(),
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
        SwingUtilities.invokeLater(() -> new Room());
    }
}