package com.foroHub.ChallengeForo.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of= "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String clave;

    public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
        this.clave = datosRegistroUsuario.clave();
        this.email = datosRegistroUsuario.email();
    }
}
