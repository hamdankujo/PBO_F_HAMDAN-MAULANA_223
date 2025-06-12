package com.praktikum.gui;

import com.praktikum.models.Item;
import com.praktikum.users.Mahasiswa;
import com.praktikum.main.LoginSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MahasiswaDashboard extends JFrame {
    private final JTextField fieldNamaBarang, fieldLokasi;
    private final DefaultTableModel tableModel;

    public MahasiswaDashboard(Mahasiswa mahasiswa) {

        setTitle("Lost & Found Kampus - Mahasiswa");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Atas
        JPanel atasPanel = new JPanel(new BorderLayout(10, 10));
        JLabel labelWelcome = new JLabel("Selamat datang, " + mahasiswa.getName());
        labelWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        atasPanel.add(labelWelcome, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        JLabel instruksi = new JLabel("Silakan isi detail barang hilang/temuan Anda di bawah ini:");
        formPanel.add(instruksi);

        JLabel labelNama = new JLabel("Nama Barang:");
        fieldNamaBarang = new JTextField(20);
        formPanel.add(labelNama);
        formPanel.add(fieldNamaBarang);

        JLabel labelLokasi = new JLabel("Lokasi Kejadian:");
        fieldLokasi = new JTextField(20);
        formPanel.add(labelLokasi);
        formPanel.add(fieldLokasi);

        JButton btnLaporkan = new JButton("Laporkan");
        JPanel panelLaporkan = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelLaporkan.add(btnLaporkan);

        atasPanel.add(formPanel, BorderLayout.CENTER);
        atasPanel.add(panelLaporkan, BorderLayout.SOUTH);
        add(atasPanel, BorderLayout.NORTH);

        // Tabel Laporan
        tableModel = new DefaultTableModel(new Object[]{"Nama", "Lokasi", "Status"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tableLaporan = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableLaporan);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Daftar Semua Laporan"));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Panel Bawah (Logout + Refresh)
        JButton btnLogout = new JButton("Logout");
        JButton btnRefresh = new JButton("Refresh Status");

        JPanel bawahPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bawahPanel.add(btnRefresh);
        bawahPanel.add(btnLogout);
        add(bawahPanel, BorderLayout.SOUTH);

        // Tombol Laporkan
        btnLaporkan.addActionListener(e -> {
            String namaBarang = fieldNamaBarang.getText();
            String lokasi = fieldLokasi.getText();

            if (namaBarang.isEmpty() || lokasi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nama dan lokasi wajib diisi.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Item item = new Item(namaBarang, "", lokasi, "Reported");
            item.setPelapor(mahasiswa);
            LoginSystem.reportedItems.add(item);

            fieldNamaBarang.setText("");
            fieldLokasi.setText("");

            updateTable();
        });

        // Tombol Logout
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginPane();
        });

        // Tombol Refresh Manual
        btnRefresh.addActionListener(e -> updateTable());

        // Refresh Otomatis Tiap 5 Detik
        Timer refreshTimer = new Timer(5000, e -> updateTable());
        refreshTimer.start();

        updateTable();
        setVisible(true);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Item item : LoginSystem.reportedItems) {
            tableModel.addRow(new Object[]{item.getItemName(), item.getLocation(), item.getStatus()});
        }
    }
}
