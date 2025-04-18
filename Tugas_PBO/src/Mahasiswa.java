public class Mahasiswa extends User {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }

    @Override
    public boolean login(String inputNama, String inputNim) {
        return getNama().equalsIgnoreCase(inputNama) && getNim().equals(inputNim);
    }

    @Override
    public void displayInfo() {
        System.out.println("====================================");
        System.out.println("🎓  Selamat datang, Mahasigma!");
        super.displayInfo();
        System.out.println("====================================");
    }
}
