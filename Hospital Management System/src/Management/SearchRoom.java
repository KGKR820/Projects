package Management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;

public class SearchRoom extends JFrame implements ActionListener {

    JTable roomTable;
    JButton bSearch, bBack;
    JComboBox<String> cRoomType;
    JCheckBox chkAvailable;

    private static final Color FORM_BG = Color.WHITE;
    private static final Color BUTTON_BG = new Color(240, 248, 255);
    private static final Color HEADER_BG = new Color(70, 130, 180);
    private static final String FONT_NAME = "Arial";

    public SearchRoom() {
        setTitle("🔍 Search Room");
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

        JLabel title = new JLabel("Search Room");
        title.setFont(new Font(FONT_NAME, Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(FORM_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        searchPanel.setBackground(FORM_BG);

        searchPanel.add(createLabel("Room Type:"));

        cRoomType = new JComboBox<>();
        styleComponent(cRoomType, 180);
        cRoomType.addItem("All Types");
        populateRoomTypes();
        searchPanel.add(cRoomType);

        chkAvailable = new JCheckBox("Available Rooms Only");
        chkAvailable.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        chkAvailable.setBackground(FORM_BG);
        chkAvailable.setForeground(Color.BLACK);
        searchPanel.add(chkAvailable);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        roomTable = new JTable();
        styleTable(roomTable);

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        footerPanel.setPreferredSize(new Dimension(1, 100));
        footerPanel.setBackground(FORM_BG);
        add(footerPanel, BorderLayout.SOUTH);

        bSearch = new JButton("🔍 Search");
        styleButton(bSearch);
        bSearch.addActionListener(this);
        footerPanel.add(bSearch);

        bBack = new JButton("🚪 Back");
        styleButton(bBack);
        bBack.addActionListener(this);
        footerPanel.add(bBack);

        loadRoomData("SELECT * FROM room");

        setVisible(true);
    }

    private void populateRoomTypes() {
        try {
            Conn c = new Conn();
            String query = "SELECT DISTINCT room_type FROM room";
            ResultSet rs = c.statement.executeQuery(query);
            while (rs.next()) {
                cRoomType.addItem(rs.getString("room_type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRoomData(String query) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.statement.executeQuery(query);

            roomTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Database Error: Could not load room data.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
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

        if (component instanceof JComboBox) {
            ((JComboBox<?>) component).setFocusable(false);
            ((JComboBox<?>) component).setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
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

        } else if (ae.getSource() == bSearch) {
            String roomType = (String) cRoomType.getSelectedItem();
            boolean availableOnly = chkAvailable.isSelected();

            String query = "SELECT * FROM room";
            boolean hasWhereClause = false;

            if (!"All Types".equals(roomType)) {
                query += " WHERE room_type = '" + roomType + "'";
                hasWhereClause = true;
            }

            if (availableOnly) {
                if (hasWhereClause) {
                    query += " AND available = 'Available'";
                } else {
                    query += " WHERE available = 'Available'";
                }
            }

            loadRoomData(query);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SearchRoom());
    }
}