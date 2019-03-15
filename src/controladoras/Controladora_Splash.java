package controladoras;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controladora_Splash implements Initializable {

    FadeTransition fadeTransition;
    @FXML
    BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fadeTransition = new FadeTransition(Duration.millis(2000), borderPane);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Fin");
                Stage stage = new Stage();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../vistas/login.fxml"));
                    Scene scene = new Scene(root, 900, 900);
                    stage.setScene(scene);
                    stage.show();
                    stage = (Stage) borderPane.getScene().getWindow();
                    stage.hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fadeTransition.play();
    }
}
