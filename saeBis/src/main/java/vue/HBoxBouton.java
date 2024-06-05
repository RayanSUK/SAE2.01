package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
/**
 * La classe HBoxBouton permet de créer un HBox contenant deux boutons et un label
 */
public class HBoxBouton extends HBox {

    /**
     * Bouton pour échanger un cristal
     */
    private Button boutonEchanger;

    /**
     * Button pour déposer un cristal
     */
    private Button boutonDeposer;

    /**
     * Bouton pour recharger/réinitialisé le jeu
     */
    private Button boutonRestart;

    /**
     * Label lorsque le joueur gagne
     */
    private Label labelVictoire;

    /**
     * Constructeur de la classe.
     * Instancie les 3 boutons et les mets sous écoute pour pouvoir communiquer avec le contrôleur
     * Les boutons sont associés à l'EventHandler, qui gère les événements de clic
     * @param eventHandler le event Handler
     */
    public HBoxBouton(EventHandler<ActionEvent> eventHandler) {

        // le bouton pour échanger les cristaux
        boutonEchanger = new Button("Échanger les cristaux");
        boutonEchanger.setOnAction(eventHandler);
        this.getChildren().add(boutonEchanger);

        // le bouton pour déposer le cristal
        boutonDeposer = new Button("Déposer cristal");
        boutonDeposer.setOnAction(eventHandler);
        this.getChildren().add(boutonDeposer);

        // le bouton pour réinitialiser
        boutonRestart = new Button("Réinitialiser");
        boutonRestart.setOnAction(eventHandler);
        this.getChildren().add(boutonRestart);

        //Le label de victoire
        labelVictoire = new Label("Victoire");
        labelVictoire.setVisible(false);
        labelVictoire.setPadding(new Insets(5,0,0,65));
        labelVictoire.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        this.getChildren().add(labelVictoire);

    }

    /**
     * Méthode qui vérifie si la partie est gagnée pour afficher le label victoire.
     */
    public void conditionVictoire(){
        labelVictoire.setVisible(VBoxGauche.getApprenti().victoire());
    }

    /**
     * Accesseur au champ
     * @return boutonDéposer
     */
    public Button getBoutonDeposer() {
        return boutonDeposer;
    }

    /**
     * Accesseur au champ
     * @return boutonEchanger
     */

    public Button getBoutonEchanger() {
        return boutonEchanger;
    }

    /**
     * Accesseur au champ
     * @return labelVictoire
     */

    public Label getLabelVictoire() {
        return labelVictoire;
    }
}
