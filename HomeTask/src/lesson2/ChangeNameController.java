package lesson2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lesson2.chatLogic.AuthService;

import java.io.IOException;

public class ChangeNameController {

    @FXML
    private Button saveButton;

    @FXML
    private TextField userName;

    @FXML
    private TextField newUserName;

    @FXML
    void initialize(){
        saveButton.setOnAction(event -> {
            changeName();

        });
    }

    private void changeName() {
        AuthService authService = new AuthService();

        authService.changeUserName(userName.getText(), newUserName.getText());

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
        saveButton.getScene().getWindow().hide();
        stage.showAndWait();

    }


}