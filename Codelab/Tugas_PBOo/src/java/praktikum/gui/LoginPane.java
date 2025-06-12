package praktikum.gui;

import praktikum.data.DataStore;
import com.praktikum.users.*;

import javax.swing.*;
import java.awt.*;

public class LoginPane extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPane() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> handleLogin());
        panel.add(loginBtn);

        add(panel);
        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        for (User user : DataStore.userList) {
            if (user.getUsername().equals(username) && user.checkPassword(password)) {
                JOptionPane.showMessageDialog(this, "Login Berhasil sebagai " + user.getName());
                dispose();
                if (user instanceof Admin) {
                    new AdminDashboard();
                } else if (user instanceof Mahasiswa) {
                    new MahasiswaDashboard();
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Username atau Password salah.");
    }
}
