package com.example.modul_6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class TebakAngka extends Application {

    private int angkaTarget;
    private int jumlahTebakan;

    private final Label labelPetunjuk = new Label("Tebak angka dari 1 sampai 100 🎯");
    private final Label labelFeedback = new Label("Ayo mulai menebak!");
    private final Label labelJumlah = new Label("📊 Total percobaan: 0");

    private final TextField fieldInput = new TextField();
    private final Button tombolTebak = new Button("✅ Tebak");
    private final Button tombolReset = new Button("🔄 Ulang");

    private void mulaiBaru() {
        angkaTarget = new Random().nextInt(100) + 1;
        jumlahTebakan = 0;
        fieldInput.clear();
        fieldInput.setDisable(false);
        tombolTebak.setDisable(false);

        labelFeedback.setText("Ayo mulai menebak!");
        labelFeedback.setStyle("-fx-text-fill: black;");
        labelJumlah.setText("📊 Total percobaan: 0");
    }

    private void prosesTebakan() {
        String input = fieldInput.getText();
        try {
            int tebakan = Integer.parseInt(input);
            jumlahTebakan++;
            labelJumlah.setText("📊 Total percobaan: " + jumlahTebakan);

            if (tebakan < angkaTarget) {
                labelFeedback.setText("⬇️ Terlalu kecil!");
                labelFeedback.setStyle("-fx-text-fill: orange;");
            } else if (tebakan > angkaTarget) {
                labelFeedback.setText("⬆️ Terlalu besar!");
                labelFeedback.setStyle("-fx-text-fill: orange;");
            } else {
                labelFeedback.setText("🎉 Benar sekali! Selamat!");
                labelFeedback.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                fieldInput.setDisable(true);
                tombolTebak.setDisable(true);
            }
        } catch (NumberFormatException e) {
            labelFeedback.setText("❌ Masukkan angka valid!");
            labelFeedback.setStyle("-fx-text-fill: red;");
        }

        fieldInput.clear();
    }

    @Override
    public void start(Stage primaryStage) {
        mulaiBaru();

        labelPetunjuk.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        fieldInput.setPromptText("Masukkan angka tebakan");

        tombolTebak.setOnAction(e -> prosesTebakan());
        tombolReset.setOnAction(e -> mulaiBaru());

        HBox tombolBox = new HBox(10, tombolTebak, tombolReset);
        tombolBox.setStyle("-fx-alignment: center;");

        VBox root = new VBox(12, labelPetunjuk, fieldInput, tombolBox, labelFeedback, labelJumlah);
        root.setStyle("-fx-padding: 25px; -fx-alignment: center; -fx-background-color: #f0faff;");

        Scene scene = new Scene(root, 360, 260);
        primaryStage.setTitle("Game Tebak Angka 🎮");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
