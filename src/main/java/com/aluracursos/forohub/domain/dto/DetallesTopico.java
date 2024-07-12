package com.aluracursos.forohub.domain.dto;

import java.time.LocalDateTime;

public record DetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        DatosUsuario autor,
        DatosCurso curso
) {
}
