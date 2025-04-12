public class Admin {
    private final String username;
    private final String password;

    public Admin() {
        this.username = "Admin223";
        this.password = "Password223";
    }

    public boolean login(String inputUsername, String inputPassword) {
        return inputUsername.equals(username) && inputPassword.equals(password);
    }
}
