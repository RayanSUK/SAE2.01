package modele;

/**
 * La classe temple représente les temples du jeu avec leurs caractéristiques
 */
public class Temple {
    /**
     * La position du temple, en final car elle est fixe
     */
    private final Position positionTemple;

    /**
     * La couleur du temple
     */
    private int couleurTemple;

    /**
     * Le constructeur de la classe qui reçoit en paramètre la position et la couleur du temple et les instancie
     * @param parPosition, la position du temple
     * @param parCouleurTemple, la couleur du temple
     */
    public Temple(Position parPosition, int parCouleurTemple) {
        positionTemple = parPosition;
        couleurTemple = parCouleurTemple;

    }

    //création des accesseurs et des setters

    /**
     * Acceseur au champ temple
     * @return couleurTemple, les couleurs des temples
     */
    public int getCouleurTemple() {
        return couleurTemple;
    }

    /**
     * Modifie la couleur des temples
     * @return couleurTemple, les couleurs des temples
     */
    public void setCouleurTemple(int couleurTemple) {
        this.couleurTemple = couleurTemple;
    }

    /**
     * Accesseur au champ positionTemple
     * @return positonTemple, la position du temple
     */
    public Position getPositionTemple() {
        return positionTemple;
    }

    /**
     * Retourne sous forme de texte la position du temple ainsi que sa couleur
     * @return une chaine de caractère
     */
    public String toString() {
        return positionTemple.toString() + " " + couleurTemple;
    }
}





