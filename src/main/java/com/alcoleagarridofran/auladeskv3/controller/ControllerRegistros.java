package com.alcoleagarridofran.auladeskv3.controller;

import io.jsonwebtoken.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ControllerRegistros {
    @Autowired
    private ConfigurableApplicationContext context;

    @FXML
    private Button textProfesor;
    @FXML
    private Button textAlumno;
    @FXML
    private Button textMateria;

    private String profesorInterfaz = "/com/java/fx/insertarProfesor.fxml";
    private String alumnoInterfaz = "/com/java/fx/insertarAlumno.fxml";
    private String materiaInterfaz = "/com/java/fx/insertarMateria.fxml";

    @FXML
    public void pulsarBotonProfesor() throws IOException, java.io.IOException {
        System.out.println("[DEBUG] Abriendo registro de Profesor");
        cambiarEscena(profesorInterfaz, "Insertar Profesores");
    }

    @FXML
    public void pulsarBotonAlumno() throws IOException, java.io.IOException {
        System.out.println("[DEBUG] Abriendo registro de Alumno");
        cambiarEscena(alumnoInterfaz, "Insertar Alumnos");
    }
    @FXML
    public void pulsarBotonMateria() throws IOException, java.io.IOException {
        System.out.println("[DEBUG] Abriendo registro de Materia");
        cambiarEscena(materiaInterfaz, "Insertar Materias");
    }

    private void cambiarEscena(String fxmlPath, String title) throws IOException, java.io.IOException {

        Stage oldStage = (Stage) textProfesor.getScene().getWindow();
        oldStage.close();

        Stage newStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        fxmlLoader.setControllerFactory(context::getBean);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        newStage.setScene(scene);
        newStage.setTitle(title);
        newStage.show();
    }
}
