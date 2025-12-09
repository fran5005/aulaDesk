package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.dto.ProfesorLoginDTO;
import com.alcoleagarridofran.auladeskv3.model.Profesor;
import com.alcoleagarridofran.auladeskv3.repository.IProfesorRepository;
import com.alcoleagarridofran.auladeskv3.service.ProfesorService;
import io.jsonwebtoken.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class InsertarProfesor implements Initializable {

    public final IProfesorRepository profesorRepository;
    public final ProfesorService profesorService;

    @Autowired
    private ConfigurableApplicationContext context;
    private int id;
    private Profesor item;

    @FXML
    private TextField textID;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido;
    @FXML
    private TextField textCorreo;
    @FXML
    private PasswordField textContrasena;

    @FXML
    private Button textGuardar;
    @FXML
    private Button textModificar;
    @FXML
    private Button textEliminar;
    @FXML
     Button textHomes;
    @FXML
     Button textBacks;

    @FXML
    ListView<Profesor> listado;

    private String back = "/com/java/fx/registros.fxml";
    private String home = "/com/java/fx/main.fxml";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("[DEBUG] Iniciando insercion de profesores.....");


        textGuardar.setOnAction(event -> insertarProfesor());
        textModificar.setOnAction(event -> actualizarProfesor());
        textEliminar.setOnAction(event -> eliminarProfesor());

            list();

        listado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                id = newValue.getIdProfesor();
                item = item();
                textID.setText(String.valueOf(item.getIdProfesor()));
                textNombre.setText(item.getNombre());
                textApellido.setText(item.getApellidos());

            }
        });



    }

    public Profesor item() {
        return profesorRepository.findById(id).get();
    }

    public void list() {

        listado.setItems(FXCollections.observableArrayList(profesorService.obtenerProfesores()));

    }

    @FXML
    public void insertarProfesor() {

        ProfesorLoginDTO profesor = new ProfesorLoginDTO();

        profesor.setIdProfesor(Integer.parseInt(textID.getText().trim()));
        profesor.setNombre(textNombre.getText().trim());
        profesor.setApellidos(textApellido.getText().trim());

        profesor.setCorreo(textCorreo.getText().trim());
        profesor.setContrasenya(textContrasena.getText());

        profesorService.insertarProfesor(profesor);
        list();
        limpiarCampos();
    }

    @FXML
    public void actualizarProfesor() {
        try {

            item.setNombre(textNombre.getText());
            item.setApellidos(textApellido.getText());

            profesorService.actualizarProfesor(item);
            list();
            limpiarCampos();

        } catch (Exception e) {
            System.err.println("[DEBUG] Profesor no actualizado: " + e.getMessage());
            e.printStackTrace();

        }
    }

    @FXML
    public void eliminarProfesor() {
        profesorService.eliminarProfesor(id);
        list();
        limpiarCampos();
    }
    private void limpiarCampos() {
        textID.clear();
        textNombre.clear();
        textApellido.clear();
        textCorreo.clear();
        textContrasena.clear();
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
