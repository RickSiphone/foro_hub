package com.aluracursos.forohub.domain.dto;

import com.aluracursos.forohub.domain.modelos.Respuesta;
import com.aluracursos.forohub.domain.modelos.Topico;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String solucion,
        LocalDateTime fechaCreacion,
        Long autor_id,
        Long topico_id
) {
    public DatosRespuesta(Respuesta respuesta) {
        this(respuesta.getId(),respuesta.getSolucion(), respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(),
                respuesta.getTopico().getId());
    }
}
