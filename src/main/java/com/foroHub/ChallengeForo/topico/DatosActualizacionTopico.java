package com.foroHub.ChallengeForo.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico (
        @NotNull
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String nombre_curso,
        @NotBlank
        String status){
}
