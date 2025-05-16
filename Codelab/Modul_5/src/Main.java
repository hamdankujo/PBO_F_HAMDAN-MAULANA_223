import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class ManajemenStok {
    private static final ArrayList<Barang> daftarBarang = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarBarang.add(new Barang("Buku", 10));
        daftarBarang.add(new Barang("Pensil", 20));
        daftarBarang.add(new Barang("Penghapus", 15));

        boolean jalan = true;
        while (jalan) {
            tampilkanMenu();
            int pilihan = ambilInputAngka("Pilih opsi: ");

            switch (pilihan) {
                case 1: tambahBarang(); break;
                case 2: tampilkanSemuaBarang(); break;
                case 3: kurangiStokBarang(); break;
                case 4: cariBarang(); break;
                case 5: hapusBarang(); break;
                case 6: updateBarang(); break;
                case 7: tampilkanTotalStok(); break;
                case 0:
                    jalan = false;
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }

        scanner.close();
    }

    private static void tampilkanMenu() {
        System.out.println("\n=== Menu Manajemen Stok ===");
        System.out.println("1. Tambah Barang Baru");
        System.out.println("2. Tampilkan Semua Barang");
        System.out.println("3. Kurangi Stok Barang");
        System.out.println("4. Cari Barang");
        System.out.println("5. Hapus Barang");
        System.out.println("6. Update Barang");
        System.out.println("7. Tampilkan Total Semua Stok");
        System.out.println("0. Keluar");
    }

    private static int ambilInputAngka(String pesan) {
        while (true) {
            try {
                System.out.print(pesan);
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine();
            }
        }
    }

    private static void tambahBarang() {
        System.out.print("Masukkan nama barang: ");
        String nama = scanner.nextLine();

        for (Barang b : daftarBarang) {
            if (b.getNama().equalsIgnoreCase(nama)) {
                System.out.println("Barang dengan nama tersebut sudah ada.");
                return;
            }
        }

        int stok = ambilInputAngka("Masukkan stok awal: ");
        daftarBarang.add(new Barang(nama, stok));
        System.out.println("Barang berhasil ditambahkan.");
    }

    private static void tampilkanSemuaBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Stok barang kosong.");
            return;
        }

        System.out.println("+-------+----------------------+--------+");
        System.out.printf("| %-5s | %-20s | %-6s |\n", "Index", "Nama Barang", "Stok");
        System.out.println("+-------+----------------------+--------+");

        for (int i = 0; i < daftarBarang.size(); i++) {
            Barang b = daftarBarang.get(i);
            System.out.printf("| %-5d | %-20s | %-6d |\n", i, b.getNama(), b.getStok());
        }

        System.out.println("+-------+----------------------+--------+");
    }


    private static void kurangiStokBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang untuk dikurangi stok.");
            return;
        }

        tampilkanSemuaBarang();
        int indeks = ambilInputAngka("Masukkan nomor indeks barang: ");

        try {
            Barang barang = daftarBarang.get(indeks);
            int jumlah = ambilInputAngka("Masukkan jumlah yang akan diambil: ");

            if (jumlah > barang.getStok()) {
                throw new StokTidakCukupException("Stok hanya tersisa " + barang.getStok());
            }

            barang.setStok(barang.getStok() - jumlah);
            System.out.println("Stok berhasil dikurangi. Sisa: " + barang.getStok());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Indeks tidak valid.");
        } catch (StokTidakCukupException e) {
            System.out.println("Gagal: " + e.getMessage());
        }
    }

    private static void cariBarang() {
        System.out.print("Masukkan nama barang yang dicari: ");
        String nama = scanner.nextLine();
        boolean ditemukan = false;

        for (Barang b : daftarBarang) {
            if (b.getNama().equalsIgnoreCase(nama)) {
                System.out.println("Ditemukan: " + b);
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    private static void hapusBarang() {
        tampilkanSemuaBarang();
        int indeks = ambilInputAngka("Masukkan indeks barang yang akan dihapus: ");
        try {
            Barang dihapus = daftarBarang.remove(indeks);
            System.out.println("Barang \"" + dihapus.getNama() + "\" berhasil dihapus.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Indeks tidak valid.");
        }
    }

    private static void updateBarang() {
        tampilkanSemuaBarang();
        int indeks = ambilInputAngka("Masukkan indeks barang yang akan diupdate: ");

        try {
            Barang barang = daftarBarang.get(indeks);
            System.out.println("1. Ubah Nama");
            System.out.println("2. Ubah Stok");
            int opsi = ambilInputAngka("Pilih opsi update: ");

            if (opsi == 1) {
                System.out.print("Masukkan nama baru: ");
                String namaBaru = scanner.nextLine();
                barang.setNama(namaBaru);
                System.out.println("Nama berhasil diubah.");
            } else if (opsi == 2) {
                int stokBaru = ambilInputAngka("Masukkan stok baru: ");
                barang.setStok(stokBaru);
                System.out.println("Stok berhasil diubah.");
            } else {
                System.out.println("Opsi tidak valid.");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Indeks tidak valid.");
        }
    }

    private static void tampilkanTotalStok() {
        int total = 0;
        for (Barang b : daftarBarang) {
            total += b.getStok();
        }
        System.out.println("Total semua stok: " + total);
    }
}
