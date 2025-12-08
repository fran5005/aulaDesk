package com.alcoleagarridofran.auladeskv3.service;

import com.alcoleagarridofran.auladeskv3.dto.AlumnoLoginDTO;
import com.alcoleagarridofran.auladeskv3.dto.ProfesorLoginDTO;
import com.alcoleagarridofran.auladeskv3.model.Alumno;
import com.alcoleagarridofran.auladeskv3.model.Profesor;
import com.alcoleagarridofran.auladeskv3.model.Usuario;
import com.alcoleagarridofran.auladeskv3.repository.IAlumnoRepository;
import com.alcoleagarridofran.auladeskv3.repository.IUsuarioRepository;
import com.alcoleagarridofran.auladeskv3.utils.Rol;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlumnoService {

    final IAlumnoRepository alumnoRepository;
    final IUsuarioRepository usuarioRepository;
    final PasswordEncoder passwordEncoder;
    Alumno alumno;

    // --- C R U D ---

    public Alumno insertarAlumno(AlumnoLoginDTO alumnoDTO) {

        if (usuarioRepository.findByCorreo(alumnoDTO.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }
        if (alumnoRepository.existsById(alumnoDTO.getNre())) {
            throw new RuntimeException("El NRE de Alumno ya existe.");
        }

        // Crear y guardar Usuario
        Usuario user = Usuario.builder()
                .correo(alumnoDTO.getCorreo())
                .contrasenya(passwordEncoder.encode(alumnoDTO.getContrasenya()))
                .rol(Rol.ALUMNO) // 🔑 Rol ALUMNO
                .build();

        Usuario usuarioFinal = usuarioRepository.save(user);

        // Crear y guardar Alumno
        Alumno alumno = new Alumno();
        alumno.setNre(alumnoDTO.getNre());
        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setApellidos(alumnoDTO.getApellidos());
        alumno.setFechaNacimiento(alumnoDTO.getFechaNacimiento()); // Usar la fecha
        alumno.setUsuario(usuarioFinal);

        return alumnoRepository.save(alumno);
    }

    public List<Alumno> obtenerAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno actualizarAlumno(Alumno alumno) {
        // La validación de existencia se haría aquí si el objeto no viniera del CRUD de la UI
        return alumnoRepository.save(alumno);
    }

    @Transactional
    public void eliminarAlumno(Integer nre) {
        // 1. Obtener la ID del Usuario ANTES de la eliminación.
        Integer idUsuario = alumnoRepository.findById(nre)
                .map(Alumno::getUsuario)
                .map(Usuario::getIdUsuario)
                .orElse(null);

        // 2. Eliminar el Alumno por NRE (esto elimina las Matrículas gracias a la cascada).
        alumnoRepository.deleteById(nre);

        // 3. Eliminar el Usuario asociado por su ID.
        if (idUsuario != null) {
            usuarioRepository.deleteById(idUsuario);
        }
    }
}
