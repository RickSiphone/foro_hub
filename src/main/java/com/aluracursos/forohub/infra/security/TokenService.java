package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.modelos.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String api_secret;

    //Al utilizarse una autenticación STATELESS en este punto el usuario ya fue verificado en la base de datos, por lo que siempre se va a generar
    //un token tomando como argumento una contraseña fija que es api_secret
    //Lo que aumenta la "seguridad" podría ser generar una api_secret para cada usuario tomando una api_secret fija y combinarla o combinar la info del usuario
    //para poder generarla. Esto solo evitaría que alguien externo conozca la contraseña pivote para generar un token pero al realizar esto
    //puede resultar más conveniente implementar otro tipo de autorización
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

    public String getSubject(String token) {
        if (token == null) {
            throw  new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(api_secret);
            verifier = JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("foro_hub")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier inválido");
        }
        return verifier.getSubject();
    }
}
