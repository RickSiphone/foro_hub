package com.aluracursos.forohub.domain.dto;

import jakarta.annotation.Nullable;

public record ActualizarTopico(
        @Nullable String titulo,
        String mensaje,
        @Nullable String status,
        @Nullable Long curso_id
) {
}
