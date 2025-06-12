package praktikum.users;

import praktikum.actions.MahasiswaActions;
import com.praktikum.main.LoginSystem;
import praktikum.models.Item;

public class Mahasiswa extends User implements MahasiswaActions {

    public Mahasiswa(String name, String id, String username, String password) {
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
            System.out.println("\n--- Menu Mahasiswa ---");
            System.out.println("1. Laporkan Barang");
            System.out.println("2. Lihat Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih dong: ");

            int pilihan = LoginSystem.getInputAngka();
            switch (pilihan) {
                case 1 -> reportItem();
                case 2 -> viewReports();
                case 0 -> running = false;
                default -> System.out.println("❌ Pilihan tidak valid.");
            }
        }
    }

    @Override
    public void reportItem() {
        System.out.print("Nama Barang: ");
        String nama = LoginSystem.scanner.nextLine();
        System.out.print("Deskripsi: ");
        String deskripsi = LoginSystem.scanner.nextLine();
        System.out.print("Lokasi: ");
        String lokasi = LoginSystem.scanner.nextLine();
        System.out.print("Status (Hilang/Ditemukan): ");
        String status = LoginSystem.scanner.nextLine();

        // Debug cek input
        // System.out.println("DEBUG input: " + nama + ", " + deskripsi + ", " + lokasi + ", " + status);

        Item item = new Item(nama, deskripsi, lokasi, status);
        LoginSystem.reportedItems.add(item);
        System.out.println("✅ Laporan berhasil ditambahkan.");
    }

    @Override
    public void viewOwnReports() {

    }

    @Override
    public void viewReports() {
        System.out.println("\n--- Laporan Barang ---");
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Tidak ada laporan barang.");
            return;
        }
        for (Item item : LoginSystem.reportedItems) {
            System.out.println(item.getItemName() + " | " + item.getDescription() + " | " +
                    item.getLocation() + " | " + item.getStatus());
        }
    }
}
