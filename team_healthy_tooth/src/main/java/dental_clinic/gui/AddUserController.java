package dental_clinic.gui;

import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.services.user.AddUserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class AddUserController {

    @Autowired
    private AddUserService addUserService;

    @FXML
    private TextField name;

    @FXML
    private Button enterButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField surname;

    @FXML
    private TextField roleId;

    public AddUserController() {
    }

    @FXML
    @Autowired
    void initialize( ) {
        enterButton.setOnAction(event -> {
            /*enterButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/addUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain =new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();*/

            AddUserRequest addUserRequest = new AddUserRequest(name.getText(), surname.getText(), login.getText(), password.getText(), Long.parseLong(roleId.getText()));
            addUserService.execute(addUserRequest);
        });
        backButton.setOnAction(event -> {
            backButton.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream iconStream = getClass().getResourceAsStream("/icon/dentalChair.png");
            Image iconMain =new Image(iconStream);
            Stage stage = new Stage();
            stage.getIcons().add(iconMain);
            stage.setTitle("Dental Clinic");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });
    }

}
