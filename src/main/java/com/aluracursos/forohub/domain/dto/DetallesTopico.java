package com.aluracursos.forohub.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime fechaCreacion,
        String status,
        DatosUsuario autor,
        DatosCurso curso
) {
}
