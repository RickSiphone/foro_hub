package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.dto.*;
import com.aluracursos.forohub.domain.modelos.Curso;
import com.aluracursos.forohub.domain.modelos.Topico;
import com.aluracursos.forohub.domain.modelos.Usuario;
import com.aluracursos.forohub.domain.repository.CursoRepository;
import com.aluracursos.forohub.domain.repository.TopicoRepository;
import com.aluracursos.forohub.domain.repository.UsuarioRepository;
import com.aluracursos.forohub.infra.excepciones.ObjetoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private CursoRepository repositorioCurso;

    @Autowired
    private TopicoRepository repositorioTopico;

    public DatosRespuestaTopico registrarTopico(RegistroTopico registroTopico) {
        Optional<Usuario> usuario = repositorioUsuario.findById(registroTopico.autor_id());
        if (usuario.isEmpty()) {
            throw new ObjetoInexistenteException("El usuario indicado no existe");
        }
        Optional<Curso> curso = repositorioCurso.findById(registroTopico.curso_id());
        if (curso.isEmpty()) {
            throw new ObjetoInexistenteException("El curso indicado no existe");
        }
        //Al momento de mandar la solicitud para crear un tópico se toma la fecha de ese momento
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Topico nuevoTopico = new Topico(registroTopico,fechaCreacion,usuario.get(),curso.get());
        //Agregar validacion para ver si ya existe un topico con el mismo titulo y mensaje
        repositorioTopico.save(nuevoTopico);
        return new DatosRespuestaTopico(nuevoTopico.getId(),nuevoTopico.getTitulo(),nuevoTopico.getMensaje(),
                nuevoTopico.getFechaCreacion(),nuevoTopico.getStatus(),nuevoTopico.getAutor().getId(),
                nuevoTopico.getCurso().getId());
    }

    public DetallesTopico visitarTopico(Long id) {
        Topico topico = repositorioTopico.getReferenceById(id);
        return new DetallesTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(),
                topico.getFechaCreacion(),topico.getStatus(),
                new DatosUsuario(topico.getAutor().getId(),topico.getAutor().getNombreUsuario(),topico.getAutor().getEmail()),
                new DatosCurso(topico.getCurso().getId(),topico.getCurso().getNombre(),topico.getCurso().getCategoria()));
    }

    public Page<DatosRespuestaTopico> listarTopicos(Pageable paginacion) {
        return repositorioTopico.findAll(paginacion)
                .map(DatosRespuestaTopico::new);
    }
}
