module org.example.saebis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens org.example.saebis to javafx.fxml;
    exports org.example.saebis;
    exports vue;
    exports modele;
    exports controleur;
}