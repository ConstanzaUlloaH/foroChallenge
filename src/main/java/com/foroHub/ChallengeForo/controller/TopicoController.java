package com.foroHub.ChallengeForo.controller;

import com.foroHub.ChallengeForo.topico.*;
import com.foroHub.ChallengeForo.usuario.Usuario;
import com.foroHub.ChallengeForo.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/foro")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;



    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> retornarListadoTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    @jakarta.transaction.Transactional
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
    @jakarta.transaction.Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        if (! topicoRepository.findById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
