package com.aluracursos.forohub.service;

import com.aluracursos.forohub.domain.dto.*;
import com.aluracursos.forohub.domain.modelos.Curso;
import com.aluracursos.forohub.domain.modelos.Topico;
import com.aluracursos.forohub.domain.modelos.Usuario;
import com.aluracursos.forohub.domain.repository.CursoRepository;
import com.aluracursos.forohub.domain.repository.TopicoRepository;
import com.aluracursos.forohub.domain.repository.UsuarioRepository;
import com.aluracursos.forohub.domain.validaciones.TopicoExistenteValidacion;
import com.aluracursos.forohub.infra.excepciones.ObjetoInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Autowired
    private CursoRepository repositorioCurso;

    @Autowired
    private TopicoRepository repositorioTopico;

    @Autowired
    private List<TopicoExistenteValidacion> validaciones;

    public DatosRespuestaTopico registrarTopico(RegistroTopico registroTopico) {
        Optional<Usuario> usuario = repositorioUsuario.findById(registroTopico.autor_id());
        if (usuario.isEmpty()) {
            throw new ObjetoInexistenteException("El usuario indicado no existe");
        }
        Optional<Curso> curso = repositorioCurso.findById(registroTopico.curso_id());
        if (curso.isEmpty()) {
            throw new ObjetoInexistenteException("El curso indicado no existe");
        }
        validaciones.forEach(e -> e.validarTopico(registroTopico.titulo(),registroTopico.mensaje()));
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

    public DetallesTopico actualizarTopico(Long id, ActualizarTopico actualizarTopico) {
        var topico = repositorioTopico.findById(id);
        if (topico.isEmpty()) {
            throw new ObjetoInexistenteException("No existe el topico buscado");
        }
        var topicoEncontrado = topico.get();
        if (actualizarTopico.titulo() != null & actualizarTopico.mensaje() != null) {
            validaciones.forEach(e -> e.validarTopico(actualizarTopico.titulo(),actualizarTopico.mensaje()));
            topicoEncontrado.setTitulo(actualizarTopico.titulo());
            topicoEncontrado.setMensaje(actualizarTopico.mensaje());
        } else  {
            if (actualizarTopico.mensaje() != null) {
                validaciones.forEach(e -> e.validarTopico(topicoEncontrado.getTitulo(),actualizarTopico.mensaje()));
                topicoEncontrado.setMensaje(actualizarTopico.mensaje());
            }
            Long id_curso = actualizarTopico.curso_id();
            if (id_curso != null) {
                Curso curso = repositorioCurso.getReferenceById(id_curso);
                topicoEncontrado.setCurso(curso);
            }
            if(actualizarTopico.status() != null) {
                topicoEncontrado.setStatus(actualizarTopico.status());
            }
        }
        repositorioTopico.save(topicoEncontrado);

        return new DetallesTopico(topicoEncontrado.getId(),topicoEncontrado.getTitulo(),topicoEncontrado.getMensaje(),
                topicoEncontrado.getFechaCreacion(),topicoEncontrado.getStatus(),
                new DatosUsuario(topicoEncontrado.getAutor().getId(),topicoEncontrado.getAutor().getNombreUsuario(),
                        topicoEncontrado.getAutor().getEmail()),
                new DatosCurso(topicoEncontrado.getCurso().getId(),topicoEncontrado.getCurso().getNombre(),
                        topicoEncontrado.getCurso().getCategoria()));
    }
}
