package com.alcoleagarridofran.auladeskv3.repository;

import com.alcoleagarridofran.auladeskv3.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> deleteByIdUsuario(int idUsuario);
}
