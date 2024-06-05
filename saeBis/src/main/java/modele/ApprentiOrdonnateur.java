package modele;

import vue.VBoxCanva;
import vue.VBoxGauche;

import java.util.List;

/**
 *  La classe ApprentiOrdonnateur représente le joueur qui se déplace sur une grille.
 *  Il échange des cristaux et interagit avec des temples.
 *  Elle implémente l'interface ConstantesCanvas pour accéder aux dimensions et couleurs définies.
 */
public class ApprentiOrdonnateur implements ConstantesCanvas {

    /**
     * Position du joueur
     */
    private Position positionApprenti;

    /**
     * Liste contenant les temples
     */
    private List<Temple> temples;

    /**
     * Liste contenant les cristaux
     */
    private List<Cristal> cristaux;
    /**
     * Indice du cristal porté par l'apprenti (-1 s'il ne porte pas de cristal)
     */
    private int cristalPorte =-1;

    /**
     * Indique si un échange de cristaux est disponible.
     */
    private boolean echangeDispo = true;

    /**
     * Constructeur de la classe
     * Instancie la position du joueur dans le canvas
     */

    public ApprentiOrdonnateur(){
 // Position initiale du joueur
        positionApprenti = new Position(LARGEUR_CANVAS/(CARRE* 2),HAUTEUR_CANVAS/(CARRE*2) );
    }

    /**
     * La méthode échangeCristaux permet à l'apprenti d'échanger des cristaux s'il se trouve sur la même position
     * qu'un cristal et si un échange est disponible.
     * Il reçoit en paramètre le cristal à échanger
     * @param cristal , le cristal à échanger
     */
    public void echangeCristaux(Cristal cristal) {
        if (echangeDispo) {
            if (cristal.getPositionCristal().equals(positionApprenti)) {
                if (cristalPorte != -1) {
                System.out.println("Vous avez déposé le cristal : " + getCristalPorte().toString() );
                }
                cristalPorte = cristaux.indexOf(cristal);
                echangeDispo = false;
                System.out.println("Vous avez récupéré le cristal : " + getCristalPorte().toString());
            }

        }
    }

    /**
     * La méthode lâcherCristal permet de déposer le cristal porté par l'apprentie à sa position
     */
    public void lacherCristal() {
        if (cristalPorte == -1){
            System.out.println("Vous n'avez aucun cristal à déposer.");
            return;
        }
        for (Cristal cristal : cristaux ) {
            // si le joueur est sur un cristal et que celui-là n'est pas le cristal porter
            if (cristal.getPositionCristal().equals(positionApprenti) && !(cristal.equals(cristaux.get(cristalPorte)))) {
                System.out.println("Vous ne pouvez déposer les cristaux que sur les temples.");
                return;
            }
        }
        for (Temple temple : temples){
            if (positionApprenti.equals(temple.getPositionTemple())){
                System.out.println("Vous avez déposé le cristal : " + getCristalPorte().toString());
                cristalPorte = -1;
            }

        }
        victoire();
        VBoxGauche.getCanvas().getBoiteBouton().conditionVictoire();
    }

    /**
     * La méthode templeAssocie prend en paramètre un cristal et retourne l'indice du temple de la même couleur
     * dans la liste de temples.
     * @param parCristal le cristal pour lequel on recherche son temple
     * @return Indice temple associé
     */
    public int templeAssocie(Cristal parCristal) {
        for (int i = 0; i < cristaux.size(); i++) {
            if (temples.get(i).getCouleurTemple() == parCristal.getCouleurCristal()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * La méthode cristalPlusProche ne prend rien en paramètres et renvoie
     *  l'indice du cristal le plus proche de l'apprenti ordonnateur.
     * @return indice cristal le plus proche
     */
    public int cristalPlusProche() {
        if (victoire()) return -1;
        int distanceMin = Integer.MAX_VALUE;
        int indiceCristal = -1;

        for (int i = 0; i < cristaux.size(); i++) {
            if (cristaux.get(i).getPositionCristal().equals(temples.get(templeAssocie(cristaux.get(i))).getPositionTemple())) {
                continue;
            }
            Position positionCristal = cristaux.get(i).getPositionCristal();
            int distanceX = Math.abs(positionCristal.getAbscisse() - positionApprenti.getAbscisse());
            int distanceY = Math.abs(positionCristal.getOrdonnee() - positionApprenti.getOrdonnee());
            int distance = distanceX + distanceY; // Calcul de la distance manhattan
            if (distance < distanceMin) {
                distanceMin = distance;
                indiceCristal = i;
            }
        }
        System.out.println("Le cristal le plus proche est le cristal : " + cristaux.get(indiceCristal).toString());
        return indiceCristal;
    }

    /**
     * La méthode victoire vérifie si tous les cristaux sont bien mis dans leur temple correspondant
     * @return True si c'est le cas, False sinon
     */
    public boolean victoire(){
        int compteur = 0;
        for (Temple temple : temples){
            for (Cristal cristal :  cristaux){
                if(cristal.getCouleurCristal() == temple.getCouleurTemple() && cristal.getPositionCristal().equals(temple.getPositionTemple())){
                    compteur +=1;
                }
            }

        }
        if(compteur == temples.size()){
            System.out.println("Vous avez gagné !");
            return true;
        }
        return false;
    }

    public void recommencerPartie() {
        cristaux = null;
        temples = null;
        positionApprenti.resetNombreDePas();
    }


    // ACCESSEURS

    /**
     * Accesseur au champ temple
     * @return temples, les temples
     */
    public List<Temple> getTemples() {
        return temples;
    }

    /**
     * Définit la liste de temples.
     * @param temples la nouvelle liste de temples
     */
    public void setTemples(List<Temple> temples) {
        this.temples = temples;
    }

    /**
     * Accesseur au champ
     * @return positionApprenti, la position du joueur
     */
    public Position getPosition() {
        return positionApprenti;
    }

    /**
     * Définit la position de l'apprenti.
     * @param positionApprenti la nouvelle position de l'apprenti
     */
    public void setPositionApprenti(Position positionApprenti) {
        this.positionApprenti = positionApprenti;
    }

    /**
     * Accesseur à la liste de cristaux
     * @return cristaux, la liste de cristaux
     */
    public List<Cristal> getCristaux() {
        return cristaux;
    }

    /**
     * Définit la liste de cristaux.
     * @param cristaux la nouvelle liste de cristaux
     */
    public void setCristaux(List<Cristal> cristaux) {
        this.cristaux = cristaux;
    }

    /**
     * Accesseur au cristal que l'apprenti porte actuellement
     * @return le cristal porté ou null s'il n'en a pas
     */
    public Cristal getCristalPorte() {
        if ( cristalPorte == -1) {
            return null;
        }
        return cristaux.get(cristalPorte) ;
    }

    /**
     * Indique l'échange de cristaux est possible.
     * @return true si un échange est disponible, sinon false
     */
    public boolean isEchangeDispo() {
        return echangeDispo;
    }

    /**
     * Réinitialise l'échange de cristaux possible
     */
    public void setEchangeDispo() {
        echangeDispo = true;
    }

    public void resetCristalPorte(){
        cristalPorte = -1;
    }
}
