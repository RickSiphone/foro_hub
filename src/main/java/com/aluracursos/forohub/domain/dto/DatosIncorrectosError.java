package com.aluracursos.forohub.domain.dto;

import org.springframework.validation.FieldError;

public record DatosIncorrectosError(
        String campo,
        String error
) {
    public DatosIncorrectosError(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
