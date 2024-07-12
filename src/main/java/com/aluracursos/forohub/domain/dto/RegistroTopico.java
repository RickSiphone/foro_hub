package com.aluracursos.forohub.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroTopico(
        @NotBlank(message = "{titulo.obligatorio}") String titulo,
        @NotBlank(message = "{mensaje.obligatorio}") String mensaje,
        //DUDA: Debe pasar el id del autor o toda la información del autor
        @NotNull(message = "{autor.obligatorio}") Long autor_id,
        @NotNull(message = "{curso.obligatorio}") Long curso_id
) {
}
