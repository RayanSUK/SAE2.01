module org.example.saebis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.example.saebis to javafx.fxml;
    exports org.example.saebis;
    exports vue;
}