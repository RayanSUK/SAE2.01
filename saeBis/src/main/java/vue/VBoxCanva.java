package vue;

import controleur.Controleur;
import javafx.application.Platform;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import modele.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * La classe VboxCanva implémente tous les composants graphiques

 */
public class VBoxCanva extends VBox implements ConstantesCanvas {
    /**
     * Le champ graphicsContext2D représente le grillage et les forme de notre jeu
     */
    private GraphicsContext graphicsContext2D;

    /**
     * Le champ labelNombreDePas affiche le nombre de pas du joueur
     */

    private Label labelNombreDePas;
    /**
     * Le champ canvasCarte est une fenêtre dans lequel s'affiche les éléments graphiques
     */

    private static Canvas canvasCarte;

    /**
     * Une liste qui contient tous les temples du jeu
     */

    private  List<Temple> temples;

    /**
     * Une liste qui contient tous les cristaux
     */

    private List<Cristal> cristaux;

    /**
     * Représente le joueur
     */

    private ApprentiOrdonnateur apprentiOrdonnateur;

    /**
     * Représente la position du joueur
     */

    private Position positionApprenti ;

    /**
     * Nouveau graphique qui affiche le cristal du joueur
     */
    private GraphicsContext graphicsContextCristal;

    /**
     * Fenêtre dans laquelle s'affiche les éléments graphiques du cristal
     */

    private Canvas canvaCristal;

    /**
     * Bouton qui permet d'échanger les cristaux
     */

    private Button boutonEchange;

    /**
     * Bouton qui permet de déposer un cristal
     */

    private Button boutonDepose;

    /**
     * Label lorsque le joueur gagne
     */
    private Label labelVictoire;
    /**
     * Bouton pour le tri par selection
     */
    private Button triInsertion;

    private Timer timer;

    /**
     * Architecture MVC
     */

    private Controleur controleur;



