package com.aluracursos.forohub.domain.dto;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String status,
        Long autor_id,
        Long curso_id
) {
}
