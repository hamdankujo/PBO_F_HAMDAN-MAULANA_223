package praktikum.main;

import praktikum.models.Item;
import com.praktikum.users.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginSystem {
    public static ArrayList<User> userList = new ArrayList<>();
    public static ArrayList<Item> reportedItems = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedUsers();
        while (true) {
            System.out.println("\n--- Sistem Login ---");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = authenticate(username, password);
            if (user != null) {
                user.displayAppMenu();
            } else {
                System.out.println("❌ Username atau password salah.");
            }
        }
    }

    public static User authenticate(String username, String password) {
        for (User u : userList) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) {
                return u;
            }
        }
        return null;
    }

    public static void seedUsers() {
        userList.add(new Admin("Admin", "223", "admin", "nim223"));
        userList.add(new Mahasiswa("Hamdan", "Nim221", "hamdan", "hamdan223"));
        userList.add(new Mahasiswa("Alfin", "Nim247", "alfin", "alfin247"));
    }

    public static int getInputAngka() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
