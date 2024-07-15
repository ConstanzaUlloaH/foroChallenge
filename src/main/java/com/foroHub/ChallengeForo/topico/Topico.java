package com.foroHub.ChallengeForo.topico;

import com.foroHub.ChallengeForo.usuario.Usuario;
import com.foroHub.ChallengeForo.usuario.UsuarioRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String nombre_curso;


    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario usuario){
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha= new Date();
        this.status= "ACTIVO";
        this.usuario = usuario;
        this.nombre_curso = datosRegistroTopico.nombre_curso();
    }

    public void actualizarDatos(DatosActualizacionTopico datosActualizacionTopico) {
        if (datosActualizacionTopico.mensaje() != null) {
            this.mensaje = datosActualizacionTopico.mensaje();
        }

        if (datosActualizacionTopico.titulo() != null) {
            this.titulo = datosActualizacionTopico.titulo();
        }

        if (datosActualizacionTopico.nombre_curso() != null) {
            this.nombre_curso = datosActualizacionTopico.nombre_curso();
        }

        if (datosActualizacionTopico.status() != null) {
            this.nombre_curso = datosActualizacionTopico.status();
        }

    }

    public void desactivarTopico() {
        this.status = "ELIMINADO";
    }
}
