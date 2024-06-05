package modele;
import java.util.List;
/**
 * La classe Algorithme permet d'exécuter les différents algorithmes de tris,
 * tels que l'algorithme de selection ainsi que l'algorithme heuristique
 */
public class Algorithme {

    /**
     * L'apprenti ordonnateur
     */
    private ApprentiOrdonnateur apprentiOrdonnateur;

    /**
     * Une liste contenant tous les cristaux
     */
    private List<Cristal> cristaux;

    /**
     * Une liste contenant tous les temples
     */
    private List<Temple> temples;

    /**
     * Le constructeur de la classe instancie les trois champs
     * @param apprentiOrdonnateur l'apprenti ordonnateur
     * @param cristaux les cristaux du scénario
     * @param temples les temples du scénario
     */

    public Algorithme(ApprentiOrdonnateur apprentiOrdonnateur, List<Cristal> cristaux, List<Temple> temples) {
        this.apprentiOrdonnateur = apprentiOrdonnateur;
        this.cristaux = cristaux;
        this.temples = temples;
    }

    /**La méthode triHeuristique ne prend rien en paramètre et réalise l'algorithme suivant :
     * <ul>
     * <li>cherche le cristal non placé le plus proche</li>
     * <li> le récupère </li>
     * <li> l'amène à son temple correspondant </li>
     * <li> recommence jusqu'à que tous les cristaux soient placés </li>
     * </ul>
     */
    public void triHeuristique() {
        Position positionApprenti = apprentiOrdonnateur.getPosition();
        // Parcours des temples et cristaux.
        for (int i = 0; i < temples.size(); i++) {
            if ( apprentiOrdonnateur.cristalPlusProche() == -1){ return; }
            Cristal cristalProche = cristaux.get(apprentiOrdonnateur.cristalPlusProche());
            // Déplacement vers le cristal le plus proche.
            while (!(positionApprenti.equals(cristalProche.getPositionCristal()))) {
                positionApprenti.deplacementUneCase(cristalProche.getPositionCristal());
            }
            // Echange avec le cristal.
            apprentiOrdonnateur.setEchangeDispo();
            apprentiOrdonnateur.echangeCristaux(cristalProche);
            // Calcul du temple associé
            Temple templeAssocie = temples.get(apprentiOrdonnateur.templeAssocie(apprentiOrdonnateur.getCristalPorte()));
            //Déplacement vers le temple associé.
            while (!(positionApprenti.equals(templeAssocie.getPositionTemple()))) {
                positionApprenti.deplacementUneCase(templeAssocie.getPositionTemple());
                if (apprentiOrdonnateur.getCristalPorte() != null) {
                    apprentiOrdonnateur.getCristalPorte().getPositionCristal().deplacementUneCase(templeAssocie.getPositionTemple());
                }
            }
            // Si le cristal n'est pas le dernier, l'apprenti ordonnateur échange les cristaux.
            if ( i != temples.size()-1) {
                apprentiOrdonnateur.echangeCristaux(cristaux.get(i));
            }

            // Si le cristal est le dernier, on le lâche.
            apprentiOrdonnateur.lacherCristal();
        }
        System.out.println("La liste des cristaux est : " + cristaux.toString());
        System.out.println("La liste des temples est  : " + temples.toString());
    }

    /**
     * La méthode triSelectionSansDéplacement ne prend rien en paramètre et réalise l'algorithme suivant :
     * - elle ne déplace pas les éléments inutilement, elle les échange seulement lorsque c'est nécessaire.
     * -Les cristaux sont triés en fonction de leur temple associé
     * -Utilise deux boucles imbriquées pour trouver et échanger le plus petit élément non trié avec le premier élément non trié de la liste.
     */
    public void triSelectionSansDeplacement() {
        //Parcours des cristaux par indice
        for (int i = 0; i < cristaux.size() - 1; i++) {
            // Initialisation du minimum à l'indice 0
            int minIndex = i;
            for (int j = i + 1; j < cristaux.size(); j++) {
                if (apprentiOrdonnateur.templeAssocie(cristaux.get(j)) < apprentiOrdonnateur.templeAssocie(cristaux.get(minIndex))) {
                    minIndex = j;

                }
            }
            // Échange des éléments
            Cristal temp = cristaux.get(minIndex);
            cristaux.set(minIndex, cristaux.get(i));
            cristaux.set(i, temp);
        }

    }

}
