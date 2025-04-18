public class User {
    private final String nama;
    private final String nim;

    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public boolean login(String input1, String input2) {
        return false;
    }

    public void displayInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM : " + nim);
    }
}
