package ar.com.ada.api.boya.entities;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "muestra")
public class Muestra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muestra_id")
    private Integer muestraId;

    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;

    private Date horario;

    private Double longitud;

    private Double latitud;
    @Column(name = "matricula_embarcacion")
    private String matriculaEmbarcacion;

    @Column(name = "altura_nivel_mar")
    private Double alturaNivelDelMar;

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud2) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getMatricula() {
        return matriculaEmbarcacion;
    }

    public void setMatricula(String matriculaEmbarcacion) {
        this.matriculaEmbarcacion = matriculaEmbarcacion;
    }

    public Double getAlturaNivelDelMar() {
        return alturaNivelDelMar;
    }

    public void setAlturaNivelDelMar(Double alturaNivelDelMar) {
        this.alturaNivelDelMar = alturaNivelDelMar;
    }

}


