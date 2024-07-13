package com.aluracursos.forohub.domain.dto;

import java.time.LocalDateTime;

public record DetalleRespuesta(
        Long id,
        String solucion,
        DetallesTopico topico,
        LocalDateTime fechaCreacion
) {
}
