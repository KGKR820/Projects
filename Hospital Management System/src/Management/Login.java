package Management;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField textField;
    JPasswordField passwordField;
    JButton loginButton;// Declare loginButton as an instance variable
    JLabel passwordLabel;
    JLabel usernameLabel;
    Login() {
        getContentPane().setBackground(new Color(204, 214, 235));
        setSize(750, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

         usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(40, 30, 150, 30);
        usernameLabel.setFont(new Font("Poppins", Font.BOLD, 22));
        add(usernameLabel);

        textField = new JTextField();
        textField.setBounds(190, 30, 200, 30);
        textField.setFont(new Font("Poppins", Font.PLAIN, 19));
        Border myBorder = BorderFactory.createLineBorder(Color.black, 1);
        textField.setBorder(myBorder);
        add(textField);

         passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40, 80, 150, 30);
        passwordLabel.setFont(new Font("Poppins", Font.BOLD, 22));
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(190, 80, 200, 30);
        passwordField.setFont(new Font("Poppins", Font.PLAIN, 19));
        passwordField.setBorder(myBorder);
        add(passwordField);

        loginButton = new JButton("Login"); // Now using the instance variable
        loginButton.setBounds(120,150, 200, 35);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 18));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        add(loginButton);

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == loginButton){
            try{
                Conn c = new Conn();
                String user = textField.getText();  // Fixed: get from textField
                String pass = new String(passwordField.getPassword());  // Fixed: get from passwordField

                String q = "select * from login where ID = '"+user+"' and PW = '"+pass+"'";
                ResultSet resultset = c.statement.executeQuery(q);

                if(resultset.next()){
                    setVisible(false);
                    new Reception();

                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }
            }
            catch(Exception E){
                E.printStackTrace();
                JOptionPane.showMessageDialog(null,"Database connection error");
            }
        }
    }
    }
