package modele;

import java.util.Collection;
import java.util.List;
@SuppressWarnings("ResultOfMethodCallIgnored")

/**
 * La classe ApprentiOrdonnateur représente le joueur qui se déplace sur une grille.
 * Il échange des cristaux et interagit avec des temples.
 * Elle implémente l'interface ConstantesCanvas pour accéder aux dimensions et couleurs définies.
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
     * La méthode echangeCristaux permet à l'apprenti d'échanger des cristaux s'il se trouve sur la même position
     * qu'un cristal et si un échange est disponible.
     * Il reçoit en paramètre le cristal à echanger
     * @param cristal , le cristal à échanger
     */
    public void echangeCristaux(Cristal cristal) {
        if (echangeDispo) {
            if (cristal.getPositionCristal().equals(positionApprenti)) {
                cristalPorte = cristaux.indexOf(cristal);
                echangeDispo = false;
            }
        }
    }

    /**
     * La méthode lacherCristal permet de déposer le cristal porté par l'apprentie à sa position
     */
    public void lacherCristal() {
        for (Cristal cristal : cristaux ) {
            if (cristal.getPositionCristal().equals(positionApprenti)) {
                return;
            }
            cristalPorte = -1;
        }
    }

    /**
     * Accesseur au champ temple
     * @return temples, les temples
     */
    public Collection<Temple> getTemples() {
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
}
