import perpustakaan.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Buku> daftarBuku = new ArrayList<>();
        List<Anggota> daftarAnggota = new ArrayList<>();

        daftarAnggota.add(new Anggota("Hamdan Maulana", "202410370110223"));
        daftarAnggota.add(new Anggota("Tanjidor dor dor", "202410370116969"));

        Anggota anggotaAktif = null;
        while (anggotaAktif == null) {
            System.out.print("Masukkan ID anggota untuk login: ");
            String idInput = input.nextLine();
            for (Anggota a : daftarAnggota) {
                if (a.getIdAnggota().equals(idInput)) {
                    anggotaAktif = a;
                    break;
                }
            }
            if (anggotaAktif == null) {
                System.out.println("❌ ID tidak ditemukan. Silakan coba lagi.\n");
            }
        }
        System.out.println("✅ Login berhasil! Selamat datang, " + anggotaAktif.getNama());

        while (true) {
            System.out.println("\n===== Menu Utama =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Lihat Daftar Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    try {
                        System.out.print("Masukkan ID buku: ");
                        int id = Integer.parseInt(input.nextLine());
                        System.out.print("Masukkan judul buku: ");
                        String judul = input.nextLine();
                        System.out.print("Masukkan penulis buku: ");
                        String penulis = input.nextLine();
                        System.out.print("Jenis buku (1. Fiksi | 2. Non-Fiksi): ");
                        String jenis = input.nextLine();

                        Buku buku;
                        if (jenis.equals("1")) {
                            buku = new Fiksi(id, judul, penulis);
                        } else if (jenis.equals("2")) {
                            buku = new NonFiksi(id, judul, penulis);
                        } else {
                            System.out.println("❌ Jenis buku tidak valid. Kembali ke menu.");
                            break;
                        }
                        daftarBuku.add(buku);
                        System.out.println("✅ Buku berhasil ditambahkan!");
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Input angka tidak valid.");
                    }
                    break;

                case "2":
                    if (daftarBuku.isEmpty()) {
                        System.out.println("📭 Tidak ada buku di perpustakaan.");
                    } else {
                        System.out.println("\n📚 Daftar Buku:");
                        for (Buku b : daftarBuku) {
                            b.displayInfo();
                        }
                    }
                    break;

                case "3":
                    if (daftarBuku.isEmpty()) {
                        System.out.println("📭 Tidak ada buku untuk dipinjam.");
                        break;
                    }
                    System.out.println("\n📚 Daftar Buku Tersedia:");
                    for (Buku b : daftarBuku) {
                        b.displayInfo();
                    }
                    try {
                        System.out.print("\nMasukkan ID buku yang ingin dipinjam: ");
                        int idCari = Integer.parseInt(input.nextLine());
                        System.out.print("Durasi pinjam (hari): ");
                        int durasi = Integer.parseInt(input.nextLine());

                        Optional<Buku> bukuPinjam = daftarBuku.stream()
                                .filter(b -> b.getId() == idCari).findFirst();

                        if (bukuPinjam.isPresent()) {
                            anggotaAktif.pinjamBuku(bukuPinjam.get(), durasi);
                        } else {
                            System.out.println("❌ Buku tidak ditemukan.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Input harus berupa angka.");
                    }
                    break;

                case "4":
                    System.out.print("Masukkan ID buku yang ingin dikembalikan: ");
                    try {
                        int idKembali = Integer.parseInt(input.nextLine());
                        Optional<Buku> bukuKembali = daftarBuku.stream()
                                .filter(b -> b.getId() == idKembali).findFirst();

                        if (bukuKembali.isPresent()) {
                            anggotaAktif.kembalikanBuku(bukuKembali.get());
                        } else {
                            System.out.println("❌ Buku tidak ditemukan.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Masukkan ID angka yang valid.");
                    }
                    break;

                case "5":
                    System.out.println("👋 Terima kasih telah menggunakan sistem. Sampai jumpa!");
                    return;

                default:
                    System.out.println("❌ Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
