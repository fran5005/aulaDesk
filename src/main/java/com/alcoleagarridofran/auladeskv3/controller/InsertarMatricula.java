package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.model.Profesor;
import io.jsonwebtoken.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsertarMatricula {

    @Autowired
    private ConfigurableApplicationContext context;
    @FXML
    Button textHomes;

    private String home = "/com/java/fx/main.fxml";

    public void home()throws IOException, java.io.IOException{
        System.out.println("[DEBUG] Volviendo a home");
        cambiarEscena(home);
    }
    private void cambiarEscena(String fxmlPath) throws IOException, java.io.IOException {


        Stage oldStage = (Stage) textHomes.getScene().getWindow();
        oldStage.close();

        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(context::getBean);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
}
