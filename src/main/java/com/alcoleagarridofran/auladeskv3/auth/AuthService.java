package com.alcoleagarridofran.auladeskv3.auth;

import com.alcoleagarridofran.auladeskv3.repository.IUsuarioRepository;
import com.alcoleagarridofran.auladeskv3.utils.Rol;
import com.alcoleagarridofran.auladeskv3.jwt.JwtService;
import com.alcoleagarridofran.auladeskv3.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(),loginRequest.getContrasenya()));
        Usuario user = (Usuario) usuarioRepository.findByCorreo(loginRequest.getCorreo()).orElseThrow();

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .rol(user.getRol().toString())
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        Rol rolUsuario = registerRequest.getRol();
        Usuario user = Usuario.builder().correo(registerRequest.getCorreo())
                .contrasenya(passwordEncoder.encode(registerRequest.getContrasenya()))
                .rol(rolUsuario).build();

        usuarioRepository.save(user);

        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
    @Transactional
    public void eliminar(int idUsuario) {
        usuarioRepository.deleteByIdUsuario(idUsuario);
    }
}