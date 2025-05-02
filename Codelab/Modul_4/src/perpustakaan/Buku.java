package perpustakaan;

public abstract class Buku {
    protected int id;
    protected String judul;
    protected String penulis;

    public Buku(int id, String judul, String penulis) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public abstract void displayInfo();
}