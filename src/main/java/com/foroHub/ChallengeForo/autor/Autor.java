package com.foroHub.ChallengeForo.autor;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    private String nombre;
    private String email;

    public Autor(DatosRegistroUsuario autor) {
        this.nombre = autor.nombre();
        this.email = autor.email();
    }
}
