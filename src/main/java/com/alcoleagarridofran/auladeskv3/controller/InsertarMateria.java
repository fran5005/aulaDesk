package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.model.Materia;
import com.alcoleagarridofran.auladeskv3.repository.IMateriaRepository;
import com.alcoleagarridofran.auladeskv3.service.MateriaService;
import io.jsonwebtoken.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class InsertarMateria implements Initializable {

    public final IMateriaRepository materiaRepository;
    public final MateriaService materiaService;

    @Autowired
    private ConfigurableApplicationContext context;
    private int id;
    Materia item;

    @FXML
    private TextField textID;
    @FXML
    private TextField textNombre;
    @FXML
    private Button textGuardar;
    @FXML
    private Button textModificar;
    @FXML
    private Button textEliminar;
    @FXML
    private ListView listado;
    @FXML
     Button textHomes;
    @FXML
     Button textBack;

    private String back = "/com/java/fx/registros.fxml";
    private String home = "/com/java/fx/main.fxml";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textGuardar.setOnAction(event -> insertarMateria());
        textModificar.setOnAction(Event -> actualizarMateria());
        textEliminar.setOnAction(event -> eliminarMateria());

        list();

        listado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                Materia materiaSeleccionada = (Materia) newValue;
                id = materiaSeleccionada.getIdMateria();
                item = item(); // ¡Ojo! Revisar la llamada a item() más abajo
                textID.setText(String.valueOf(item.getIdMateria()));
                textNombre.setText(item.getNombre());
            }
        });
    }

    public Materia item(){
        return materiaRepository.findById(id).get();
    }

    public void list(){
        listado.setItems(FXCollections.observableArrayList(materiaRepository.findAll()));
    }

    @FXML
    public void insertarMateria(){
        Materia materia = new Materia();

        materia.setIdMateria(Integer.parseInt(textID.getText()));
        materia.setNombre(textNombre.getText().trim());
        materiaService.insertarMateria(materia);
        list();
        limpiarCampos();
    }
    @FXML
    public void actualizarMateria(){
        item.setIdMateria(Integer.valueOf(textID.getText()));
        item.setNombre(textNombre.getText());
        materiaService.actualizarMateria(item);
        list();
        limpiarCampos();
    }
    @FXML
    public void eliminarMateria(){
        materiaService.eliminarMateria(id);
        list();
        limpiarCampos();
    }

    private void limpiarCampos() {
        textID.clear();
        textNombre.clear();
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

        // 1. Obtener la ventana (Stage) actual y cerrarla
        // Usamos textProfesor (o cualquier otro botón) para obtener la ventana actual
        Stage oldStage = (Stage) textHomes.getScene().getWindow();
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
        newStage.show();
    }

}
