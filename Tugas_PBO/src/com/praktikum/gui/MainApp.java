package com.praktikum.gui;

import com.praktikum.main.LoginSystem;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        LoginSystem.seedUsers();
        SwingUtilities.invokeLater(() -> new LoginPane());
    }
}
