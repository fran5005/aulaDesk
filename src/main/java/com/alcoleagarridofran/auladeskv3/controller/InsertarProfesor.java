package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.dto.ProfesorLoginDTO;
import com.alcoleagarridofran.auladeskv3.model.Profesor;
import com.alcoleagarridofran.auladeskv3.repository.IProfesorRepository;
import com.alcoleagarridofran.auladeskv3.service.ProfesorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class InsertarProfesor implements Initializable {

    public final IProfesorRepository profesorRepository;
    public final ProfesorService profesorService;


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
    private Button textHomes; // Botón Home (🏠)
    @FXML
    private Button textBacks; // Botón Atrás (⏪)

    @FXML
    ListView<Profesor> listado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("[DEBUG] Iniciando insercion de profesores.....");

        // 1. Conexión de Eventos (Si usas setOnAction)
        textGuardar.setOnAction(event -> insertarProfesor());
        textModificar.setOnAction(event -> actualizarProfesor());
        textEliminar.setOnAction(event -> eliminarProfesor());

            // 2. Llamada a la lista
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

        listado.setItems(FXCollections.observableArrayList(profesorRepository.findAll()));

    }

    @FXML
    public void insertarProfesor() {

        ProfesorLoginDTO profesor = new ProfesorLoginDTO();

        // Asignar datos del Profesor (que no son autogenerados)
        profesor.setIdProfesor(Integer.parseInt(textID.getText().trim()));
        profesor.setNombre(textNombre.getText().trim());
        profesor.setApellidos(textApellido.getText().trim());

        // Asignar credenciales de Usuario (para crear la cuenta de login)
        profesor.setCorreo(textCorreo.getText().trim());
        profesor.setContrasenya(textContrasena.getText()); // getText() en PasswordField

        // 3. Llamar al servicio para la lógica de negocio (crea Usuario, obtiene ID y crea Profesor)
        profesorService.insertarProfesor(profesor);
        list();
        limpiarCampos();
    }

    @FXML
    public void actualizarProfesor() {
        try {
            // 2. Actualizar el objeto 'item' con los nuevos datos de los campos de texto
            // EL ID (this.item.getIdProfesor()) ya está en el objeto y no se toca.

            item.setNombre(textNombre.getText());
            item.setApellidos(textApellido.getText());

            // 3. Llamar al servicio para guardar los cambios en la base de datos
            // Usamos el servicio inyectado
            profesorService.actualizarProfesor(item);
            list();
            limpiarCampos();

        } catch (Exception e) {
            System.err.println("Error durante la actualización: " + e.getMessage());
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
}
