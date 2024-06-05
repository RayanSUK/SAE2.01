package vue;

import javafx.scene.layout.HBox;

/**
 * La classe HBoxRoot hérite de HBox et organise les 2 VBox (VBoxGauche et VBoxDroite) horizontalement.
 */
public class HBoxRoot extends HBox {
    private VBoxGauche VBoxGauche;
    private VBoxDroite VBoxDroite;

    /**
     * Constructeur de la classe. Instancie les deux boites et les ajoute à la HBox.
     */
    public HBoxRoot(){
        VBoxGauche = new VBoxGauche();
        VBoxDroite = new VBoxDroite();

        this.getChildren().add(VBoxGauche);
        this.getChildren().add(VBoxDroite);



    }
}
