package com.aluracursos.forohub.infra.errores;

import com.aluracursos.forohub.domain.dto.DatosIncorrectosError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream()
                .map(DatosIncorrectosError::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErrorRutaInexistente(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity tratarErrorBodyIncorrecto(){
        return ResponseEntity.badRequest().body("Debes enviar mínimo el mensaje para poder modificar el tópico");
    }
}
