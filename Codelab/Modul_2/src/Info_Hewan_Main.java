import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Hewan {
    String nama;
    String jenis;
    String suara;

    Hewan(String nama, String jenis, String suara) {
        this.nama = nama;
        this.jenis = jenis;
        this.suara = suara;
    }

    void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Jenis: " + jenis);
        System.out.println("Suara: " + suara);
        System.out.println();
    }
}

public class Info_Hewan_Main {
    public static void main(String[] args) {
        Map<String, Hewan> hewanMap = new HashMap<>();
        hewanMap.put("Kucing", new Hewan("Kucing", "Mamalia", "Nyann~~"));
        hewanMap.put("Anjing", new Hewan("Anjing", "Mamalia", "Woof-Woof!!"));
        hewanMap.put("Ayam" , new Hewan("Ayam" , "Aves" , "Petok-Petok^^"));
        hewanMap.put("Kambing" , new Hewan("Kambing" , "Mamalia" , "Mbeeeek~~"));
        hewanMap.put("Ular" , new Hewan("Ular" , "Reptil" , "Sssstttt~~~"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Masukkan nama hewan (atau ketik 'exit' untuk keluar): ");
            String inputNama = scanner.nextLine();

            if (inputNama.equalsIgnoreCase("exit")) {
                System.out.println("Terima kasih Sayang!");
                break;
            }

            Hewan hewan = hewanMap.get(inputNama);
            if (hewan != null) {
                hewan.tampilkanInfo();
            } else {
                System.out.println("Hewan tidak ditemukan!");
            }
        }
        scanner.close();
    }
}
