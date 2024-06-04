package vue;

import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {
    private VBoxGauche VBoxGauche;
    private VBoxDroite VBoxDroite;

    public HBoxRoot(){
        VBoxGauche = new VBoxGauche();
        VBoxDroite = new VBoxDroite();

        this.getChildren().add(VBoxGauche);
        this.getChildren().add(VBoxDroite);



    }
}
