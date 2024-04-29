package vue;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import modele.Position;

public class VboxRoot extends VBox implements ConstantesCanvas {
    private GraphicsContext graphicsContext2D;

    private Label labelNombreDePas;

    private Canvas canvasCarte;

    private Position positionApprenti;



    public VboxRoot(){

        // Position initiale du joueur
        positionApprenti = new Position(LARGEUR_CANVAS/(CARRE*2),HAUTEUR_CANVAS/(CARRE*2) );

        //l'étiquette qui affiche le nombre de pas
        labelNombreDePas = new Label ("Nombre de pas : 0");

        //le canvas et son contexte graphique
        canvasCarte = new Canvas();
        canvasCarte.setWidth(LARGEUR_CANVAS);
        canvasCarte.setHeight(HAUTEUR_CANVAS);
        graphicsContext2D = canvasCarte.getGraphicsContext2D();

        //les carrés
        graphicsContext2D.setStroke(COULEUR_GRILLE);
        for(int i = 0; i < LARGEUR_CANVAS; i += CARRE){
            for(int j = 0; j < HAUTEUR_CANVAS; j += CARRE){
                graphicsContext2D.strokeRect(i,j, CARRE, CARRE);
            }
        }

        // ajout des composants graphiques à la racine (this)
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(10));
        this.getChildren().add(labelNombreDePas);
        VBox.setMargin(labelNombreDePas, new Insets(5));

        // les numéros de colonne
        int numCol = 1;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < LARGEUR_CANVAS; i += CARRE){
            graphicsContext2D.fillText(Integer.toString(numCol),i+CARRE/3, CARRE/2);
            numCol++;
        }

        //les numéros de ligne
        int numLigne = 1;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for(int i = CARRE; i < HAUTEUR_CANVAS; i +=CARRE){
            graphicsContext2D.fillText(Integer.toString(numLigne),CARRE/3, i+CARRE/2);
            numLigne++;
        }
        //position apprenti et affichage
        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE + 1,
                positionApprenti.getOrdonnee()*CARRE + 1,
                LARGEUR_OVALE,HAUTEUR_OVALE);


        // Position pour le toString
        Position position = new Position(10,10);
        Label labelToString = new Label(position.toString());
        // Affichage du toString
        this.getChildren().add(labelToString);


        // Récupère et affiche la position de la case cliquée avec la souris
        canvasCarte.setOnMouseClicked(event -> {
            // boolean qui permet de verifier que la case n'est pas une case chiffrée
            boolean etest = false;
            int abscisse = (int) event.getX() / CARRE;
            int ordonne = (int) event.getY() / CARRE;
            // test des coordonnées de la case cliquée
            if ( abscisse != 0 ){
                if ( ordonne != 0){
                    etest = true;
                }
            }
            // si boolean vrai faire le coloriage
            if (etest) {
                Position positionClique = new Position(abscisse, ordonne);
                System.out.println(positionClique);
                labelToString.setText(position.toString());

                // Permet de colorier la case cliquée ? fillRect a comme parametre : abscisse, ordonne, longueur, largeur
                graphicsContext2D.setFill(COULEUR_CLIQUE);
                graphicsContext2D.fillRect(abscisse * CARRE, ordonne * CARRE, CARRE, CARRE);
            }
            if (!etest) {
                labelToString.setText( "Case cliquée invalide, veuillez réessayer");

            }
        });


    }




}
