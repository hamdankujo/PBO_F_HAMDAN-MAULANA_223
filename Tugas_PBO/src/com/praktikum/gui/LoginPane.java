package com.praktikum.gui;

import com.praktikum.main.LoginSystem;
import com.praktikum.users.Admin;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPane extends JFrame {
    private JComboBox<String> roleBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPane() {
        setTitle("Lost & Found Kampus UMM");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Login Sistem Lost & Found", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        formPanel.add(new JLabel("Role:"));
        roleBox = new JComboBox<>(new String[]{"Mahasiswa", "Admin"});
        formPanel.add(roleBox);

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        loginButton = new JButton("Login");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);

        loginButton = new JButton("Login2");
        JPanel buttonPanel2 = new JPanel();
        buttonPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                User user = LoginSystem.authenticate(username, password);
                if (user != null) {
                    dispose();
                    if (user instanceof Admin) {
                        new AdminDashboard();
                    } else if (user instanceof Mahasiswa) {
                        new MahasiswaDashboard((Mahasiswa) user);
                    } else {
                        JOptionPane.showMessageDialog(LoginPane.this, "Role tidak dikenali.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginPane.this, "Login gagal, periksa kembali.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        LoginSystem.seedUsers();
        new LoginPane();
    }
}
