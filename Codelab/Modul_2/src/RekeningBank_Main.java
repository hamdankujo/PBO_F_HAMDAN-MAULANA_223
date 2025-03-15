import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class RekeningBank {
    String nomorRekening;
    String namaPemilik;
    double saldo;

    RekeningBank(String nomorRekening, String namaPemilik, double saldo) {
        this.nomorRekening = nomorRekening;
        this.namaPemilik = namaPemilik;
        this.saldo = saldo;
    }

    void tampilkanInfo() {
        System.out.println("Nomor Rekening: " + nomorRekening);
        System.out.println("Nama Pemilik: " + namaPemilik);
        System.out.println("Saldo: Rp " + saldo);
        System.out.println();
    }

    void setorUang(double jumlah) {
        if (jumlah > 0) {
            saldo += jumlah;
            System.out.println("Berhasil menyetor Rp " + jumlah);
            System.out.println("Saldo saat ini: Rp " + saldo);
        } else {
            System.out.println("Jumlah setoran harus lebih dari 0.");
        }
    }

    void tarikUang(double jumlah) {
        if (jumlah > 0 && jumlah <= saldo) {
            saldo -= jumlah;
            System.out.println("Berhasil menarik Rp " + jumlah);
            System.out.println("Saldo saat ini: Rp " + saldo);
        } else {
            System.out.println("Penarikan gagal. Saldo tidak mencukupi atau jumlah tidak valid.");
        }
    }

    boolean transferUang(RekeningBank tujuan, double jumlah) {
        if (jumlah > 0 && jumlah <= saldo) {
            saldo -= jumlah;
            tujuan.saldo += jumlah;
            System.out.println("Berhasil transfer Rp " + jumlah + " ke " + tujuan.namaPemilik);
            System.out.println("Saldo saat ini: Rp " + saldo);
            return true;
        } else {
            System.out.println("Transfer gagal. Saldo tidak mencukupi atau jumlah tidak valid.");
            return false;
        }
    }
}

class RekeningBank_main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, RekeningBank> daftarRekening = new HashMap<>();

        RekeningBank rekening1 = new RekeningBank("202410370110223", "HAMDAN MAULANA", 8000000);
        daftarRekening.put(rekening1.nomorRekening, rekening1);

        while (true) {
            System.out.println("\n=== ATM SIGMA BANK ===");
            System.out.println("1. Cek Saldo");
            System.out.println("2. Setor Uang");
            System.out.println("3. Tarik Uang");
            System.out.println("4. Tambah Nomor Rekening Tujuan");
            System.out.println("5. Transfer Uang");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    rekening1.tampilkanInfo();
                    break;
                case 2:
                    System.out.print("Masukkan jumlah setoran: Rp ");
                    double setor = scanner.nextDouble();
                    rekening1.setorUang(setor);
                    break;
                case 3:
                    System.out.print("Masukkan jumlah penarikan: Rp ");
                    double tarik = scanner.nextDouble();
                    rekening1.tarikUang(tarik);
                    break;
                case 4:
                    System.out.print("Masukkan nomor rekening tujuan: ");
                    String nomorBaru = scanner.nextLine();
                    System.out.print("Masukkan nama pemilik rekening: ");
                    String namaBaru = scanner.nextLine();
                    System.out.print("Masukkan saldo awal: Rp ");
                    double saldoBaru = scanner.nextDouble();
                    RekeningBank rekeningBaru = new RekeningBank(nomorBaru, namaBaru, saldoBaru);
                    daftarRekening.put(nomorBaru, rekeningBaru);
                    System.out.println("Rekening tujuan berhasil ditambahkan.");
                    break;
                case 5:
                    System.out.print("Masukkan nomor rekening tujuan: ");
                    String nomorTujuan = scanner.nextLine();
                    if (daftarRekening.containsKey(nomorTujuan)) {
                        RekeningBank tujuan = daftarRekening.get(nomorTujuan);
                        System.out.print("Masukkan jumlah transfer: Rp ");
                        double jumlahTransfer = scanner.nextDouble();
                        boolean sukses = rekening1.transferUang(tujuan, jumlahTransfer);
                        if (sukses) {
                            System.out.println("Transfer berhasil dilakukan.");
                        } else {
                            System.out.println("Transfer gagal.");
                        }
                    } else {
                        System.out.println("Nomor rekening tujuan tidak ditemukan.");
                    }
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan SIGMA BANK.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }
}
