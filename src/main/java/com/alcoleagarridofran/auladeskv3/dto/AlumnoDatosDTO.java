package com.alcoleagarridofran.auladeskv3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDatosDTO {
    private int nre;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
}
