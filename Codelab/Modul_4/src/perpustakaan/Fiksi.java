package perpustakaan;

public class Fiksi extends Buku {
    public Fiksi(int id, String judul, String penulis) {
        super(id, judul, penulis);
    }

    @Override
    public void displayInfo() {
        System.out.println("\uD83D\uDCD5 [Fiksi] ID: " + id + " | " + judul + " oleh " + penulis);
    }
}