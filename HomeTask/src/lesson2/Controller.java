package lesson2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane registration;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private AnchorPane chat;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField messageField;

    @FXML
    private Button signUpButton;

    @FXML
    private ImageView changeUserName;

    @FXML
    private Button rename;


    final static String SERVER_ADDRESS = "localhost";
    final static int SERVER_PORT = 8118;
    private static Socket socket = null;
    private static DataInputStream in = null;
    private static DataOutputStream out = null;
    boolean isAuthorized;


    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if (!isAuthorized) {
            registration.setVisible(true);
            registration.setManaged(true);
            chat.setVisible(false);
            chat.setManaged(false);
        } else {
            registration.setVisible(false);
            registration.setManaged(false);
            chat.setVisible(true);
            chat.setManaged(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            setAuthorized(false);
            setSignUp();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String strFromServer = in.readUTF();
                            if (strFromServer.startsWith("/authok")) {
                                setAuthorized(true);
                                break;
                            } else {
                                textArea.appendText(strFromServer + "\n");
                            }
                        }
                        while (true) {
                            String strFromServer = in.readUTF();
                            if (strFromServer.equals("/end")) break;
                            textArea.appendText(strFromServer + "\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Controller.this.setAuthorized(false);
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void sendMsgByEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER)
            try {
                out.writeUTF(messageField.getText());
                messageField.clear();
                messageField.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void sendMsgByClick(ActionEvent actionEvent) {
        try {
            out.writeUTF(messageField.getText());
            messageField.clear();
            messageField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSignUp() {
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().
                    getResource("/lesson2/signUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }


    public void setChangeUserName(ActionEvent actionEvent) {
            rename.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().
                    getResource("/lesson2/changeUserName.fxml"));
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


    public void toAuth() {
        try {
            out.writeUTF("/auth " + login.getText().trim() + " " + password.getText().trim());
            login.clear();
            password.clear();
            setAuthorized(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}