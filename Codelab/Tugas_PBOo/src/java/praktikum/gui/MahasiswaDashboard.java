package praktikum.gui;

import praktikum.data.DataStore;
import praktikum.models.Item;

import javax.swing.*;
import java.awt.*;

public class MahasiswaDashboard extends JFrame {
    public MahasiswaDashboard() {
        setTitle("Dashboard Mahasiswa");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField namaField = new JTextField();
        JTextField deskripsiField = new JTextField();
        JTextField lokasiField = new JTextField();
        JTextField statusField = new JTextField();

        panel.add(new JLabel("Nama Barang:"));
        panel.add(namaField);
        panel.add(new JLabel("Deskripsi:"));
        panel.add(deskripsiField);
        panel.add(new JLabel("Lokasi:"));
        panel.add(lokasiField);
        panel.add(new JLabel("Status (Hilang/Ditemukan):"));
        panel.add(statusField);

        JButton reportBtn = new JButton("Laporkan Barang");
        reportBtn.addActionListener(e -> {
            String nama = namaField.getText();
            String deskripsi = deskripsiField.getText();
            String lokasi = lokasiField.getText();
            String status = statusField.getText();
            DataStore.reportedItems.add(new Item(nama, deskripsi, lokasi, status));
            JOptionPane.showMessageDialog(this, "Barang berhasil dilaporkan!");
        });

        JButton lihatBtn = new JButton("Lihat Laporan");
        lihatBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder("\n--- Daftar Laporan ---\n");
            for (Item item : DataStore.reportedItems) {
                sb.append(item.getItemName()).append(" | ")
                        .append(item.getDescription()).append(" | ")
                        .append(item.getLocation()).append(" | ")
                        .append(item.getStatus()).append("\n");
            }
            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea));
        });

        panel.add(reportBtn);
        panel.add(lihatBtn);

        add(panel);
        setVisible(true);
    }
}
