package Boundary;


import javax.swing.*;

import java.awt.*;
import Control.PassengerController;

public class RegisterForm extends JFrame {

    private JTextField txtId, txtName, txtAddress;
    private String id,name,address;
    private PassengerController pessengerLogic;

    public RegisterForm() {
    	pessengerLogic=PassengerController.getInstance();
        setTitle("BookingFlights - Register");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(220, 240, 255));
        add(mainPanel);

        JLabel lblTitle = new JLabel("✈ Register New Customer", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setBounds(100, 20, 300, 30);
        mainPanel.add(lblTitle);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(100, 80, 100, 25);
        mainPanel.add(lblId);

        txtId = new JTextField();
        txtId.setBounds(200, 80, 180, 25);
        mainPanel.add(txtId);

        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(100, 120, 100, 25);
        mainPanel.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(200, 120, 180, 25);
        mainPanel.add(txtName);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(100, 160, 100, 25);
        mainPanel.add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(200, 160, 180, 25);
        mainPanel.add(txtAddress);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 220, 100, 30);
        btnRegister.setBackground(new Color(0, 120, 215));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnRegister.setFocusPainted(false);
        btnRegister.addActionListener(e -> {
            id=txtId.getText();
            name=txtName.getText();
            address=txtAddress.getText();

            System.out.print("id -> "+id);

            String success = pessengerLogic.insertPassenger(id,name,address);
            JOptionPane.showMessageDialog(this, success);
    
        });
        mainPanel.add(btnRegister);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegisterForm().setVisible(true);
        });
    }
}
