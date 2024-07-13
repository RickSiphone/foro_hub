package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.dto.DatosRespuesta;
import com.aluracursos.forohub.domain.dto.ModificarRespuesta;
import com.aluracursos.forohub.domain.dto.ResponderPublicacion;
import com.aluracursos.forohub.domain.repository.RespuestaRepository;
import com.aluracursos.forohub.service.RespuestaService;
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
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    private RespuestaRepository repositorioRespuesta;

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    @Operation(summary = "Permite agregar una respuesta a algún tópico en específico")
    public ResponseEntity<DatosRespuesta> responderPublicacion(@RequestBody @Valid ResponderPublicacion responderPublicacion,
                                                               UriComponentsBuilder uriComponentsBuilder){
        DatosRespuesta respuestaPublicada =respuestaService.agregarRespuesta(responderPublicacion);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuestaPublicada.id()).toUri();
        return ResponseEntity.created(url).body(respuestaPublicada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Permite ver información detallada de una respuesta y la publicación a la que respondió")
    public ResponseEntity verRespuestaEspecifica(@PathVariable Long id) {
        var response = respuestaService.verRespuesta(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Permite ver todas las respuestas que se encuentran en el foro")
    public ResponseEntity<Page<DatosRespuesta>> listarRespuestas(@PageableDefault(sort="fechaCreacion",size = 10) Pageable paginacion){
        return ResponseEntity.ok(respuestaService.listarRespuestas(paginacion));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Permite actualizar el contenido de la respuesta publicada")
    public ResponseEntity actualizarRespuesta(@PathVariable Long id, @RequestBody @Valid ModificarRespuesta modificarRespuesta) {
        var response = respuestaService.modificarRespuesta(id,modificarRespuesta);
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.ok().body("Se ha eliminado la respuesta solicitada");
    }

}
