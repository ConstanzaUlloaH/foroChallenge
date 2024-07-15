package com.foroHub.ChallengeForo.topico;

import java.util.Date;

public record DatosListadoTopicos(String titulo, String mensaje, String nombre_curso, Long id, Date fecha, String status) {
    public DatosListadoTopicos(Topico topico){
        this(topico.getMensaje(), topico.getTitulo(),topico.getNombre_curso(), topico.getId(), topico.getFecha(), topico.getStatus());
    }
}
