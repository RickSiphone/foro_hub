package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.dto.DatosJWT;
import com.aluracursos.forohub.domain.dto.DatosLoginUsuario;
import com.aluracursos.forohub.domain.modelos.Usuario;
import com.aluracursos.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacion", description = "Obtiene un token que le permite al usuario asignado el acceso a los endpoints correspondientes, pero el token expira 2 horas después de haberlo solicitado, por lo que deberá solicitarse otro o solicitar al equipo de Backend que aumente el tiempo de expiración")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosLoginUsuario datosLoginUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosLoginUsuario.nombreUsuario(),datosLoginUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWT(JWTtoken));
    }


}
