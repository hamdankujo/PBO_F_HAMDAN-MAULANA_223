public class Mahasiswa {
    private final String nama;
    private final String nim;

    public Mahasiswa() {
        this.nama = "Hamdan Maulana";
        this.nim = "202410370110223";
    }

    public boolean login(String inputNama, String inputNim) {
        return inputNama.equals(nama) && inputNim.equals(nim);
    }

    public void tampilkanData() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM : " + nim);
    }
}
