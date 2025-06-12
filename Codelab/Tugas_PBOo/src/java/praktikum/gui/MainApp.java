package praktikum.gui;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginSystem.seedUsers();
            new LoginPane();
        });
    }
}
