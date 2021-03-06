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
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainMenuController {

    @FXML
    private AnchorPane mainMenu;

    @FXML
    private Button backButton;

    @FXML
    private Button addUserButton;

    @FXML
    private Button blockUserButton;

    @FXML
    private Button getUsersButton;

    @FXML
    private Button addRoleButton;

    @FXML
    private Button getRolesButton;

    @FXML
    private Button setRoleButton;

    @FXML
    private Button usersRoleButton;

    @FXML
    private MenuItem addPatientItem;

    @FXML
    private MenuItem changePersonalDataItem;

    @FXML
    private MenuItem searchPatientItem;

    @FXML
    private MenuItem updatePatientJowl;

    @FXML
    private MenuItem addDoctorItem;

    @FXML
    private MenuItem fillDoctorGraphicItem;

    @FXML
    private MenuItem getDoctorListItem;

    @FXML
    private MenuItem deleteDoctorItem;

    @FXML
    private MenuItem addVisitItem;

    @FXML
    private MenuItem changeVisitTimeItem;

    @FXML
    private MenuItem searchVsitByDateItem;

    @FXML
    private MenuItem searchVisitByPersonalCodeItem;

    @FXML
    private MenuItem deletePlannedVisitItem;

    @FXML
    void initialize() {
        addUserButton.setOnAction(event -> {
            //addUser.getScene().getWindow().hide();
            Parent root = null ;
            try {
                addUserButton.getScene().getWindow().hide();
                root = FXMLLoader.load(getClass().getResource("/FXML/addUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain = new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });

        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/scene.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain = new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });
    }
}