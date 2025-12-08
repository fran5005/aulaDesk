package com.alcoleagarridofran.auladeskv3.service;

import com.alcoleagarridofran.auladeskv3.dto.ProfesorDatosDTO;
import com.alcoleagarridofran.auladeskv3.dto.ProfesorLoginDTO;
import com.alcoleagarridofran.auladeskv3.model.Profesor;
import com.alcoleagarridofran.auladeskv3.model.Usuario;
import com.alcoleagarridofran.auladeskv3.repository.IProfesorRepository;
import com.alcoleagarridofran.auladeskv3.repository.IUsuarioRepository;
import com.alcoleagarridofran.auladeskv3.utils.Rol;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    final IProfesorRepository profesorRepository;
    final IUsuarioRepository usuarioRepository;
    final PasswordEncoder passwordEncoder;



    public Profesor insertarProfesor(ProfesorLoginDTO profesorDTO) {

        if (usuarioRepository.findByCorreo(profesorDTO.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }
        if (profesorRepository.existsById(profesorDTO.getIdProfesor())) {
            throw new RuntimeException("El ID de Profesor ya existe.");
        }

        Usuario user = Usuario.builder()
                .correo(profesorDTO.getCorreo())
                .contrasenya(passwordEncoder.encode(profesorDTO.getContrasenya()))
                .rol(Rol.PROFESOR)
                .build();

        Usuario usuarioFinal = usuarioRepository.save(user); // 💡 Aquí usuarioGuardado ya tiene su ID

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(profesorDTO.getIdProfesor());
        profesor.setNombre(profesorDTO.getNombre());
        profesor.setApellidos(profesorDTO.getApellidos());

        // 🔑 VÍNCULO FINAL: Asignamos el objeto Usuario
        profesor.setUsuario(usuarioFinal);
        return profesorRepository.save(profesor);
    }

    public List<Profesor> obtenerProfesores() {
        // 💡 Spring Data JPA ya proporciona el método findAll() en la interfaz Repository
        // que extiende a JpaRepository.
        return profesorRepository.findAll();
    }

    public Profesor findById(Integer id) {
        // 💡 Usa orElseThrow para lanzar una excepción si no se encuentra
        return profesorRepository.findById(id).get();
    }

    public Profesor actualizarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void eliminarProfesor(Integer id) {

        profesorRepository.deleteById(id);

    }
}

