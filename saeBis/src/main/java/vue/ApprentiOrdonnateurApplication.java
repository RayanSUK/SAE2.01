package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Position;
import org.example.saebis.HelloApplication;

import java.io.File;
import java.io.IOException;

public class ApprentiOrdonnateurApplication extends Application {
    public void start(Stage stage) {
        VBox root = new VboxRoot();
        Scene scene = new Scene(root,600,580);
        stage.setScene(scene);
        stage.setTitle("PROJETSAE");
        stage.show();

    }
}
