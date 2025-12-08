package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.auth.AuthResponse;
import com.alcoleagarridofran.auladeskv3.auth.AuthService;
import com.alcoleagarridofran.auladeskv3.auth.LoginRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CONTROLLER {

    @Autowired
    private ConfigurableApplicationContext context;
    @Autowired
    private AuthService authService;

    private String FxmlRegistros = "/com/java/fx/registros.fxml";

    // 2. Mapeo de elementos del FXML
    // Los campos fx:id en el FXML deben coincidir con estos nombres de variables
    @FXML
    private TextField textUser;
    @FXML
    private PasswordField textPass;
    @FXML
    private Button buttonLogin;

    // 3. Manejador de Eventos del Botón
    // Debes añadir 'onAction="#pulsaBotonLogin"' al botón en el FXML (ver Paso 2)
    @FXML
    public void pulsaBotonLogin() throws IOException {
        try {
            // A. Recoger credenciales de la interfaz
            String correo = textUser.getText();
            String contrasenya = textPass.getText();

            // B. Crear el DTO de solicitud de login
            LoginRequest loginRequest = new LoginRequest(correo, contrasenya);

            // C. Llamar al servicio de autenticación
            // Esto devolverá un objeto AuthResponse con el token JWT si es exitoso.
            AuthResponse authResponse = authService.login(loginRequest);

            // D. Si la autenticación es exitosa:
            System.out.println("[DEBUG] Entrando al Login");
            // 💡 Aquí iría la lógica para cambiar la escena principal de la aplicación.
            cambiarEscena(FxmlRegistros);
        } catch (Exception e) {
            // Manejo de errores de autenticación (ej. credenciales incorrectas)
            System.err.println("[DEBUG] Error de Login: Credenciales inválidas o error de servicio.");
            // 💡 Aquí iría la lógica para mostrar un mensaje de error en la interfaz.
            e.printStackTrace();
        }


    }

    private void cambiarEscena(String fxmlPath) throws IOException {
        // 1. Obtener la ventana (Stage) actual del botón de Login
        Stage oldStage = (Stage) buttonLogin.getScene().getWindow();

        // 2. Cerrar la ventana de Login
        oldStage.close();

        // 3. Crear una nueva ventana (Stage)
        Stage newStage = new Stage();

        // 4. Cargar el FXML, usando el ControllerFactory de Spring
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(context::getBean); // ¡CRUCIAL para inyección en el nuevo controlador!

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // 5. Mostrar la nueva ventana
        newStage.setScene(scene);
        newStage.setTitle("Registrar");
        Image applicationIcon = new Image(getClass().getResourceAsStream("/images/logoAulaDesk.png"));
        newStage.getIcons().add(applicationIcon);
        newStage.show();
    }


}

