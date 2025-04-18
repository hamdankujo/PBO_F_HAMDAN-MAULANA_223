public class Admin extends User {
    private final String username;
    private final String password;

    public Admin(String nama, String nim, String username, String password) {
        super(nama, nim);
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    @Override
    public void displayInfo() {
        System.out.println("====================================");
        System.out.println("👑  Selamat datang, Admin Ganteng!");
        super.displayInfo();
        System.out.println("====================================");
    }
}
