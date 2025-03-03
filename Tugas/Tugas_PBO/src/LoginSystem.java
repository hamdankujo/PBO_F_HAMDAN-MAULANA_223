//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class LoginSystem {
    public static void main(String[] args) {
        Scanner objInput = new Scanner(System.in);

        //data admin
        String username = "Admin223";
        String password = "Password223";

        //data mahasiswa
        String namaMhs = "Hamdan Maulana";
        String nimMhs = "202410370110223";

        System.out.println("Pilih Login:");
        System.out.println("1. Admin");
        System.out.println("2. Mahasiswa");
        System.out.print("Masukan Pilihan: ");

        int pilihan;

        if (objInput.hasNextInt()){
            pilihan = objInput.nextInt();
            objInput.nextLine();
        }else {
            System.out.println("Inputan tidak valid mohon masukkan angka");
            objInput.close();
            return;
        }

        if (pilihan == 1){
            while (true){
                System.out.print("Masukkan Username: ");
                String inputUserName = objInput.nextLine();
                System.out.print("Masukkan Password: ");
                String inputPassword = objInput.nextLine();

                if (inputUserName.equals(username)&&inputPassword.equals(password)){
                    System.out.println("Login Admin Berhasil");
                    break;
                }else {
                    System.out.println("Login gagal! username atau password salah");
                }
            }
        }else if (pilihan == 2){
            while (true){
                System.out.print("Masukkan Nama: ");
                String inpuNamaMhs = objInput.nextLine();
                System.out.print("Masukkan Nim: ");
                String inputNim = objInput.nextLine();

                if (inpuNamaMhs.equals(namaMhs)&&inputNim.equals(nimMhs)){
                    System.out.println("Login Mahasiswa Berhasil");
                    System.out.println("Nama: " + inpuNamaMhs);
                    System.out.println("Nim: " + inputNim);
                    break;
                }else {
                    System.out.println("Login gagal! nama atau nim salah");
                }
            }
        }else{
            System.out.println("inputan tidak valid mohon masukkan angka 1 atau 2");
        }
        objInput.close();
    }
}