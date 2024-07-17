package com.foroHub.ChallengeForo.domain.topico;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record DatosRegistroTopico(
        @NotNull
        Long idUsuario,
          @NotBlank
          String titulo,
          @NotBlank
          String mensaje,
          @NotBlank
          String nombre_curso) {
}
