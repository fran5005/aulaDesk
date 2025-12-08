package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.dto.AlumnoLoginDTO;
import com.alcoleagarridofran.auladeskv3.model.Alumno;
import com.alcoleagarridofran.auladeskv3.repository.IAlumnoRepository;
import com.alcoleagarridofran.auladeskv3.service.AlumnoService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
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

    // 🔑 Estado del Controlador
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
    private Button textHome;
    @FXML
    private Button textBack;

    @FXML
    private ListView<Alumno> listado;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("[DEBUG] Iniciando insercion de alumnos...");

        // 1. Conexión de Eventos
        textSave.setOnAction(event -> insertarAlumno());
        textReplace.setOnAction(event -> actualizarAlumno());
        textDelete.setOnAction(event -> eliminarAlumno());

        // 2. Carga inicial
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

        listado.setItems(FXCollections.observableArrayList(alumnoRepository.findAll()));
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
        // 🔑 Usar getText() del PasswordField
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

}
