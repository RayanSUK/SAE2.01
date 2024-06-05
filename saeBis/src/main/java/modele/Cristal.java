package modele;

/**
 * La classe cristal représente les cristaux du jeu avec leurs caractéristiques
 */
public class Cristal {
    /**
     * La position du cristal
     */
    private Position positionCristal;

    /**
     * La couleur du cristal
     */
    private int couleurCristal;

    /**
     * Constructeur de la classe.
     * Reçoit en paramètre la position du cristal et la couleur du cristal puis l'instancie.
     * @param parPositionCristal , la position du cristal
     * @param parCouleurCristal , la couleur du cristal
     */

    public Cristal(Position parPositionCristal, int parCouleurCristal ){
        couleurCristal = parCouleurCristal;
        positionCristal = parPositionCristal;
    }


    /**
     * Accesseur au champ couleurCristal
     * @return couleurCristal, la couleur du cristal
     */
    public int getCouleurCristal() {
        return couleurCristal;
    }

    /**
     * Accesseur au champ positionCristal
     * @return positionCristal, la position du cristal
     */
    public Position getPositionCristal() {
        return positionCristal;
    }

    /**
     * Modifie la position du cristal avec le paramètre parPosition
     * @param parPosition , nouvelle position du cristal
     */

    public void setPositionCristal(Position parPosition){
        positionCristal = parPosition;
    }

    /**
     * Modifie la couleur du cristal avec le paramètre couleurCristal
     * @param couleurCristal, nouvelle couleur du cristal
     */
    public void setCouleurCristal(int couleurCristal) {
        this.couleurCristal = couleurCristal;
    }

    /**
     * Affiche sous forme de texte la position du cristal et sa couleur (qui est un entier)
     * @return une chaine de caractère
     */
    public String toString() {
        return positionCristal.toString() + " de couleur " + couleurCristal;
    }


}

