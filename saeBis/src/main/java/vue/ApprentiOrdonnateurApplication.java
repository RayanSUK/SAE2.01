package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La classe ApprentiOrdonnateurApplication permet de lancer l'application
 */
public class ApprentiOrdonnateurApplication extends Application {

    /**
     * Méthode principale de l'application pour lancer l'interface graphique
     * @param stage, la fenêtre principale de l'application
     */
    public void start(Stage stage) {
        HBoxRoot root = new HBoxRoot();
        Scene scene = new Scene(root,700,580);
        stage.setScene(scene);
        stage.setTitle("PROJETSAE");
        stage.show();

    }
}
