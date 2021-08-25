package ar.com.ada.api.boya.entities;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "muestra")
public class Muestra {
    
private Integer muestraId;

private Integer boyaId;

private Date horarioMuestra;

private String matriculaEmbarcacion;

public Integer getMuestraId() {
    return muestraId;
}

public void setMuestraId(Integer muestraId) {
    this.muestraId = muestraId;
}

public Integer getBoyaId() {
    return boyaId;
}

public void setBoyaId(Integer boyaId) {
    this.boyaId = boyaId;
}

public Date getHorarioMuestra() {
    return horarioMuestra;
}

public void setHorarioMuestra(Date horarioMuestra) {
    this.horarioMuestra = horarioMuestra;
}

public String getMatriculaEmbarcacion() {
    return matriculaEmbarcacion;
}

public void setMatriculaEmbarcacion(String matriculaEmbarcacion) {
    this.matriculaEmbarcacion = matriculaEmbarcacion;
}

public Double getLongitud() {
    return longitud;
}

public void setLongitud(Double longitud) {
    this.longitud = longitud;
}

public Double getLatitud() {
    return latitud;
}

public void setLatitud(Double latitud) {
    this.latitud = latitud;
}

public Double getAlturaNivelMar() {
    return alturaNivelMar;
}

public void setAlturaNivelMar(Double alturaNivelMar) {
    this.alturaNivelMar = alturaNivelMar;
}

private Double longitud;

private Double latitud;

private Double alturaNivelMar;


}
