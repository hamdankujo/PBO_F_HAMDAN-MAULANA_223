package perpustakaan;

public class NonFiksi extends Buku {
    public NonFiksi(int id, String judul, String penulis) {
        super(id, judul, penulis);
    }

    @Override
    public void displayInfo() {
        System.out.println("\uD83D\uDCD7 [Non-Fiksi] ID: " + id + " | " + judul + " oleh " + penulis);
    }
}