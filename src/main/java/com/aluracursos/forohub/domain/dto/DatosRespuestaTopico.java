package com.aluracursos.forohub.domain.dto;

import com.aluracursos.forohub.domain.modelos.Topico;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime fechaCreacion,
        String status,
        Long autor_id,
        Long curso_id
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),topico.getStatus(),topico.getAutor().getId(),
                topico.getCurso().getId());
    }
}
