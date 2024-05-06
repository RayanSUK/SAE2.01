package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import modele.ApprentiOrdonnateur;
import modele.ConstantesCanvas;
import modele.Controleur;

import java.io.File;

public class VBoxRoot extends VBox implements ConstantesCanvas {
    private static ApprentiOrdonnateur apprenti;
    private static Controleur controleur;
    private static VBoxCanva canva;

    public VBoxRoot() {
        apprenti = new ApprentiOrdonnateur();
        canva = new VBoxCanva();
        controleur = new Controleur();

        // La barre de menus
        MenuBar menuBar = new MenuBar();
        VBoxCanva.setMargin(menuBar, new Insets(9));



        //Le menus des scénarios
        Menu menuScenarios = new Menu(INTITULE_MENU_SCENARIOS[0]);
        menuBar.getMenus().add(menuScenarios);

        //Les items du menu scénarios
        File[] scenarios = new File("C:\\Users\\sukka\\Desktop\\COURS\\S2\\JAVA\\DEV_J\\IntelliJ_projets_IHM" +
                "\\IHM\\SAE2.01\\saeBis\\scenarios").listFiles();
        for (int i = 0 ; i < scenarios.length; i ++){
            MenuItem menuItem = new MenuItem(scenarios[i].getName());
            menuItem.setUserData(scenarios[i]);
            menuItem.setOnAction(controleur);
            menuScenarios.getItems().add(menuItem);
        }


        // la vue
        this.getChildren().add(menuBar);
        this.getChildren().add(canva);

    }
    public static ApprentiOrdonnateur getApprenti() {
        return apprenti;
    }

    public static VBoxCanva getCanvas() {
        return canva;
    }
}
