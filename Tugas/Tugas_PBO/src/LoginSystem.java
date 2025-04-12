import java.util.Scanner;

public class LoginSystem {
    private Scanner objInput;
    private Admin admin;
    private Mahasiswa mahasiswa;

    public static void main(String[] args) {
        LoginSystem system = new LoginSystem();
        system.pilihan();
    }

    public void pilihan() {
        objInput = new Scanner(System.in);
        admin = new Admin();
        mahasiswa = new Mahasiswa();

        System.out.println("Pilih Login:");
        System.out.println("1. LoginSystem.Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukkan Pilihan: ");

        int pilihan;
        if (objInput.hasNextInt()) {
            pilihan = objInput.nextInt();
            objInput.nextLine();
        } else {
            System.out.println("Inputan tidak valid, mohon masukkan angka.");
            objInput.close();
            return;
        }

        switch (pilihan) {
            case 1:
                loginSebagaiAdmin();
                break;
            case 2:
                loginSebagaiMahasiswa();
                break;
            default:
                System.out.println("Inputan tidak valid, mohon masukkan angka 1 atau 2.");
        }

        objInput.close();
    }

    private void loginSebagaiAdmin() {
        while (true) {
            System.out.print("Masukkan Username: ");
            String inputUsername = objInput.nextLine();
            System.out.print("Masukkan Password: ");
            String inputPassword = objInput.nextLine();

            if (admin.login(inputUsername, inputPassword)) {
                System.out.println("Login LoginSystem.Admin Berhasil");
                break;
            } else {
                System.out.println("Login gagal! Username atau password salah.");
            }
        }
    }

    private void loginSebagaiMahasiswa() {
        while (true) {
            System.out.print("Masukkan Nama: ");
            String inputNama = objInput.nextLine();
            System.out.print("Masukkan NIM: ");
            String inputNim = objInput.nextLine();

            if (mahasiswa.login(inputNama, inputNim)) {
                System.out.println("Login Mahasiswa Berhasil");
                mahasiswa.tampilkanData();
                break;
            } else {
                System.out.println("Login gagal! Nama atau NIM salah.");
            }
        }
    }

    public static class Admin {
        private final String username;
        private final String password;

        public Admin() {
            this.username = "Admin223";
            this.password = "Password223";
        }

        public boolean login(String inputUsername, String inputPassword) {
            return inputUsername.equals(username) && inputPassword.equals(password);
        }
    }
}
