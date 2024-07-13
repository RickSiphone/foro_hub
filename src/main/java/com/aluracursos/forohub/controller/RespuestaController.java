package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.dto.DatosRespuesta;
import com.aluracursos.forohub.domain.dto.ResponderPublicacion;
import com.aluracursos.forohub.domain.repository.RespuestaRepository;
import com.aluracursos.forohub.service.RespuestaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<DatosRespuesta> responderPublicacion(@RequestBody @Valid ResponderPublicacion responderPublicacion,
                                                               UriComponentsBuilder uriComponentsBuilder){
        DatosRespuesta respuestaPublicada =respuestaService.agregarRespuesta(responderPublicacion);
        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuestaPublicada.id()).toUri();
        return ResponseEntity.created(url).body(respuestaPublicada);
    }

    @GetMapping("/{id}")
    public ResponseEntity verRespuestaEspecifica(@PathVariable Long id) {
        var response = respuestaService.verRespuesta(id);
        return ResponseEntity.ok(response);
    }
}
