package com.foroHub.ChallengeForo.controller;

import com.foroHub.ChallengeForo.usuario.DatosRegistroUsuario;
import com.foroHub.ChallengeForo.usuario.Usuario;
import com.foroHub.ChallengeForo.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){

        try {
            usuarioRepository.save(new Usuario(datosRegistroUsuario));
        } catch (Exception e){
            System.out.println("ERRORR " + e.getMessage());
        }

        return ResponseEntity.ok( "Usuario creado con exito");
    }
}
