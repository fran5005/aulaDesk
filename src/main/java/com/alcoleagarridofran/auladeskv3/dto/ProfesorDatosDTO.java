package com.alcoleagarridofran.auladeskv3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDatosDTO {
    private Integer idProfesor;
    private String nombre;
    private String apellidos;
}
