package com.espinozameridaal.modernui.controllers;

import com.espinozameridaal.modernui.services.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private final AuthService authService = new AuthService();
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize() {
        if (errorLabel != null) {
            errorLabel.setVisible(false);
            errorLabel.setManaged(false);
        }
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        boolean ok = authService.login(username, password);
        if (!ok) {
            if (errorLabel != null) {
                errorLabel.setText("Invalid username or password");
                errorLabel.setVisible(true);
                errorLabel.setManaged(true);
            }
            return;
        }

        // Success -> open dashboard
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/espinozameridaal/modernui/fxml/dashboard.fxml")
            );
            Parent root = loader.load();
            DashboardController controller = loader.getController();
            controller.setCurrentUsername(username);

            Scene scene = new Scene(root, 1100, 700);
            scene.getStylesheets().add(
                    getClass().getResource("/com/espinozameridaal/modernui/css/style.css").toExternalForm()
            );

            if (stage == null) {
                stage = (Stage) usernameField.getScene().getWindow();
            }
            stage.setTitle("Modern Chat - " + username);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            if (errorLabel != null) {
                errorLabel.setText("Failed to load dashboard UI");
                errorLabel.setVisible(true);
                errorLabel.setManaged(true);
            }
        }
    }
}
