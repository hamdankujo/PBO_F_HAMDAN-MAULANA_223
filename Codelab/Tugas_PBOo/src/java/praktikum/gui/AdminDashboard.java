package praktikum.gui;

import javax.swing.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Dashboard Admin");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Selamat datang Admin");
        add(label);

        setVisible(true);
    }
}