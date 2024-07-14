package com.foroHub.ChallengeForo;

import com.foroHub.ChallengeForo.autor.DatosRegistroUsuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record DatosRespuestaTopico(Long id,
                                   @NotBlank
                                   String topico,
                                   @NotBlank
                                   String mensaje,
                                   @DateTimeFormat
                                   Date fecha,
                                   @NotBlank
                                   DatosRegistroUsuario Autor,
                                   @NotBlank
                                   String respuesta) {
}
