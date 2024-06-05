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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * La classe VBoxDroite hérite de la classe VBox contenant une zone de texte qui représente le terminal. Elle se situera à droite de notre carte.
 */
public class VBoxDroite extends VBox {
    /**
     * Zone de texte du terminal
     */
    private TextArea textAreaLog;

    /**
     * Le constructeur de la classe.
     * Initialise les composants de l'interface et configure la redirection des sorties.
     */
    public VBoxDroite() {
        VBox texteDroite = new VBox();
        Label label = new Label("Informations :");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        label.setPadding(new Insets(10, 10, 36, 300));
        Separator separateur = new Separator(Orientation.VERTICAL);
        this.getChildren().add(separateur);
        this.getChildren().add(label);
        textAreaLog = new TextArea();
        textAreaLog.setEditable(false);
        textAreaLog.setPrefHeight(640); // Ajuster la hauteur
        textAreaLog.setPrefWidth(680);  // Ajuster la largeur
        texteDroite.getChildren().add(textAreaLog);
        this.getChildren().add(texteDroite);

        // Redirige les sorties et les erreurs
        PrintStream printStream = new PrintStream(new OutputStream() {
            private final byte[] buffer = new byte[1024];
            private int bufferIndex = 0;

            @Override
            public void write(int b) {
                buffer[bufferIndex++] = (byte) b;
                if (b == '\n') {
                    String text = new String(buffer, 0, bufferIndex, StandardCharsets.UTF_8);
                    Platform.runLater(() -> textAreaLog.appendText(text));
                    bufferIndex = 0;
                }
            }
        }, true, StandardCharsets.UTF_8);

        System.setOut(printStream);
        System.setErr(printStream);

        // Message de bienvenue
        System.out.println("Bienvenue et merci de nous rejoindre. " + "\n" +
                "Pour commencer à jouer, choisissez une des 6 cartes proposé dans le menu des scénarios en haut à gauche de votre écran." + "\n" +
                "Le but du jeu sera de placer tout les cristaux à leurs temples correspondants en faisant le MINIMUM DE PAS POSSIBLE." +"\n"+
                "En bas à gauche de votre écran se trouve les informations du joueur telles que le nombre de pas que vous avez faits ainsi que votre cristal." + "\n" +
                "Si vous voulez découvrir la voie la plus rapide pour finir le jeu, choisissez un algorithme qui jouera à votre place." + "\n"+
                "Amusez-vous bien !" + "\n");
    }
}
