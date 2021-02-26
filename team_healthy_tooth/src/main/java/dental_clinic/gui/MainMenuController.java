package dental_clinic.gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainMenu;

    @FXML
    private Button backButton;

   /*@FXML
   public void backButton {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }*/

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            Parent root = null;
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain =new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/scene.fxml"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });



    }
}

