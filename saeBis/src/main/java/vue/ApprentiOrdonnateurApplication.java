package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApprentiOrdonnateurApplication extends Application {
    public void start(Stage stage) {
        VBox root = new VBoxRoot();
        Scene scene = new Scene(root,700,580);
        stage.setScene(scene);
        stage.setTitle("PROJETSAE");
        stage.show();

    }
}
