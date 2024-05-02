package vue;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import modele.Position;

import java.io.File;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class VboxRoot extends VBox implements ConstantesCanvas {
    private GraphicsContext graphicsContext2D;

    private Label labelNombreDePas;

    private static Canvas canvasCarte;

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
                        System.out.println(positionClique);
                        labelToString.setText(position.toString());

                        // Permet de colorier la case cliquée ? fillRect a comme parametre : abscisse, ordonne, longueur, largeur
                        graphicsContext2D.setFill(COULEUR_CLIQUE);
                        coloriee = true;
                        graphicsContext2D.fillRect(abscisse * CARRE, ordonne * CARRE, CARRE, CARRE);
                        this.deplacementAvecTimer(positionApprenti, positionClique);

                    }
                    if (!etest) {
                        labelToString.setText("Case cliquée invalide, veuillez réessayer");

                    }
                });




    }
    /**
     * Méthode de déplacement avec un Timer.
     * Elle déplace l'apprenti vers la position cible.
     * !! ENLEVER LA COULEUR QU'IL MET A CHAQUE DEPLACEMENT !!
     *
     * @param positionApprenti Position actuelle de l'apprenti.
     * @param positionCliquee  Position cible du déplacement.
     */
    private void deplacementAvecTimer(Position positionApprenti, Position positionCliquee) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                graphicsContext2D.clearRect(positionApprenti.getAbscisse() * CARRE + 2, positionApprenti.getOrdonnee() * CARRE + 2, CARRE - 4, CARRE - 4); // dimensiosn

                positionApprenti.deplacementUneCase(positionCliquee);

                graphicsContext2D.setFill(COULEUR_APPRENTI);
                graphicsContext2D.fillOval(positionApprenti.getAbscisse() * CARRE + CARRE / 8, positionApprenti.getOrdonnee() * CARRE + CARRE / 4, LARGEUR_OVALE, HAUTEUR_OVALE);

                Platform.runLater(() -> labelNombreDePas.setText("Nombre de pas : " + Position.getNombreDePas()));

                if (positionApprenti.equals(positionCliquee)) {
                    timer.cancel();

                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);
    }

    /*
    Read file from scanner
    Lis tout simplement le document, parfait, juste a régler le probleme des deux cases l'une a cote de l'autre
     */
    public static class ReadFromFileUsingScanner {
        public ReadFromFileUsingScanner() {
            try {
                // Chemin vers le fichier
                File position = new File("C:\\Users\\TEMP.DINFO\\Downloads\\SAE-ORDONATTEUR-main\\SAE-ORDONATTEUR-main\\position.txt");

                // Création d'un scanner pour lire le fichier
                Scanner sc = new Scanner(position);

                // Obtention du GraphicsContext à partir du Canvas
                GraphicsContext graphicsContext2D = canvasCarte.getGraphicsContext2D();

                // Lecture du fichier ligne par ligne
                while (sc.hasNextLine()) {
                    // Lecture de la ligne courante
                    String line = sc.nextLine();

                    // Séparation des valeurs en utilisant la virgule comme séparateur
                    String[] values = line.split(", ");

                    // Extraction des valeurs x et y
                    int x = Integer.parseInt(values[0]);
                    int y = Integer.parseInt(values[1]);

                    String couleurTemple = values[2];
                    String couleurCristal = values[3];
                    System.out.println(couleurTemple);
                    System.out.println(couleurCristal);



                    // Remplissage des cases du canvas avec les couleurs
                    graphicsContext2D.setFill(Paint.valueOf(couleurTemple));
                    graphicsContext2D.fillRect(x * CARRE, y * CARRE, CARRE, CARRE);
                    graphicsContext2D.setFill(Paint.valueOf(couleurCristal));
                    graphicsContext2D.fillRect((x + 1) * CARRE, y * CARRE, CARRE, CARRE);
                }

                // Fermeture du scannerscr
                sc.close();
            } catch (Exception e) {
                // Gestion des exceptions
                e.printStackTrace();
            }



        };
    }





}
