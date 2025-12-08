package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.model.Materia;
import com.alcoleagarridofran.auladeskv3.repository.IMateriaRepository;
import com.alcoleagarridofran.auladeskv3.service.MateriaService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

     Materia item;

    @FXML
     TextField textId;
    @FXML
    private TextField textNombre;
    @FXML
    private Button textGuardar;
    @FXML
    private Button textModificar;
    @FXML
    private Button textEliminar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textGuardar.setOnAction(event -> insertarMateria());
        textModificar.setOnAction(Event -> actualizarMateria());
    }
    @FXML
    public void insertarMateria(){
        Materia materia = new Materia();

        materia.setIdMateria(Integer.parseInt(textId.getText()));
        materia.setNombre(textNombre.getText().trim());
        materiaService.insertarMateria(materia);
    }
    @FXML
    public void actualizarMateria(){
        item.setIdMateria(Integer.valueOf(textId.getText()));
        item.setNombre(textNombre.getText());
        materiaService.actualizarMateria(item);
    }

}
