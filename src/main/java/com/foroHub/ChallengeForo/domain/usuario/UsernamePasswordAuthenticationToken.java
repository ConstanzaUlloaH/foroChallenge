package com.foroHub.ChallengeForo.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsernamePasswordAuthenticationToken(@Email @NotBlank String email, @NotBlank String clave) {
}
