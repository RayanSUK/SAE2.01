package vue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import modele.*;

import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class VBoxCanva extends VBox implements ConstantesCanvas {
    private GraphicsContext graphicsContext2D;

    private Label labelNombreDePas;

    private static Canvas canvasCarte;

    private  List<Temple> temples;

    private Collection<Cristal> cristaux;

    private Position positionApprenti ;

    private Label labelToString;


    public VBoxCanva(){



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
        int numCol = -15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for (int i = CARRE; i < LARGEUR_CANVAS; i += CARRE){
            graphicsContext2D.fillText(Integer.toString(numCol),i+CARRE/3, CARRE/2);
            numCol++;
        }

        //les numéros de ligne
        int numLigne = -15;
        graphicsContext2D.setFill(COULEUR_GRILLE);
        for(int i = CARRE; i < HAUTEUR_CANVAS; i +=CARRE){
            graphicsContext2D.fillText(Integer.toString(numLigne),CARRE/3, i+CARRE/2);
            numLigne++;
        }

        //position apprenti et affichage
        positionApprenti = VBoxRoot.getApprenti().getPosition();
        graphicsContext2D.setFill(COULEUR_APPRENTI);
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE +0.5,
                positionApprenti.getOrdonnee()*CARRE + 0.5,
                LARGEUR_OVALE,HAUTEUR_OVALE);

//
        //// Position pour le toString
        //Position position = new Position(10,10);
        //labelToString = new Label(position.toString());
        //// Affichage du toString
        //this.getChildren().add(labelToString);





        // Récupère et affiche la position de la case cliquée avec la souris
        canvasCarte.setOnMouseClicked(event -> {
                    // boolean qui permet de voir si une case est déjà coloriée :
                    boolean coloriee = false;
                    // boolean qui permet de verifier que la case n'est pas une case chiffrée
                    boolean etest = false;
                    int abscisse = (int) event.getX() / CARRE;
                    int ordonne = (int) event.getY() / CARRE;
                    // test des coordonnées de la case cliquée
                    if (abscisse != 0) {
                        if (ordonne != 0) {
                            etest = true;
                        }
                    }
                    // si boolean vrai faire le coloriage
                    if (etest) {
                        Position positionClique = new Position(abscisse, ordonne);
                       //for (Temple temple : temples){
                       //    if ( positionClique.equals(temple.getPositionTemple())) {

                       //    }
                       //}
                        System.out.println(positionClique);
                        //labelToString.setText(position.toString());

                        // Permet de colorier la case cliquée ? fillRect a comme parametre : abscisse, ordonne, longueur, largeur
                        graphicsContext2D.setFill(COULEUR_CLIQUE);
                        coloriee = true;
                        graphicsContext2D.fillRect(abscisse * CARRE +1, ordonne * CARRE+1, CARRE-2, CARRE-2);

                        this.deplacementAvecTimer(positionApprenti, positionClique);

                    }
                    if (!etest) {
                        labelToString.setText("Case cliquée invalide, veuillez réessayer");

                    }
                });
        Button bouton = new Button("Test effacer");
        bouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                effacerCanva();

            }

        });
        this.getChildren().add(bouton);

    }

    public List<Temple> getTemples() {
        return temples;
    }

    public void setTemples(List<Temple> parTemples) {
        this.temples = parTemples;
        // affichage des temples
        for (Temple temple : temples) {
            Position positionTemple = temple.getPositionTemple();
            graphicsContext2D.setFill(COULEURS_TEMPLES[temple.getCouleurTemple()-1]);
            graphicsContext2D.fillRect(positionTemple.getAbscisse()*CARRE+1,positionTemple.getOrdonnee()*CARRE+1,CARRE-1,CARRE-1);
        }
    }

    public void setCristaux(List<Cristal> parCristaux) {
        this.cristaux = parCristaux ;
        // affichage des temples
        for (Cristal cristal : cristaux) {
            Position positionCristal = cristal.getPositionCristal();
            graphicsContext2D.setFill(COULEURS_TEMPLES[cristal.getCouleurCristal()-1]);
            graphicsContext2D.fillOval(positionCristal.getAbscisse()*CARRE+1,positionCristal.getOrdonnee()*CARRE+1,CARRE-1,CARRE-1);
        }
    }

    /**
     * Méthode de déplacement avec un Timer.
     * Elle déplace l'apprenti vers la position cible.
     * !! ENLEVER LA COULEUR QU'IL MET A CHAQUE DEPLACEMENT !!
     *
     * @param positionApprenti Position actuelle de l'apprenti.
     * @param positionsCibles  Position cible du déplacement.
     */
    private void deplacementAvecTimer(Position positionApprenti, Position positionsCibles) {
        Timer timer = new Timer();
        final int [] tabIndice = {0}; 
        
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //Position positionCible = positionsCibles.get(tabIndice[0]);

                graphicsContext2D.clearRect(positionApprenti.getAbscisse() * CARRE + 1, positionApprenti.getOrdonnee() * CARRE + 1, CARRE - 2, CARRE - 2); // dimensiosn
                for (Temple temple : temples) {
                    Position positionTemple = temple.getPositionTemple();
                    if (positionApprenti.equals(positionTemple)) {
                        graphicsContext2D.setFill(COULEURS_TEMPLES[temple.getCouleurTemple() - 1]);
                        graphicsContext2D.fillRect(positionTemple.getAbscisse() * CARRE + 1, positionTemple.getOrdonnee() * CARRE + 1, CARRE - 1, CARRE - 1);
                    }
                }
                positionApprenti.deplacementUneCase(positionsCibles);

                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillOval(positionApprenti.getAbscisse() * CARRE , positionApprenti.getOrdonnee() * CARRE , LARGEUR_OVALE, HAUTEUR_OVALE);

                Platform.runLater(() -> labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas()));

                if (positionApprenti.equals(positionsCibles)) {
                    timer.cancel();

                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);
    }

    public void effacerCanva(){
        graphicsContext2D.setFill(Paint.valueOf("white"));
        for ( int i = 20; i< LARGEUR_CANVAS; i+= CARRE){
            for ( int j = 20; j < HAUTEUR_CANVAS; j+= CARRE) {
                graphicsContext2D.fillRect(i +1 , j+1, CARRE - 2, CARRE - 2);
            }
        }
        graphicsContext2D.setFill(COULEUR_APPRENTI);
        positionApprenti.setPosition(LARGEUR_CANVAS/(CARRE*2), HAUTEUR_CANVAS/(CARRE*2));
        graphicsContext2D.fillOval(positionApprenti.getAbscisse()*CARRE +0.5,
                positionApprenti.getOrdonnee()*CARRE + 0.5,
                LARGEUR_OVALE,HAUTEUR_OVALE);

    }






}

/*
-methode d'échange de cristal
-ne pas effacer quand tu passes sur un temple ou un cristal!!! -> à moitié réglé,
 on doit avoir le choix de prendre ou non le cristal
 */
