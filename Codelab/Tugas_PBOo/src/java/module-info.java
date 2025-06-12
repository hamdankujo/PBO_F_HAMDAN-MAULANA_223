module com.example.tugas_pboo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.tugas_pboo to javafx.fxml;
    exports com.example.tugas_pboo;
}