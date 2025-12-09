package com.alcoleagarridofran.auladeskv3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoLoginDTO {
    private Integer nre;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    
    private String correo;
    private String contrasenya;
}
