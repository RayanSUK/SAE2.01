package vue;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.OutputStream;
import java.io.PrintStream;

public class VBoxDroite extends VBox {
    private TextArea textAreaLog;
    public VBoxDroite(){


        VBox texteDroite = new VBox();
        Label label = new Label("Informations :");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        label.setPadding(new Insets(10,10,36,300));
        Separator separateur = new Separator(Orientation.VERTICAL);
        this.getChildren().add(separateur);
        this.getChildren().add(label);
        textAreaLog = new TextArea();
        textAreaLog.setEditable(false);
        textAreaLog.setPrefHeight(640);// Ajuster la hauteur
        textAreaLog.setPrefWidth(640); // Ajuster la largeur
        texteDroite.getChildren().add(textAreaLog);
        this.getChildren().add(texteDroite);

        // Redirection de System.out et System.err vers le PrintStream
        System.setOut(printStream);
        System.setErr(printStream);


    }

    PrintStream printStream = new PrintStream(new OutputStream() {
        @Override
        public void write(int b) {
            // Utilisation de Platform.runLater pour mettre à jour l'interface graphique depuis un thread non JavaFX
            Platform.runLater(() -> textAreaLog.appendText(String.valueOf((char) b)));
        }
    });
}
