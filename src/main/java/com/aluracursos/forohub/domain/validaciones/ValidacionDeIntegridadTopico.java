package com.aluracursos.forohub.domain.validaciones;

import com.aluracursos.forohub.domain.dto.RegistroTopico;

public interface ValidacionDeIntegridadTopico {
    public void validarTopico(String titulo, String mensaje);
}
