package com.aluracursos.forohub.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ModificarRespuesta(
        @NotBlank(message = "{solucion.obligatorio}") String solución
) {
}
