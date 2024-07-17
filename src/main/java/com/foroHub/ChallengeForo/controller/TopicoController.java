package com.foroHub.ChallengeForo.controller;


import com.foroHub.ChallengeForo.domain.topico.*;
import com.foroHub.ChallengeForo.domain.usuario.Usuario;
import com.foroHub.ChallengeForo.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/foro")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;



    @GetMapping
    public ResponseEntity<List<DatosListadoTopicos>> retornarListadoTopicos() {
        return ResponseEntity.ok(topicoRepository.findAll().stream()
                .filter(topico -> topico.getStatus().equals("ACTIVO"))
                .map(DatosListadoTopicos::new)
                .toList());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){

        if (! usuarioRepository.findById(datosRegistroTopico.idUsuario()).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioRepository.findById(datosRegistroTopico.idUsuario()).get();
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario));
        var datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getUsuario().getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getNombre_curso());

        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id) {

        if (! topicoRepository.findById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoRepository.findById(id).get();

        var datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getUsuario().getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getNombre_curso());

        return ResponseEntity.ok(datosRespuestaTopico);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico) {

        try {
            if (! topicoRepository.findById(datosActualizacionTopico.id()).isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Topico topico = topicoRepository.getReferenceById(datosActualizacionTopico.id());

            topico.actualizarDatos(datosActualizacionTopico);

            var datosRespuestaTopico = new DatosRespuestaTopico(
                    topico.getUsuario().getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getNombre_curso());

            return ResponseEntity.ok(datosRespuestaTopico);

        } catch (Exception e){
            throw new Error(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        if (! topicoRepository.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
