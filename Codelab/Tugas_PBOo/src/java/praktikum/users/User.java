package praktikum.users;


import praktikum.models.Item;

public abstract class User {
    protected String name;
    protected String id;
    protected String username;
    protected String password;

    public User(String name, String id, String username, String password) {
        this.name = name;
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() { // ✅ ini yang ditambahkan
        return name;
    }

    public abstract boolean checkPassword(String password);

    public abstract void displayAppMenu();

    public void displayInfo() {
        System.out.println("Nama: " + name + ", ID: " + id + ", Username: " + username);
    }

    public void viewReports() {
        System.out.println("\n--- Laporan Barang ---");
        if (LoginSystem.reportedItems.isEmpty()) {
            System.out.println("Tidak ada laporan barang.");
            return;
        }
        for (Item item : LoginSystem.reportedItems) {
            System.out.println(item.getItemName() + " | " + item.getDescription() + " | " +
                    item.getLocation() + " | " + item.getStatus());
        }
    }
}
