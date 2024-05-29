package vue;

import controleur.Controleur;
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

    private List<Cristal> cristaux;

    private ApprentiOrdonnateur apprentiOrdonnateur;

    private Position positionApprenti ;

    private Label labelToString;

    private GraphicsContext graphicsContextCristal;

    private Canvas canvaCristal;

    private Button boutonEchange;

    private Controleur controleur;


    public VBoxCanva(){
        controleur = new Controleur();
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

        boutonEchange = new Button("Echanger cristaux");
        boutonEchange.setOnAction(controleur);
        this.getChildren().add(boutonEchange);


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
        apprentiOrdonnateur = VBoxRoot.getApprenti();
        positionApprenti = apprentiOrdonnateur.getPosition();
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



        Label labelCristal = new Label("Cristal porté : ");
        this.getChildren().add(labelCristal);
        VBox.setMargin(labelCristal,new Insets(10));

        // Création d'un canva pour afficher une case qui montre le cristal porté par le joueur
        canvaCristal = new Canvas();
        canvaCristal.setHeight(80);
        canvaCristal.setWidth(80);
        graphicsContextCristal = canvaCristal.getGraphicsContext2D();
        graphicsContextCristal.setStroke(COULEUR_GRILLE);
        graphicsContextCristal.strokeRect( 0 , 0 , CARRE*2- 5, CARRE*2-5);




        this.getChildren().add(canvaCristal);
        VBox.setMargin(canvaCristal, new Insets(5));



    }



    public List<Temple> getTemples() {
        return temples;
    }


    // Méthode pour placer les temples
    public void setTemples(List<Temple> parTemples) {
        this.temples = parTemples;
        // affichage des temples
        for (Temple temple : temples) {
            Position positionTemple = temple.getPositionTemple();
            graphicsContext2D.setFill(COULEURS_TEMPLES[temple.getCouleurTemple()-1]);
            graphicsContext2D.fillRect(positionTemple.getAbscisse()*CARRE+1,positionTemple.getOrdonnee()*CARRE+1,CARRE-1,CARRE-1);
        }
    }


    /**
     * Méthode qui prend en parametres une liste de cristal
     * pour placer les cristaux graphiquement
     * @param parCristaux Liste de cristaux
     */

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

                if (! (temples == null)) {
                    // Test position temple
                    for (Temple temple : temples) {
                        Position positionTemple = temple.getPositionTemple();
                        // Si l'apprenti est sur un temple on re-colorie apres son passage
                        if (positionApprenti.equals(positionTemple)) {
                            graphicsContext2D.setFill(COULEURS_TEMPLES[temple.getCouleurTemple() - 1]);
                            graphicsContext2D.fillRect(positionTemple.getAbscisse() * CARRE + 1, positionTemple.getOrdonnee() * CARRE + 1, CARRE - 1, CARRE - 1);
                        }
                    }
                    // Test position Cristal
                    for (Cristal cristal : cristaux) {
                        Position positionCristal = cristal.getPositionCristal();
                        if (!(cristal.equals(apprentiOrdonnateur.getCristalPorte()))) {
                            // Si l'apprenti est sur un temple avec un cristal on re-colorie apres son passage
                            if (positionApprenti.equals(positionCristal)) {
                                graphicsContext2D.setFill(COULEURS_TEMPLES[cristal.getCouleurCristal() - 1]);
                                graphicsContext2D.fillOval(positionCristal.getAbscisse() * CARRE + 1, positionCristal.getOrdonnee() * CARRE + 1, CARRE - 1, CARRE - 1);
                            }
                        }

                    }
                }

                positionApprenti.deplacementUneCase(positionsCibles);


                // Actualise la position du cristal en meme temps que l'apprenti
                if (!(apprentiOrdonnateur.getCristalPorte()== null)) {
                    apprentiOrdonnateur.getCristalPorte().getPositionCristal().deplacementCristal(positionsCibles);
                }

                // Dessine l'apprenti
                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillOval(positionApprenti.getAbscisse() * CARRE , positionApprenti.getOrdonnee() * CARRE , LARGEUR_OVALE, HAUTEUR_OVALE);

                Platform.runLater(() -> labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas()));

                if (positionApprenti.equals(positionsCibles)) {
                    timer.cancel();
                    timer.cancel();
                    if (apprentiOrdonnateur.getCristalPorte()!=null) {
                        System.out.println("Le cristal porté est :" + apprentiOrdonnateur.getCristalPorte().toString());
                    }

                }
                apprentiOrdonnateur.setEchangeDispo();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);
    }

    public void echangeGraphique(){
        // si l'apprenti a un cristal
        if (!(apprentiOrdonnateur.getCristalPorte() == null)){
            // Le cristal porté avant échange
            Cristal cristaltemp = apprentiOrdonnateur.getCristalPorte();
            //Parcours de la liste des cristaux
            for (Cristal cristal : cristaux) {
                // si la position de l'apprenti est égale à la position du cristal de la boucle
                if (apprentiOrdonnateur.getPosition().equals(cristal.getPositionCristal())) {
                    //System.out.println("TEST 1");
                    // si le cristal testé n'est pas le cristal porté
                    if ( !(cristaltemp.equals(cristal))) {
                        System.out.println(apprentiOrdonnateur.isEchangeDispo());
                        apprentiOrdonnateur.echangeCristaux(cristal);
                        System.out.println(apprentiOrdonnateur.isEchangeDispo());
                        Cristal cristalPorte = apprentiOrdonnateur.getCristalPorte();

                        //graphicsContext2D.setFill(COULEURS_TEMPLES[cristal.getCouleurCristal()]);
                        //graphicsContext2D.fillOval(cristalPorte.getPositionCristal().getAbscisse() * CARRE ,
                        //        cristalPorte.getPositionCristal().getOrdonnee() * CARRE,
                        //        LARGEUR_OVALE,
                        //        HAUTEUR_OVALE);

                        graphicsContextCristal.setFill(COULEURS_TEMPLES[cristalPorte.getCouleurCristal()-1]);
                        graphicsContextCristal.fillOval(3, 3, 30, 30);
                        System.out.println(" Cristal porté" +cristalPorte.toString());
                    }
                }
            }
            //apprentiOrdonnateur.lacherCristal();
        }

        // C BON
        //si l'apprenti n'a pas de cristal
        if (apprentiOrdonnateur.getCristalPorte() == null){
            // Parcours de la liste de cristaux
            for (Cristal cristalEchange: cristaux) {
                // si la position de l'apprenti est égale à celle du cristal de la boucle
                if (apprentiOrdonnateur.getPosition().equals(cristalEchange.getPositionCristal())) {
                    // On récupère le cristal
                    apprentiOrdonnateur.echangeCristaux(cristalEchange);
                    graphicsContextCristal.setFill(COULEURS_TEMPLES[apprentiOrdonnateur.getCristalPorte().getCouleurCristal()-1]);
                    graphicsContextCristal.fillOval( 3, 3, 30, 30);
                }

            }
        }
            //graphicsContextCristal.fillOval();
    }

    /** Méthode qui ne prend rien en parametre et ne renvoie rien,
     * elle redessine l'intérieur des cases de la grille
     */
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


    public String cristauxToString(){
        String cristales = "";
        for (Cristal cristal : cristaux){
            cristales += cristal.toString();
        }
        return cristales;
    }
}


/*
-methode d'échange de cristal
-ne pas effacer quand tu passes sur un temple ou un cristal!!! -> à moitié réglé,
 on doit avoir le choix de prendre ou non le cristal
 */