package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;



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
     * Label lorsque le joueur gagne
     */
    private Label labelVictoire;

    public HBoxBouton(EventHandler<ActionEvent> eventHandler) {
        boutonEchanger = new Button("Échanger les cristaux");
        boutonEchanger.setOnAction(eventHandler);
        this.getChildren().add(boutonEchanger);

        // le bouton pour déposer le cristal
        boutonDeposer = new Button("Déposer cristal");
        boutonDeposer.setOnAction(eventHandler);
        this.getChildren().add(boutonDeposer);

        //Le label de victoire
        labelVictoire = new Label("Victoire");
        labelVictoire.setVisible(false);
        labelVictoire.setPadding(new Insets(5,0,0,65));
        labelVictoire.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        this.getChildren().add(labelVictoire);

    }

    public void conditionVictoire(){
        labelVictoire.setVisible(VBoxRoot.getApprenti().victoire());
    }

    public Button getBoutonDeposer() {
        return boutonDeposer;
    }

    public Button getBoutonEchanger() {
        return boutonEchanger;
    }

    public Label getLabelVictoire() {
        return labelVictoire;
    }
}
