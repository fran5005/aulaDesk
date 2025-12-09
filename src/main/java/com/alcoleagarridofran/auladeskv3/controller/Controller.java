package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.auth.AuthResponse;
import com.alcoleagarridofran.auladeskv3.auth.AuthService;
import com.alcoleagarridofran.auladeskv3.auth.LoginRequest;
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
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Controller {

    @Autowired
    private ConfigurableApplicationContext context;
    @Autowired
    private AuthService authService;

    private String FxmlRegistros = "/com/java/fx/registros.fxml";
    private String FxmlProfesor = "/com/java/fx/profesor.fxml";
    private String FxmlAlumno = "/com/java/fx/alumno.fxml";

    @FXML
    private TextField textUser;
    @FXML
    private PasswordField textPass;
    @FXML
    private Button buttonLogin;

    @FXML
    public void pulsaBotonLogin() throws IOException {
        try {

            String correo = textUser.getText();
            String contrasenya = textPass.getText();

            LoginRequest loginRequest = new LoginRequest(correo, contrasenya);

            AuthResponse authResponse = authService.login(loginRequest);
            if (authResponse != null && authResponse.getRol() != null) {
                String rol = authResponse.getRol();
                String fxmlDestino;

                switch (rol) {
                    case "ADMIN":
                        fxmlDestino = FxmlRegistros;
                        break;
                    case "PROFESOR":
                        fxmlDestino = FxmlProfesor;
                        break;
                    case "ALUMNO":
                        fxmlDestino = FxmlAlumno;
                        break;
                    default:
                        System.err.println("[ERROR] Rol no reconocido: " + rol);
                        return;
                }

                cambiarEscena(fxmlDestino);

            } else {
                System.out.println("Error de autenticación");
            }
        } catch (Exception e) {

            System.err.println("[DEBUG] Error de Login: Credenciales inválidas o error de servicio.");

            e.printStackTrace();
        }
    }

    private void cambiarEscena(String fxmlPath) throws IOException {

        Stage oldStage = (Stage) buttonLogin.getScene().getWindow();
        oldStage.close();

        Stage newStage = new Stage();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(context::getBean);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.setTitle("Registrar");
        Image applicationIcon = new Image(getClass().getResourceAsStream("/images/logoAulaDesk.png"));
        newStage.getIcons().add(applicationIcon);
        newStage.show();
    }
}

