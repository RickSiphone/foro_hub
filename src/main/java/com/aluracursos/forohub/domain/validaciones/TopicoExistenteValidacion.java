package com.aluracursos.forohub.domain.validaciones;

import com.aluracursos.forohub.domain.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoExistenteValidacion implements ValidacionDeIntegridadTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validarTopico(String titulo, String mensaje) {
        var existeTopico = topicoRepository.existsByTituloAndMensaje(titulo,mensaje);
        if (existeTopico) {
            throw new RuntimeException("Ya existe un tópico con el mismo titulo y mensaje");
        }
    }
}
