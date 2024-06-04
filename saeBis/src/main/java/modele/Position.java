package modele;

/**
 * La classe position représente la position en abscisse et en ordonnée
 */
public class Position {
    /**
     * Compteur du nombre de pas
     */
    private int nombreDePas = 0;
    /**
     * La position en abscisse
     */
    private int abscisse;

    /**
     * La position en ordonnée
     */
    private int ordonnee;

    /**
     * Constructeur de la classe. Reçoit en paramètre la position abscisse et la position ordonnée puis les instancie
     * @param abscisse, la position en abscisse
     * @param ordonnee, la position en ordonnée
     */

    public Position(int abscisse, int ordonnee){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /**
     * La méthode deplacementUneCase déplace la position this d'une case
     * pour la rapprocher de celle du paramètre parPosition.
     * Elle incrémente le champ static nombreDePas
     * @param parPosition : la position vers laquelle this se rapproche
     */

    public void deplacementUneCase (Position parPosition){
        nombreDePas++;
        if (this.abscisse > parPosition.abscisse){
            this.abscisse -=1;
            return;
        }
        if (this.abscisse < parPosition.abscisse) {
            this.abscisse += 1;
            return;
        }

        if (this.ordonnee > parPosition.ordonnee) {
            this.ordonnee -= 1;
            return;
        }

        if (this.ordonnee < parPosition.ordonnee) {
            this.ordonnee += 1;
            return;
        }

    }

    /**
     * La méthode deplacementCristal déplace la position this d'un cristal
     * pour la rapprocher de celle du paramètre parPosition.
     * @param parPosition, la position vers laquelle this se rapproche
     */
    public void deplacementCristal (Position parPosition){
        if (this.abscisse > parPosition.abscisse){
            this.abscisse -=1;
            return;
        }
        if (this.abscisse < parPosition.abscisse) {
            this.abscisse += 1;
            return;
        }

        if (this.ordonnee > parPosition.ordonnee) {
            this.ordonnee -= 1;
            return;
        }

        if (this.ordonnee < parPosition.ordonnee) {
            this.ordonnee += 1;
            return;
        }

    }


    /**
     * Méthode qui reçoit en paramètre une position et regarde si les positions sont les mêmes et regarde si la position de l'objet appelant est la même.
     * @param parPosition , la position demandée
     * @return true si les positions sont les mêmes, sinon False
     */
    public boolean equals(Position parPosition){
        if(this.ordonnee == parPosition.ordonnee){
            if(this.abscisse == parPosition.abscisse){
                return true;
            }
        }
        return false;
    }

    /**
     * Accesseur au champ nombreDePas
     * @return nombreDePas, le nombre de pas du joueur
     */
    public int getNombreDePas(){
        return nombreDePas;
    }

    /**
     * Accesseur au champ abscisse
     * @return abscisse, la position en abscisse
     */
    public int getAbscisse(){
        return abscisse;
    }

    /**
     * Accesseur au champ ordonnee
     * @return ordonnee, la position en ordonnée
     */

    public int getOrdonnee(){
        return ordonnee;
    }

    /**
     * Reçoit en paramètre parAbscisse et parOrd pour modifier la position
     * @param parAbscisse, position en abscisse
     * @param parOrd , position en ordonnée
     */
    public void setPosition(int parAbscisse, int parOrd){
        abscisse = parAbscisse;
        ordonnee = parOrd;
    }

    /**
     * La méthode resetNombreDePas permet de remettre le compteur à 0.
     * Nous l'utiliserons pour nos tests unitaires
     */
    public void resetNombreDePas() {
        nombreDePas = 0;
    }

    /**
     * Retourne sous forme de texte la position avec son abscisse et son ordonnée
     * @return une chaine de caractère
     */
    public String toString(){
        return("Position : " + (this.getAbscisse()-16) + ", " + (this.getOrdonnee()-16));
    }




}
