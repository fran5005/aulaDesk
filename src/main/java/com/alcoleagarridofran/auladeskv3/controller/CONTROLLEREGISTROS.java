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
public class CONTROLLEREGISTROS {
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

        // 1. Obtener la ventana (Stage) actual y cerrarla
        // Usamos textProfesor (o cualquier otro botón) para obtener la ventana actual
        Stage oldStage = (Stage) textProfesor.getScene().getWindow();
        oldStage.close();

        // 2. Crear una nueva ventana (Stage)
        Stage newStage = new Stage();

        // 3. Cargar el FXML, usando el ControllerFactory de Spring
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        // ¡CRUCIAL! Asegura que los controladores de la nueva vista (@FXML)
        // tengan sus dependencias de Spring inyectadas (@Autowired).
        fxmlLoader.setControllerFactory(context::getBean);

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        // 4. Mostrar la nueva ventana
        newStage.setScene(scene);
        newStage.setTitle(title);
        newStage.show();
    }
}
