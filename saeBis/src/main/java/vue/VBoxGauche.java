package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import modele.ApprentiOrdonnateur;
import modele.ConstantesCanvas;
import controleur.Controleur;

import java.io.File;

/**
 * La classe VBoxGauche est la racine de la vue de l'application.
 *  Elle organise les différents composants de la partie gauche de l'interface (les boutons, la carte, et les menus)
 */
public class VBoxGauche extends VBox implements ConstantesCanvas {
    /**
     * Représente le joueur
     */
    private static ApprentiOrdonnateur apprenti;

    /**
     * Architecture MVC
     */
    private static Controleur controleur;

    /**
     * Représente la carte
     */
    private static VBoxCanva canva;



    /**
     * Constructeur de la classe pour initialiser les composants de l'interface et les ajoutées.
     */
    public VBoxGauche() {

        // instancie les composantes
        apprenti = new ApprentiOrdonnateur();
        canva = new VBoxCanva();
        controleur = new Controleur();

        // La barre de menus
        MenuBar menuBar = new MenuBar();
        VBoxCanva.setMargin(menuBar, new Insets(9));



        //Le menus des scénarios
        Menu menuScenarios = new Menu(INTITULE_MENU_ITEMS[0]);
        menuBar.getMenus().add(menuScenarios);

        File scenariosDir = new File("scenarios");
        //Les items du menu scénarios
        File[] scenarios = scenariosDir.listFiles(); //lire fichier
        for (int i = 0 ; i < scenarios.length; i ++){
            MenuItem menuItem = new MenuItem(scenarios[i].getName());
            menuItem.setUserData(scenarios[i]);
            menuItem.setOnAction(controleur);
            menuScenarios.getItems().add(menuItem);
        }

        // ajout
        this.getChildren().add(menuBar);

        Menu menuAlgo = new Menu(INTITULE_MENU_ITEMS[1]);
        menuBar.getMenus().add(menuAlgo);
        MenuItem itemAlgoTriSelection = new MenuItem("Algorithme de tri par sélection");
        MenuItem itemAlgoHeuristique = new MenuItem("Algorithme Heuristique");
        itemAlgoTriSelection.setOnAction(controleur);
        itemAlgoHeuristique.setOnAction(controleur);
        menuAlgo.getItems().addAll(itemAlgoTriSelection, itemAlgoHeuristique);
        this.getChildren().add(canva);
    }


    /**
     * Accesseur au champ apprenti
     * @return apprenti, le joueur
     */
    public static ApprentiOrdonnateur getApprenti() {
        return apprenti;
    }

    /**
     * Accesseur au champ canvas
     * @return canvas, la carte
     */
    public static VBoxCanva getCanvas() {
        return canva;
    }

    /**
     * Accesseur au champ contrôleur
     * @return contrôleur
     */
    public static Controleur getControleur(){
        return controleur;
    }

}
