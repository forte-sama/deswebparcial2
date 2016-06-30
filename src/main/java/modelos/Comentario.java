package modelos;

import servicios.ComentarioServicios;

import javax.persistence.*;

import java.util.List;

/**
 * Created by Dell_2 on 6/29/2016.
 */
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 500)
    private String cuerpo;
    private String usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    private Publicacion publicacion;
    @ManyToOne(fetch = FetchType.EAGER)
    private Comentario padre;

    public Comentario() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public Comentario getPadre() {
        return padre;
    }

    public void setPadre(Comentario padre) {
        this.padre = padre;
    }

    public boolean tieneRespuestas() {
        return ComentarioServicios.getInstancia().findByPadre(this).size() > 0;
    }

    public List<Comentario> respuestas() {
        return ComentarioServicios.getInstancia().findByPadre(this);
    }
}
