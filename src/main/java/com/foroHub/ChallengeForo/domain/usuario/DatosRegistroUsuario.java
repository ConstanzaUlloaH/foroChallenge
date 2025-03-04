package com.foroHub.ChallengeForo.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @Email
        @NotBlank
        String email ,
        @NotBlank
        String clave) {
}
