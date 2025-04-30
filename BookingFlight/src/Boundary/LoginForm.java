package Boundary;
import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public LoginForm() {
        setTitle("BookingFlights - Login");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(220, 240, 255));
        mainPanel.setLayout(null);
        add(mainPanel);

        // Company Title
        JLabel lblCompany = new JLabel("✈ BookingFlights", SwingConstants.CENTER);
        lblCompany.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblCompany.setForeground(new Color(33, 33, 99));
        lblCompany.setBounds(150, 20, 300, 40);
        mainPanel.add(lblCompany);

        // Logo (optional)
        try {
        	ImageIcon icon = new ImageIcon(getClass().getResource("/boundary/images/logo.png"));
            Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(260, 70, 80, 80);
            mainPanel.add(imgLabel);
        } catch (Exception e) {
            System.out.println("Logo image not found.");
        }

        // Login Card
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBounds(150, 170, 300, 160);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        mainPanel.add(cardPanel);

        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(20, 20, 100, 25);
        cardPanel.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 20, 150, 25);
        cardPanel.add(txtUsername);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(20, 60, 100, 25);
        cardPanel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 60, 150, 25);
        cardPanel.add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 110, 100, 30);
        btnLogin.setBackground(new Color(0, 120, 215));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLogin.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Welcome, " + txtUsername.getText() + "!");
            // Move to next screen...
        });
        cardPanel.add(btnLogin);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true);
        });
    }
}