    /**
     * Constructeur de la classe. Permet d'instancier les composants graphiques.
     * Détermine la position du joueur.
     * Colorier les cases cliquer
     */
    public VBoxCanva(){

        //instancie le controleur
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

        //le bouton pour échanger de cristal
        boutonEchange = new Button("Echanger cristaux");
        boutonEchange.setOnAction(controleur);
        this.getChildren().add(boutonEchange);

        // le bouton pour déposer le cristal
        boutonDepose = new Button("Déposer cristal");
        boutonDepose.setOnAction(controleur);
        this.getChildren().add(boutonDepose);

        //Le bouton de victoire
        labelVictoire = new Label("Victoire");
        labelVictoire.setVisible(false);
        this.getChildren().add(labelVictoire);

        //Le bouton de l'algorithme de tri par Selection
        triInsertion = new Button("tri par selection");
        triInsertion.setOnAction(controleur);
        this.getChildren().add(triInsertion);

        // ajout des composants graphiques à la racine (this)
        this.getChildren().add(canvasCarte);
        VBox.setMargin(canvasCarte, new Insets(10));
        this.getChildren().add(labelNombreDePas);

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

                        // Permet de colorier la case cliquée
                        graphicsContext2D.setFill(COULEUR_CLIQUE);
                        coloriee = true;

                        // fillRect a comme parametre : abscisse, ordonne, longueur, largeur
                        graphicsContext2D.fillRect(abscisse * CARRE +1, ordonne * CARRE+1, CARRE-2, CARRE-2);

                        this.deplacementAvecTimer(positionApprenti, positionClique);

                    }
                    if (!etest) {
                        System.out.println("Case cliquée invalide, veuillez réessayer");

                    }
                });


        //afficher le cristal
        Label labelCristal = new Label("Cristal porté : ");
        this.getChildren().add(labelCristal);


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


    /**
     * Accesseur pour le champ temple
     * @return temples, les temples du jeu
     */
    public List<Temple> getTemples() {
        return temples;
    }

    /**
     * La méthode setTemples reçoit en paramètre une liste de temples et les affiches dans le CanvaCarte
     * @param parTemples, une liste de temple
     */
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
     * Méthode qui prend en parametre une liste de cristal
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
     * Elle reçoit en paramètre la position du joueur et la position du clic
     * @param positionApprenti Position actuelle de l'apprenti.
     * @param positionsCibles  Position cible du déplacement.
     */
    private void deplacementAvecTimer(Position positionApprenti, Position positionsCibles) {
        if (positionApprenti.equals(positionsCibles)) {
            return;
        }
        //Création d'un timer
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

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

                //le joueur se déplace case par case

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
                    if (apprentiOrdonnateur.getCristalPorte()!=null) {
                        System.out.println("Le cristal porté est :" + apprentiOrdonnateur.getCristalPorte().toString());
                    }

                }
                apprentiOrdonnateur.setEchangeDispo();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 200);
    }


    /**
     * La méthode échange graphique permet d'afficher graphiquement la récupération d'un cristal
     */
    public void echangeGraphique() {
        // si l'apprenti a un cristal
        if (!(apprentiOrdonnateur.getCristalPorte() == null)) {
            // Le cristal porté avant échange
            Cristal cristaltemp = apprentiOrdonnateur.getCristalPorte();
            //Parcours de la liste des cristaux
            for (Cristal cristal : cristaux) {
                // si la position de l'apprenti est égale à la position du cristal de la boucle
                if (apprentiOrdonnateur.getPosition().equals(cristal.getPositionCristal())) {
                    //System.out.println("TEST 1");
                    // si le cristal testé n'est pas le cristal porté
                    if (!(cristaltemp.equals(cristal))) {
                        System.out.println(apprentiOrdonnateur.isEchangeDispo());
                        apprentiOrdonnateur.echangeCristaux(cristal);
                        System.out.println(apprentiOrdonnateur.isEchangeDispo());
                        Cristal cristalPorte = apprentiOrdonnateur.getCristalPorte();

                        graphicsContextCristal.setFill(COULEURS_TEMPLES[cristalPorte.getCouleurCristal() - 1]);
                        graphicsContextCristal.fillOval(3, 3, 30, 30);
                        System.out.println(" Cristal porté" + cristalPorte.toString());
                    }
                }
            }
        }
        //si l'apprenti n'a pas de cristal
        if (apprentiOrdonnateur.getCristalPorte() == null) {
            // Parcours de la liste de cristaux
            for (Cristal cristalEchange : cristaux) {
                // si la position de l'apprenti est égale à celle du cristal de la boucle
                if (apprentiOrdonnateur.getPosition().equals(cristalEchange.getPositionCristal())) {
                    // On récupère le cristal
                    apprentiOrdonnateur.echangeCristaux(cristalEchange);
                    graphicsContextCristal.setFill(COULEURS_TEMPLES[apprentiOrdonnateur.getCristalPorte().getCouleurCristal() - 1]);
                    graphicsContextCristal.fillOval(3, 3, 30, 30);
                }

            }
        }

    }


    /**
     * La méthode supprimerCristalPorter lâche le cristal puis le supprime graphiquement après vérification.
     */
    public void supprimerCristalPorter(){
        apprentiOrdonnateur.lacherCristal();
        if (cristaux.indexOf(apprentiOrdonnateur.getCristalPorte()) == -1) {
            graphicsContextCristal.setFill(Color.WHITE);
            graphicsContextCristal.fillOval(3, 3, 30, 30);
        }
        if(apprentiOrdonnateur.victoire()){
            labelVictoire.setVisible(true);

        }
    }

    public void conditionVictoire(){
        labelVictoire.setVisible(apprentiOrdonnateur.victoire());
    }

    /** Méthode qui ne prend rien en parametre et ne renvoie rien,
     * Elle redessine l'intérieur des cases de la grille lorsque qu'on change de scénarios.
     */
    public void effacerCanva(){

        //Redessiner en blanc
        graphicsContext2D.setFill(Color.WHITE);
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


    public void triSelection(Position positionApprenti) {
        System.out.println(cristaux.toString());
        // Parcours des cristaux par indice
        for (int i = 0; i < cristaux.size() - 1; i++) {
            // Initialisation du minimum à l'indice i
            int minIndex = i;
            for (int j = i + 1; j < cristaux.size(); j++) {
                if (apprentiOrdonnateur.templeAssocie(cristaux.get(j)) < apprentiOrdonnateur.templeAssocie(cristaux.get(minIndex))) {
                    minIndex = j;
                }
            }

            // Déplacement de l'apprenti vers le cristal à l'indice minIndex
            Position positionCristalMini = cristaux.get(minIndex).getPositionCristal();
            while (!positionApprenti.equals(positionCristalMini)) {
                positionApprenti.deplacementUneCase(positionCristalMini);
                if (apprentiOrdonnateur.getCristalPorte() != null) {
                    apprentiOrdonnateur.getCristalPorte().getPositionCristal().deplacementCristal(positionApprenti);
                }
                System.out.println("Apprenti : " + positionApprenti + " | Cristal Mini : " + positionCristalMini);
            }

            System.out.println(cristaux.toString());

            // Echange du cristal porté par l'apprenti avec le cristal à l'indice minIndex
            apprentiOrdonnateur.echangeCristaux(cristaux.get(minIndex));
            System.out.println("Echange avec cristal mini : " + apprentiOrdonnateur.getCristalPorte());

            // Déplacement de l'apprenti vers le cristal à l'indice i
            Position positionCristalI = cristaux.get(i).getPositionCristal();
            while (!positionApprenti.equals(positionCristalI)) {
                positionApprenti.deplacementUneCase(positionCristalI);
                if (apprentiOrdonnateur.getCristalPorte() != null) {
                    apprentiOrdonnateur.getCristalPorte().getPositionCristal().deplacementCristal(positionApprenti);
                }
                System.out.println("Apprenti : " + positionApprenti + " | Cristal I : " + positionCristalI);
            }
            System.out.println(cristaux.toString());

            // Echange du cristal porté par l'apprenti avec le cristal à l'indice i
            apprentiOrdonnateur.echangeCristaux(cristaux.get(i));
            System.out.println("Echange avec cristal I : " + apprentiOrdonnateur.getCristalPorte());

            System.out.println(cristaux.toString());

            //// Échange des cristaux dans la liste
            Cristal temp = cristaux.get(minIndex);
            cristaux.set(minIndex, cristaux.get(i));
            cristaux.set(i, temp);

            // Affichage pour débogage
            System.out.println("État actuel des cristaux : " + cristaux);
        }
        System.out.println();
        System.out.println("fin boucle");
        System.out.println("Cristal porté :" + apprentiOrdonnateur.getCristalPorte());
        System.out.println(cristaux.toString());

        // // Gestion du dernier cristal
        // Position positionCristalLast = temples.get(0).getPositionTemple();
        // while (!positionApprenti.equals(positionCristalLast)) {
        //     positionApprenti.deplacementUneCase(positionCristalLast);
        //     if (apprentiOrdonnateur.getCristalPorte() != null) {
        //         apprentiOrdonnateur.getCristalPorte().getPositionCristal().deplacementCristal(positionApprenti);
        //     }
        //     System.out.println("Apprenti : " + positionApprenti + " | Cristal Last : " + positionCristalLast);
        // }
        // System.out.println(cristaux.toString());
//
        // // Echange du cristal porté par l'apprenti avec le dernier cristal
        // apprentiOrdonnateur.lacherCristal();
        // System.out.println("Echange avec dernier cristal : " + apprentiOrdonnateur.getCristalPorte());
//
        // // Vérification finale de la liste des cristaux
        // System.out.println("Liste finale des cristaux : " + cristaux);
    }

    // TRI HEURISTIQUE GRAPHIQUE !!!!!

    public void deplacementGraphique(Position parPosition) {
        //Task<Void> task = new Task() {
        //@Override
        //protected Void call() throws Exception {
        //code
        while (!(positionApprenti.equals(parPosition))) {
            // Pause pour rendre le déplacement visible

            graphicsContext2D.clearRect(positionApprenti.getAbscisse() * CARRE + 1, positionApprenti.getOrdonnee() * CARRE + 1, CARRE - 2, CARRE - 2);

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
                        // Si l'apprenti est sur un temple avec un cristal, on re-colorie apres son passage
                        if (positionApprenti.equals(positionCristal)) {
                            graphicsContext2D.setFill(COULEURS_TEMPLES[cristal.getCouleurCristal() - 1]);
                            graphicsContext2D.fillOval(positionCristal.getAbscisse() * CARRE + 1, positionCristal.getOrdonnee() * CARRE + 1, CARRE - 1, CARRE - 1);
                        }
                    }
                }
            }


            positionApprenti.deplacementUneCase(parPosition);

            // Pause pour rendre le déplacement visible

            // Actualise la position du cristal en meme temps que l'apprenti
            if (!(apprentiOrdonnateur.getCristalPorte()== null)) {
                apprentiOrdonnateur.getCristalPorte().getPositionCristal().deplacementCristal(parPosition);
            }


            //// Pause pour rendre le déplacement visible
            //try {
            //    Thread.sleep(80); // Adjust the sleep duration as needed
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            // Dessine l'apprenti
            graphicsContext2D.setFill(COULEUR_APPRENTI);
            graphicsContext2D.fillOval(positionApprenti.getAbscisse() * CARRE , positionApprenti.getOrdonnee() * CARRE , LARGEUR_OVALE, HAUTEUR_OVALE);
            Platform.runLater(() -> labelNombreDePas.setText("Nombre de pas : " + positionApprenti.getNombreDePas()));

            //// Pause pour rendre le déplacement visible
            //try {
            //    Thread.sleep(80); // Adjust the sleep duration as needed
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        }

        //return null;
        //}
        //};
        //Thread thread = new Thread(task);
        //thread.setDaemon(true);
        //thread.start();
    }

    public void triHeuristiqueAvecAffichage() throws InterruptedException {
        Position positionApprenti = apprentiOrdonnateur.getPosition();
        // Parcours des temples et cristaux.
        for (int i = 0; i < temples.size(); i++) {
            if ( apprentiOrdonnateur.cristalPlusProche() == -1){ return; }
            Cristal cristalProche = cristaux.get(apprentiOrdonnateur.cristalPlusProche());
            // Déplacement vers le cristal le plus proche.
            //while (!(positionApprenti.equals(cristalProche.getPositionCristal()))) {

            deplacementAvecTimer(positionApprenti, cristalProche.getPositionCristal());
            // Pause pour rendre le déplacement visible
            //}
            // Echange avec le cristal.
            System.out.println(positionApprenti.toString());
            System.out.println(cristalProche.toString());
            try {
                Thread.sleep(2000); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(positionApprenti.toString());
            apprentiOrdonnateur.setEchangeDispo();
            apprentiOrdonnateur.echangeCristaux(cristalProche);
            System.out.println(apprentiOrdonnateur.getCristalPorte().toString());
            // Calcul du temple associé
            Temple templeAssocie = temples.get(apprentiOrdonnateur.templeAssocie(apprentiOrdonnateur.getCristalPorte()));
            //Déplacement vers le temple associé.
            //while (!(positionApprenti.equals(templeAssocie.getPositionTemple()))) {

            deplacementAvecTimer(positionApprenti, templeAssocie.getPositionTemple());

            try {
                Thread.sleep(2000); // Adjust the sleep duration as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (apprentiOrdonnateur.getCristalPorte() != null) {

                apprentiOrdonnateur.getCristalPorte().getPositionCristal().deplacementUneCase(templeAssocie.getPositionTemple());
            }
            //}
            // Si le cristal n'est pas le dernier, l'apprenti ordonnateur échange les cristaux.
            if ( i != temples.size()-1) {
                apprentiOrdonnateur.echangeCristaux(cristaux.get(i));
            }

            // Si le cristal est le dernier, on le lâche.
            apprentiOrdonnateur.lacherCristal();
            System.out.println(positionApprenti.getNombreDePas());
        }
        apprentiOrdonnateur.victoire();
        conditionVictoire();
    }


}


