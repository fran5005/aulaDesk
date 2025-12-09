package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.dto.AlumnoLoginDTO;
import com.alcoleagarridofran.auladeskv3.model.Alumno;
import com.alcoleagarridofran.auladeskv3.repository.IAlumnoRepository;
import com.alcoleagarridofran.auladeskv3.service.AlumnoService;
import io.jsonwebtoken.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class InsertarAlumno implements Initializable {


    public final IAlumnoRepository alumnoRepository;
    public final AlumnoService alumnoService;

    @Autowired
    private ConfigurableApplicationContext context;
    private Alumno item;
    private int nre;

    @FXML
    private TextField textNre;
    @FXML
    private TextField textName;
    @FXML
    private TextField textSurnames;
    @FXML
    private DatePicker textDate;
    @FXML
    private TextField textCorreo;

    @FXML
    private PasswordField textContrasena;

    @FXML
    private Button textSave;
    @FXML
    private Button textReplace;
    @FXML
    private Button textDelete;
    @FXML
    Button textHome;
    @FXML
     Button textBack;

    @FXML
    private ListView<Alumno> listado;

    private String back = "/com/java/fx/registros.fxml";
    private String home = "/com/java/fx/main.fxml";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("[DEBUG] Iniciando insercion de alumnos...");

        textSave.setOnAction(event -> insertarAlumno());
        textReplace.setOnAction(event -> actualizarAlumno());
        textDelete.setOnAction(event -> eliminarAlumno());

        list();
        listado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue != null) {
               nre = newValue.getNre();
               item = item();
               textNre.setText(String.valueOf(item.getNre()));
               textName.setText(item.getNombre());
               textSurnames.setText(item.getApellidos());

           }
        });

    }

    public Alumno item() {
        return alumnoRepository.findById(nre).get();
    }

    public void list () {

        listado.setItems(FXCollections.observableArrayList(alumnoService.obtenerAlumnos()));
    }
    @FXML
    public void insertarAlumno() {

        AlumnoLoginDTO alumno = new AlumnoLoginDTO();

        alumno.setNre(Integer.parseInt(textNre.getText().trim()));
        alumno.setNombre(textName.getText().trim());
        alumno.setApellidos(textSurnames.getText().trim());

        if (textDate.getValue() != null) {
            alumno.setFechaNacimiento(Date.from(textDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        alumno.setCorreo(textCorreo.getText().trim());
        alumno.setContrasenya(textContrasena.getText());

        alumnoService.insertarAlumno(alumno);
        list();
        limpiarCampos();

    }

    @FXML
    public void actualizarAlumno() {
        item.setNombre(textName.getText().trim());
        item.setApellidos(textSurnames.getText().trim());

        alumnoService.actualizarAlumno(item);
        list();
        limpiarCampos();
    }

    @FXML
    public void eliminarAlumno() {
    alumnoService.eliminarAlumno(nre);
    limpiarCampos();
    list();
    }
    private void limpiarCampos() {
        textNre.clear();
        textName.clear();
        textSurnames.clear();
        textCorreo.clear();
        textContrasena.clear();
        textDate.setValue(null);
    }

    public void back()throws IOException, java.io.IOException{
        System.out.println("[DEBUG] Volviendo atras");
        cambiarEscena(back);
    }

    public void home()throws IOException, java.io.IOException{
        System.out.println("[DEBUG] Volviendo a home");
        cambiarEscena(home);
    }
    private void cambiarEscena(String fxmlPath) throws IOException, java.io.IOException {

        Stage oldStage = (Stage) textHome.getScene().getWindow();
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
