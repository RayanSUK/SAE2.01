package modele;

/**
 * La classe cristal représente les cristaux du jeu avec leurs caractéristiques
 */
public class Cristal {
    /**
     * La position du cristal
     */
    private Position positioncristal;

    /**
     * La couleur du cristal
     */
    private int couleurcristal;

    /**
     * Constructeur de la classe.
     * Reçoit en paramètre la position du cristal et la couleur du cristal puis l'instancie.
     * @param parPositioncristal , la position du cristal
     * @param parCouleurcristal , la couleur du cristal
     */

    public Cristal(Position parPositioncristal, int parCouleurcristal ){
        couleurcristal = parCouleurcristal;
        positioncristal = parPositioncristal;
    }


    /**
     * Accesseur au champ couleurCristal
     * @return couleurCristal, la couleur du cristal
     */
    public int getCouleurCristal() {
        return couleurcristal;
    }

    /**
     * Accesseur au champ positionCristal
     * @return positionCristal, la position du cristal
     */
    public Position getPositionCristal() {
        return positioncristal;
    }

    /**
     * Modifie la position du cristal avec le paramètre parPosition
     * @param parPosition , nouvelle position du cristal
     */

    public void setPositionCristal(Position parPosition){
        positioncristal = parPosition;
    }

    /**
     * Modifie la couleur du cristal avec le paramètre couleurCristal
     * @param couleurcristal, nouvelle couleur du cristal
     */
    public void setCouleurCristal(int couleurcristal) {
        this.couleurcristal = couleurcristal;
    }

    /**
     * Affiche sous forme de texte la position du cristal et sa couleur (qui est un entier)
     * @return une chaine de caractère
     */
    public String toString() {
        return positioncristal.toString() + " " + couleurcristal;
    }


}

