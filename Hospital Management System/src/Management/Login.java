package Management;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Login extends JFrame {
    JTextField textField;
    JPasswordField passwordField;

    Login() {
        getContentPane().setBackground(new Color(204, 214, 235));
        setSize(750, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(40, 30, 150, 30);
        usernameLabel.setFont(new Font("Poppins", Font.BOLD, 22));
        add(usernameLabel);

        textField = new JTextField();
        textField.setBounds(190, 30, 200, 30);
        textField.setFont(new Font("Poppins", Font.PLAIN, 19));
        Border myBorder = BorderFactory.createLineBorder(Color.black, 1);
        textField.setBorder(myBorder);
        add(textField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 80, 150, 30);
        passwordLabel.setFont(new Font("Poppins", Font.BOLD, 22));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(190, 80, 200, 30);
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 19));
        passwordField.setBorder(myBorder);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120,150, 200, 35);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 18));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        add(loginButton);

        // Fixed image loading - removed String.valueOf()
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("Icons/Logo.jpg"));
        Image i1 = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel imageLabel = new JLabel(imageIcon1);
        imageLabel.setBounds(450, 30, 250, 200);
        add(imageLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
}
