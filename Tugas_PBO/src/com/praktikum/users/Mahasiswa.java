package com.praktikum.users;

import com.praktikum.actions.MahasiswaActions;
import java.util.Scanner;

public class Mahasiswa extends User implements MahasiswaActions {
    private final Scanner input = new Scanner(System.in);

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim) {
        return getNama().equalsIgnoreCase(inputNama) && getNim().equals(inputNim);
    }

    @Override
    public void displayAppMenu() {
        int pilihan;
        do {
            System.out.println("\n--- Menu Mahasiswa ---");
            System.out.println("1. Laporkan Barang Temuan/Hilang");
            System.out.println("2. Lihat Daftar Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilihan Anda: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1 -> reportItem();
                case 2 -> viewReportedItems();
                case 0 -> System.out.println("👋 Logout berhasil.");
                default -> System.out.println("❌ Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    @Override
    public void reportItem() {
        System.out.println("--- Form Laporan Barang ---");
        System.out.print("Nama Barang        : ");
        String namaBarang = input.nextLine();
        System.out.print("Deskripsi Barang   : ");
        String deskripsi = input.nextLine();
        System.out.print("Lokasi Ditemukan   : ");
        String lokasi = input.nextLine();

        System.out.println("✅ Laporan berhasil dikirim.");
        System.out.println("Detail:");
        System.out.println("Barang   : " + namaBarang);
        System.out.println("Deskripsi: " + deskripsi);
        System.out.println("Lokasi   : " + lokasi);
    }

    @Override
    public void viewReportedItems() {
        System.out.println(">> Fitur Lihat Laporan Belum Tersedia <<");
    }

    @Override
    public void displayInfo() {
        System.out.println("====================================");
        System.out.println("🎓  Selamat datang, Mahasigma!");
        super.displayInfo();
        System.out.println("====================================");
    }
}
