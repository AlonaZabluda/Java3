package lesson2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lesson2.chatLogic.AuthService;
import lesson2.chatLogic.NewUser;

import java.io.IOException;


public class SignUpController {


    @FXML
    private Button signUpButt;

    @FXML
    private TextField userName;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;


    @FXML
    void initialize(){
        signUpButt.setOnAction(event -> {
            signUpNewUser();

        });
    }

    private void signUpNewUser() {
        AuthService authService = new AuthService();

        String userN = userName.getText();
        String log = login.getText();
        String pass = password.getText();
        NewUser newUser = new NewUser(userN, log, pass);
        authService.signUpUser(newUser);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().
                getResource("/lesson2/sample.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }
}