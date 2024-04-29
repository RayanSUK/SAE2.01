package modele;

public class Position {
    private static int nombreDePas = 0;
    private int abscisse;
    private int ordonnee;

    public Position(int abscisse, int ordonnee){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /*
    La méthode deplacementUneCase déplace la position this d'une case
    pour la rapprocher de celle du paramètre parPosition.
    Elle incrémente le champ static nombreDePas
    @param parPosition : la position vers laquelle this se rapproche
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




    //La méthode equals regarde si le joueur est arrivé à la position demandée.
    public boolean equals(Position parPosition){
        if(this.ordonnee == parPosition.ordonnee){
            if(this.abscisse == parPosition.abscisse){
                return true;
            }
        }
        return false;
    }

    //Les gets
    public int getNombreDePas(){
        return nombreDePas;
    }

    public int getAbscisse(){
        return abscisse;
    }

    public int getOrdonnee(){
        return ordonnee;
    }

    public String toString(){
        return("Position du joueur : " + this.getAbscisse() + ", " + this.getOrdonnee() + "\n" + "Nombre de pas : " + this.getNombreDePas());
    }

    //La méthode toString

}
