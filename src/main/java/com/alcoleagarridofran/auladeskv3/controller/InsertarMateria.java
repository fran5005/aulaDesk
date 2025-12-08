package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.model.Materia;
import com.alcoleagarridofran.auladeskv3.repository.IMateriaRepository;
import com.alcoleagarridofran.auladeskv3.service.MateriaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class InsertarMateria implements Initializable {

    public final IMateriaRepository materiaRepository;
    public final MateriaService materiaService;

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

}
