package com.praktikum.main;

import com.praktikum.users.User;

import java.util.Scanner;

public class LoginSystem {
    private Scanner input;
    private User admin;
    private User mahasiswa;

    public static void main(String[] args) {
        LoginSystem app = new LoginSystem();
        app.start();
    }

    public void start() {
        input = new Scanner(System.in);
        admin = new User("Admin Mahasigma", "202410370110223") {
            @Override
            public boolean login(String input1, String input2) {
                return false;
            }

            @Override
            public void displayAppMenu() {

            }
        };
        mahasiswa = new User("Admin Mahasigma", "202410370110223") {
            @Override
            public boolean login(String input1, String input2) {
                return false;
            }

            @Override
            public void displayAppMenu() {

            }
        };

        boolean running = true;

        while (running) {
            System.out.println("\n====================================");
            System.out.println("         SELAMAT DATANG DI");
            System.out.println("       SISTEM LOGIN MAHASIGMA");
            System.out.println("====================================");
            System.out.println("1. Login sebagai Admin");
            System.out.println("2. Login sebagai Mahasiswa");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan Lee: ");

            int pilihan = -1;
            if (input.hasNextInt()) {
                pilihan = input.nextInt();
                input.nextLine();
            } else {
                input.nextLine();
            }

            switch (pilihan) {
                case 1 -> loginSebagaiAdmin();
                case 2 -> loginSebagaiMahasiswa();
                case 0 -> {
                    running = false;
                    System.out.println("Terima kasih telah menggunakan sistem login. See You Ganteng!");
                }
                default -> System.out.println("❌ Waduh Pilihan tidak valid. Coba lagi lee.");
            }
        }

        input.close();
    }

    private void loginSebagaiAdmin() {
        System.out.println("\n--- Login Admin ---");
        System.out.print("Username : ");
        String username = input.nextLine();
        System.out.print("Password : ");
        String password = input.nextLine();

        if (admin.login(username, password)) {
            System.out.println("✅ Horee Login berhasil!");
            admin.displayInfo();
            User user = admin; // Polymorphism
            user.displayAppMenu();
        } else {
            System.out.println("❌ Aduh Login gagal! Username atau password salah.");
        }
    }

    private void loginSebagaiMahasiswa() {
        System.out.println("\n--- Login Mahasiswa ---");
        System.out.print("Nama : ");
        String nama = input.nextLine();
        System.out.print("NIM  : ");
        String nim = input.nextLine();

        if (mahasiswa.login(nama, nim)) {
            System.out.println("✅ Horee Login berhasil!");
            mahasiswa.displayInfo();
            User user = mahasiswa; // Polymorphism
            user.displayAppMenu();
        } else {
            System.out.println("❌ Aduh Login gagal! Nama atau NIM salah.");
        }
    }
}
