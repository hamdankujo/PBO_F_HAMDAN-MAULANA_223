package com.praktikum.gui;

import com.praktikum.main.LoginSystem;
import com.praktikum.models.Item;
import com.praktikum.users.Mahasiswa;
import com.praktikum.users.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private final JTable itemTable;
    private final DefaultTableModel itemModel;
    private final DefaultTableModel mahasiswaModel;

    public AdminDashboard() {
        setTitle("Lost & Found Kampus");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel greetingLabel = new JLabel("Halo, ADMIN MAHASIGMA");
        greetingLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        add(greetingLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        // Tabel barang
        itemModel = new DefaultTableModel(new String[]{"Nama", "Lokasi", "Status"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        itemTable = new JTable(itemModel);
        JScrollPane itemScroll = new JScrollPane(itemTable);
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBorder(BorderFactory.createTitledBorder("Laporan Barang"));
        itemPanel.add(itemScroll, BorderLayout.CENTER);

        JButton markClaimedButton = new JButton("Tandai Claimed");
        markClaimedButton.addActionListener(_ -> markItemClaimed());
        itemPanel.add(markClaimedButton, BorderLayout.SOUTH);

        centerPanel.add(itemPanel);

        // Tabel mahasiswa
        mahasiswaModel = new DefaultTableModel(new String[]{"Nama", "NIM"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable mahasiswaTable = new JTable(mahasiswaModel);
        JScrollPane mahasiswaScroll = new JScrollPane(mahasiswaTable);
        JPanel mahasiswaPanel = new JPanel(new BorderLayout());
        mahasiswaPanel.setBorder(BorderFactory.createTitledBorder("Data Mahasiswa"));
        mahasiswaPanel.add(mahasiswaScroll, BorderLayout.CENTER);

        centerPanel.add(mahasiswaPanel);

        add(centerPanel, BorderLayout.CENTER);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(_ -> logout());
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel.add(logoutButton);
        add(southPanel, BorderLayout.SOUTH);

        loadItemData();
        loadMahasiswaData();

        setVisible(true);
    }

    private void loadItemData() {
        itemModel.setRowCount(0);
        for (Item item : LoginSystem.reportedItems) {
            itemModel.addRow(new Object[]{item.getItemName(), item.getLocation(), item.getStatus()});
        }
    }

    private void loadMahasiswaData() {
        mahasiswaModel.setRowCount(0);
        for (User user : LoginSystem.userList) {
            if (user instanceof Mahasiswa) {
                mahasiswaModel.addRow(new Object[]{user.getName(), ((Mahasiswa) user).getId()});
            }
        }
    }

    private void markItemClaimed() {
        int viewRow = itemTable.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih item terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int modelRow = itemTable.convertRowIndexToModel(viewRow);
        Item selectedItem = LoginSystem.reportedItems.get(modelRow);

        if ("Claimed".equalsIgnoreCase(selectedItem.getStatus())) {
            JOptionPane.showMessageDialog(this, "Item sudah ditandai sebagai Claimed.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        selectedItem.setStatus("Claimed");

        // Update langsung baris yang dipilih tanpa reload seluruh tabel
        itemModel.setValueAt("Claimed", modelRow, 2);
        itemTable.repaint(); // Paksa refresh tampilan
    }

    private void logout() {
        dispose();
        new LoginPane();
    }
}
