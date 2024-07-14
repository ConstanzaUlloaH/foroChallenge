package com.foroHub.ChallengeForo.controller;

import com.foroHub.ChallengeForo.topico.DatosRegistroTopico;
import com.foroHub.ChallengeForo.topico.Topico;
import com.foroHub.ChallengeForo.topico.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/foro")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        topicoRepository.save(new Topico(datosRegistroTopico));
    }
}
