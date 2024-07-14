package com.foroHub.ChallengeForo.topico;

import com.foroHub.ChallengeForo.autor.DatosRegistroUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record DatosRegistroTopico(
                                  @NotBlank
                                  String titulo,
                                  @NotBlank
                                  String mensaje,
                                  @DateTimeFormat
                                  Date fecha,
                                  @NotNull
                                          @Valid
                                  DatosRegistroUsuario autor,
                                  @NotBlank
                                  String status) {
}
