package modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by saleta on 6/24/2016.
 */
@Entity
public class Publicacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Usuario usuario;
    private Double precioPublicacion;
    private Double precioVehiculo;
    private Date fechaInicio;
    private Date fechaFin;
    @OneToOne
    private Tipo tipo;
    @OneToOne
    private Marca marca;
    private String modelo;
    private Integer anio;
    private String transmision;
    private  Integer uso;
    private Integer pasajeros;
    private String combustible;
    private Integer cilindros;
    @Column(columnDefinition = "VARCHAR(300)")
    private String Observaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getPrecioPublicacion() {
        return precioPublicacion;
    }

    public void setPrecioPublicacion(Double precioPublicacion) {
        this.precioPublicacion = precioPublicacion;
    }

    public Double getPrecioVehiculo() {
        return precioVehiculo;
    }

    public void setPrecioVehiculo(Double precioVehiculo) {
        this.precioVehiculo = precioVehiculo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public Integer getUso() {
        return uso;
    }

    public void setUso(Integer uso) {
        this.uso = uso;
    }

    public Integer getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(Integer pasajeros) {
        this.pasajeros = pasajeros;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public Integer getCilindros() {
        return cilindros;
    }

    public void setCilindros(Integer cilindros) {
        this.cilindros = cilindros;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }
}
