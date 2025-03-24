import java.util.Scanner;
import java.util.Random;

abstract class KarakterGame {
    protected String nama;
    protected int kesehatan;
    protected int kekuatan;
    protected String seranganKhusus;

    public KarakterGame(String nama, int kesehatan, int kekuatan, String seranganKhusus) {
        this.nama = nama;
        this.kesehatan = kesehatan;
        this.kekuatan = kekuatan;
        this.seranganKhusus = seranganKhusus;
    }

    public String getNama() {
        return nama;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = Math.max(kesehatan, 0);
    }

    public int getKekuatan() {
        return kekuatan;
    }

    public boolean parry(String lokasiSerangan, String lokasiParry) {
        return lokasiSerangan.equalsIgnoreCase(lokasiParry);
    }

    public abstract void tampilkanInfo();
}

class Pahlawan extends KarakterGame {
    public Pahlawan(String nama, int kekuatan, String seranganKhusus) {
        super(nama, 100, kekuatan, seranganKhusus);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\033[34;1mPahlawan: " + nama + " | Kesehatan: " + kesehatan + " | Serangan Khusus: " + seranganKhusus + "\033[0m");
    }
}

class Musuh extends KarakterGame {
    public Musuh(String nama, int kekuatan, String seranganKhusus) {
        super(nama, 100, kekuatan, seranganKhusus);
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("\033[31;1mMusuh: " + nama + " | Kesehatan: " + kesehatan + " | Serangan Khusus: " + seranganKhusus + "\033[0m");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("\n\033[33;1m==============================");
        System.out.println("       STREET FIGHTER ");
        System.out.println("==============================\033[0m\n");

        String[][] karakterPahlawan = {
                {"Ryu", "Hadouken"},
                {"Chun Li", "Spinning Bird Kick"},
                {"Ken", "Shoryuken"},
                {"Guile", "Sonic Boom"}
        };

        String[][] karakterMusuh = {
                {"M. Bison", "Psycho Crusher"},
                {"Juri", "Fuhajin"},
                {"Akuma", "Raging Demon"},
                {"Vega", "Bloody High Claw"}
        };

        System.out.println("Pilih karakter Anda: ");
        for (int i = 0; i < karakterPahlawan.length; i++) {
            System.out.println("\033[32;1m" + (i + 1) + ". " + karakterPahlawan[i][0] + "\033[0m");
        }
        int pilihanPahlawan = scanner.nextInt() - 1;

        System.out.println("Pilih lawan Anda: ");
        for (int i = 0; i < karakterMusuh.length; i++) {
            System.out.println("\033[31;1m" + (i + 1) + ". " + karakterMusuh[i][0] + "\033[0m");
        }
        int pilihanMusuh = scanner.nextInt() - 1;

        KarakterGame pemain = new Pahlawan(karakterPahlawan[pilihanPahlawan][0], 20, karakterPahlawan[pilihanPahlawan][1]);
        KarakterGame lawan = new Musuh(karakterMusuh[pilihanMusuh][0], 20, karakterMusuh[pilihanMusuh][1]);

        System.out.println("\n\033[36;1mStatus Awal:\033[0m");
        pemain.tampilkanInfo();
        lawan.tampilkanInfo();

        while (pemain.getKesehatan() > 0 && lawan.getKesehatan() > 0) {
            System.out.println("\n\033[33;1mGiliran Anda! Pilih serangan ke: (1) Kepala atau (2) Badan\033[0m");
            int targetSerangan = scanner.nextInt();
            String lokasiSerangan = targetSerangan == 1 ? "Kepala" : "Badan";

            String lokasiParryLawan = random.nextBoolean() ? "Kepala" : "Badan";

            if (lawan.parry(lokasiSerangan, lokasiParryLawan)) {
                System.out.println("\033[31;1m" + lawan.getNama() + " berhasil memblokir serangan Anda!\033[0m");
            } else {
                System.out.println("\033[32;1m" + pemain.getNama() + " menggunakan " + pemain.seranganKhusus + " dan menyerang " + lawan.getNama() + " ke " + lokasiSerangan + " memberikan " + pemain.getKekuatan() + " damage!\033[0m");
                lawan.setKesehatan(lawan.getKesehatan() - pemain.getKekuatan());
            }

            if (lawan.getKesehatan() <= 0) break;

            System.out.println("\n\033[35;1mGiliran lawan!\033[0m");
            String lokasiSeranganLawan = random.nextBoolean() ? "Kepala" : "Badan";

            System.out.println("\033[33;1mPilih pertahanan ke: (1) Kepala atau (2) Badan\033[0m");
            int pilihanParry = scanner.nextInt();
            String lokasiParry = pilihanParry == 1 ? "Kepala" : "Badan";

            if (pemain.parry(lokasiSeranganLawan, lokasiParry)) {
                System.out.println("\033[34;1mAnda berhasil memblokir serangan lawan!\033[0m");
            } else {
                System.out.println("\033[31;1m" + lawan.getNama() + " menggunakan " + lawan.seranganKhusus + " dan menyerang " + pemain.getNama() + " ke " + lokasiSeranganLawan + " memberikan " + lawan.getKekuatan() + " damage!\033[0m");
                pemain.setKesehatan(pemain.getKesehatan() - lawan.getKekuatan());
            }
        }

        System.out.println("\n\033[36;1mHasil Pertarungan:\033[0m");
        pemain.tampilkanInfo();
        lawan.tampilkanInfo();

        if (pemain.getKesehatan() > 0) {
            System.out.println("\n\033[35;1mSELAMAT! ANDA MENANG!\033[0m");
        } else {
            System.out.println("\n\033[31;1mANDA KALAH! LAWAN MENANG!\033[0m");
        }

        scanner.close();
    }
}
