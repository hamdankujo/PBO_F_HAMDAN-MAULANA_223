package praktikum.users;

import praktikum.actions.AdminActions;
import com.praktikum.main.LoginSystem;
import praktikum.main.LoginSystem;
import praktikum.models.Item;

public class Admin extends User implements AdminActions {

    private praktikum.main.LoginSystem LoginSystem;

    public Admin(String name, String id, String username, String password) {
        super(name, id, username, password);
    }

    @Override
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public void displayAppMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Menu Admin ---");
            System.out.println("1. Lihat data Mahasiswa");
            System.out.println("2. Lihat laporan barang");
            System.out.println("3. Hapus laporan barang");
            System.out.println("0. Logout");
            System.out.print("Pilih dong: ");

            int pilihan = LoginSystem.getInputAngka();
            switch (pilihan) {
                case 1 -> manageUsers();
                case 2 -> manageItems();
                case 3 -> deleteItem();
                case 0 -> running = false;
                default -> System.out.println("❌ Pilihan tidak valid.");
            }
        }
    }

    @Override
    public void manageUsers() {
        System.out.println("\n--- Daftar Mahasiswa ---");
        boolean ada = false;
        for (User u : LoginSystem.userList) {
            if (u instanceof Mahasiswa) {
                u.displayInfo();
                ada = true;
            }
        }
        if (!ada) System.out.println("Tidak ada data mahasiswa.");
    }

    @Override
    public void manageItems() {
        System.out.println("\n--- Daftar Barang yang Dilaporkan ---");
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Tidak ada barang yang dilaporkan.");
            return;
        }
        for (int i = 0; i < LoginSystem.reportedItems.size(); i++) {
            Item item = LoginSystem.reportedItems.get(i);
            System.out.printf("%d. %s | %s | %s | %s\n", i + 1, item.getItemName(), item.getDescription(),
                    item.getLocation(), item.getStatus());
        }
    }

    public void deleteItem() {
        manageItems();
        if (LoginSystem.reportedItems.isEmpty()) return;

        System.out.print("Masukkan nomor item yang ingin dihapus: ");
        int index = LoginSystem.getInputAngka();
        if (index < 1 || index > LoginSystem.reportedItems.size()) {
            System.out.println("❌ Nomor tidak valid.");
            return;
        }
        try {
            Item removed = LoginSystem.reportedItems.remove(index - 1);
            System.out.println("✅ Barang '" + removed.getItemName() + "' berhasil dihapus.");
        } catch (Exception e) {
            System.out.println("❌ Gagal menghapus item: " + e.getMessage());
        }
    }
}
