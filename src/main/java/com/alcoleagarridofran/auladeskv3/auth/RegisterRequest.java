package com.alcoleagarridofran.auladeskv3.auth;

import com.alcoleagarridofran.auladeskv3.utils.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String correo;
    String contrasenya;
    Rol rol = Rol.ADMIN;
}
