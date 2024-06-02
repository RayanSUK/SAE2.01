package modele;

import javafx.scene.paint.Color;

/**
 * L'interface ConstantesCanvas définis des constantes dans le contexte de notre carte
 */
public interface ConstantesCanvas {

    // dimensions de la grille
    /**
     * Largeur de la grille
     */
    final int LARGEUR_CANVAS = 640;

    /**
     * Hauteur de la grille
     */
    final int HAUTEUR_CANVAS = 640;

    /**
     * Taille d'un carré de la grille
     */
    final int CARRE = 20;

    /**
     * Couleur de la grille
     */
    final Color COULEUR_GRILLE = Color.BLACK;

    /**
     * Couleur de du joueur
     */
    final Color COULEUR_APPRENTI = Color.PURPLE;

    /**
     * Couleur utilisée pour indiquer un clic.
     */

    final Color COULEUR_CLIQUE = Color.RED;

    /**
     * Couleur des temples
     */

    final Color [] COULEURS_TEMPLES = {Color.BLUE,Color.YELLOW,Color.GREEN,Color.ORANGE,Color.FUCHSIA,
    Color.BROWN, Color.SKYBLUE, Color.SIENNA,Color.PINK,Color.LEMONCHIFFON,Color.SEAGREEN,};

    /**
     * Largeur des cristaux (ils sont de formes ovales)
     */

    final int LARGEUR_OVALE = 19;

    /**
     * Hauteur des cristaux
     */

    final int HAUTEUR_OVALE = 19;


    /**
     * Noms des options de menu pour le choix des scénarios.
     */
    final String [] INTITULE_MENU_SCENARIOS = {"Choix du scénario"};


}
