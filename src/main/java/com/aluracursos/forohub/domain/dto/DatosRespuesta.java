package com.aluracursos.forohub.domain.dto;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String solucion,
        LocalDateTime fechaCreacion,
        Long autor_id,
        Long topico_id
) {
}
