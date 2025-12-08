package com.alcoleagarridofran.auladeskv3.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.alcoleagarridofran.auladeskv3.utils.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author franc
 */
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
        @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
        @NamedQuery(name = "Usuario.findByContrasenya", query = "SELECT u FROM Usuario u WHERE u.contrasenya = :contrasenya"),
        @NamedQuery(name = "Usuario.findByRol", query = "SELECT u FROM Usuario u WHERE u.rol = :rol")})

public class Usuario implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Basic(optional = false)
    @Column(name = "contrasenya")
    private String contrasenya;
    @Basic(optional = false)
    @Column(name = "rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @OneToOne(mappedBy = "usuario")
    private Alumno alumno;
    @OneToOne(mappedBy = "usuario")
    private Profesor profesor;

    @Builder
    public Usuario(String correo, String contrasenya, Rol rol) {
        this.correo = correo;
        this.contrasenya = contrasenya;
        this.rol = rol;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    }

    @Override
    public boolean isEnabled() {
        return true;    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public String getPassword() {
        return contrasenya;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.toString()));
    }


    @Override
    public String toString() {
        return "com.mycompany.mavenproject4.asdasd.Usuario[ correo=" + correo + " ]";
    }

}
