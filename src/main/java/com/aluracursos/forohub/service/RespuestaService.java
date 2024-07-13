package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.dto.*;
import com.aluracursos.forohub.domain.modelos.Respuesta;
import com.aluracursos.forohub.domain.repository.RespuestaRepository;
import com.aluracursos.forohub.domain.repository.TopicoRepository;
import com.aluracursos.forohub.domain.repository.UsuarioRepository;
import com.aluracursos.forohub.infra.excepciones.ObjetoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespuestaService {

    @Autowired
    private TopicoRepository repositorioTopico;

    @Autowired
    private UsuarioRepository repositorioAutor;

    @Autowired
    private RespuestaRepository repositorioRespuesta;

    public DatosRespuesta agregarRespuesta(ResponderPublicacion responderPublicacion) {
        var existeTopico = repositorioTopico.findById(responderPublicacion.topico_id());
        if (existeTopico.isEmpty()) {
            throw new ObjetoInexistenteException("No existe el tópico indicado");
        }
        var existeAutor = repositorioAutor.findById(responderPublicacion.autor_id());
        if (existeAutor.isEmpty()) {
            throw new ObjetoInexistenteException("No existe el autor indicado");
        }
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Respuesta nuevaRespuesta = new Respuesta(responderPublicacion,fechaCreacion,existeTopico.get(),existeAutor.get());
        repositorioRespuesta.save(nuevaRespuesta);
        return new DatosRespuesta(nuevaRespuesta.getId(),nuevaRespuesta.getSolucion(),nuevaRespuesta.getFechaCreacion(),
                nuevaRespuesta.getAutor().getId(),nuevaRespuesta.getTopico().getId());
    }

    public DetalleRespuesta verRespuesta(Long id) {
        var existeRespuesta = repositorioRespuesta.findById(id);
        if (existeRespuesta.isPresent()){
            var respuesta = existeRespuesta.get();
            return new DetalleRespuesta(respuesta.getId(), respuesta.getSolucion(),
                    new DetallesTopico(respuesta.getTopico().getId(),respuesta.getTopico().getTitulo(),respuesta.getTopico().getMensaje(),
                            respuesta.getTopico().getFechaCreacion(),respuesta.getTopico().getStatus(),
                            new DatosUsuario(respuesta.getTopico().getAutor().getId(),respuesta.getTopico().getAutor().getNombreUsuario(),respuesta.getTopico().getAutor().getEmail()),
                            new DatosCurso(respuesta.getTopico().getCurso().getId(),respuesta.getTopico().getCurso().getNombre(),respuesta.getTopico().getCurso().getCategoria())),
                    respuesta.getFechaCreacion());
        }
        throw new ObjetoInexistenteException("No existe la respuesta buscada");
    }

    public Page<DatosRespuesta> listarRespuestas(Pageable paginacion) {
        return repositorioRespuesta.findAll(paginacion)
                .map(DatosRespuesta::new);
    }
}
