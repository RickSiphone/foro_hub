package com.aluracursos.forohub.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponderPublicacion(
        @NotBlank(message = "{solucion.obligatorio}") String solucion,
        @NotNull(message = "{topico.obligatorio}") Long topico_id,
        @NotNull(message = "{autor.obligatorio}") Long autor_id
) {
}
