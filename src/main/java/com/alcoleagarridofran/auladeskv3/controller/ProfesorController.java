package com.alcoleagarridofran.auladeskv3.controller;

import com.alcoleagarridofran.auladeskv3.model.Profesor;
import com.alcoleagarridofran.auladeskv3.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;
/*
    @PostMapping("/insertar")
    private ResponseEntity<Profesor> insertar(@RequestBody Profesor profesor){

        Profesor prof = profesorService.insertarProfesor(profesor);
        return new ResponseEntity<>(prof, HttpStatus.CREATED);
    }

 */
}
