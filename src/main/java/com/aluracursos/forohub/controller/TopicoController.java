package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.dto.ActualizarTopico;
import com.aluracursos.forohub.domain.dto.DatosTopico;
import com.aluracursos.forohub.domain.dto.RegistroTopico;
import com.aluracursos.forohub.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Operation(summary = "Permite registrar un tópico")

    public ResponseEntity<DatosTopico> registrarTopico(@RequestBody @Valid RegistroTopico registroTopico,
                                                       UriComponentsBuilder uriComponentsBuilder){
        var topicoCreado = topicoService.registrarTopico(registroTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoCreado.id()).toUri();
        return ResponseEntity.created(url).body(topicoCreado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Permite ver toda la información de algún tópico en específico")
    public ResponseEntity verTopicoEspecifico(@PathVariable Long id) {
        var response = topicoService.visitarTopico(id);
        return ResponseEntity.ok(response);
    }

    //Mostraré todos los datos de usuario y curso o solo los id's?
    @GetMapping
    @Operation(summary = "Permite ver información general de todos los tópicos disponibles en el foro ordenados del más antiguo al más reciente")
    public ResponseEntity<Page<DatosTopico>> listarTopicos(@PageableDefault(sort="fechaCreacion",size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicos(paginacion));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Permite actualizar la información de un tópico específico. Se puede actualizar el título, mensaje o el curso relacionado al tópico")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid ActualizarTopico actualizarTopico) {
        var response = topicoService.actualizarTopico(id,actualizarTopico);
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Permite borrar un tópico de la base de datos")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoService.borrarTopico(id);
        return ResponseEntity.ok().body("Se ha eliminado con éxito el tópico indicado");
    }
}