package perpustakaan;

public class Anggota implements Peminjaman {
    private String nama;
    private String idAnggota;

    public Anggota(String nama, String idAnggota) {
        this.nama = nama;
        this.idAnggota = idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public void displayInfo() {
        System.out.println("Anggota: " + nama + " (ID: " + idAnggota + ")");
    }

    @Override
    public void pinjamBuku(Buku buku) {
        System.out.println("\uD83D\uDCDA " + nama + " (" + idAnggota + ") meminjam buku: " + buku.getJudul());
    }

    public void pinjamBuku(Buku buku, int durasi) {
        System.out.println("\uD83D\uDCDA " + nama + " (" + idAnggota + ") berhasil meminjam buku: \"" + buku.getJudul() + "\" selama " + durasi + " hari.");
        System.out.println("\uD83D\uDCC5 Pengembalian paling lambat dalam " + durasi + " hari.");
    }

    @Override
    public void kembalikanBuku(Buku buku) {
        System.out.println("\u2705 " + nama + " (" + idAnggota + ") telah mengembalikan buku: \"" + buku.getJudul() + "\" (ID: " + buku.getId() + ")");
    }
}