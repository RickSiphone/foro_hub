package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.dto.DatosRespuestaTopico;
import com.aluracursos.forohub.domain.dto.DetallesTopico;
import com.aluracursos.forohub.domain.dto.RegistroTopico;
import com.aluracursos.forohub.service.TopicoService;
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
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid RegistroTopico registroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        var topicoCreado = topicoService.registrarTopico(registroTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoCreado.id()).toUri();
        return ResponseEntity.created(url).body(topicoCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity verTopicoEspecifico(@PathVariable Long id) {
        var response = topicoService.visitarTopico(id);
        return ResponseEntity.ok(response);
    }

    //Mostraré todos los datos de usuario y curso o solo los id's?
    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(sort="fechaCreacion",size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicos(paginacion));
    }
}