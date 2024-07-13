package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.modelos.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String api_secret;
    public String generarToken(Usuario usuario){
        var expiracion = generarFechaExpiracion(2,"-06:00");
        try {
            Algorithm algorithm = Algorithm.HMAC256(api_secret);
            return JWT.create()
                    .withIssuer("foro_hub")
                    .withSubject(usuario.getUsername())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(expiracion)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generarFechaExpiracion(long tiempoExpiracion, String zonaHoraria){
        return LocalDateTime.now().plusHours(tiempoExpiracion).toInstant(ZoneOffset.of(zonaHoraria));
    }
}
