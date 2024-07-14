package com.foroHub.ChallengeForo.topico;

import com.foroHub.ChallengeForo.autor.Autor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private Date fecha;
    private String status;
    @Embedded
    private Autor autor;
//    private String curso;
//    private String respuesta;


    public Topico(DatosRegistroTopico datosRegistroTopico){
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha= datosRegistroTopico.fecha();
        this.status= datosRegistroTopico.status();
        this.autor = new Autor(datosRegistroTopico.autor());
//        this.curso = datosRegistroTopico.curso;
//        this.respuesta=datosRegistroTopico.respuesta;

    }
}
