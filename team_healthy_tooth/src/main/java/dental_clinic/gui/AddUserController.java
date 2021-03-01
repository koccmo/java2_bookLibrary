package dental_clinic.gui;

import dental_clinic.core.requests.user.AddUserRequest;
import dental_clinic.core.services.user.AddUserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AddUserController {

    @Autowired
    private AddUserService addUserService;

    @FXML
    private TextField name;

    @FXML
    private Button enterButton;

    @FXML
    private Button exitButton;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField surname;

    @FXML
    private TextField roleId;

    @FXML
    void initialize( ) {
        enterButton.setOnAction(event -> {
            AddUserRequest addUserRequest = new AddUserRequest(name.getText(), surname.getText(), login.getText(), password.getText(), Long.parseLong(roleId.getText()));
            addUserService.execute(addUserRequest);
        }
        );
    }

}
