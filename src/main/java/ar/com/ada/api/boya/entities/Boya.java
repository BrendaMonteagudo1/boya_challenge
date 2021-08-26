package ar.com.ada.api.boya.entities;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;


@Entity
@Table(name = "boya")
public class Boya {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "boya_id")
  private Integer boyaId;
  
  @Column(name = "color_luz")
  private ColorLuzEnum colorLuz;

  @Column(name = "longitud_instalacion")
  private Double longitudInstalacion;

  @Column(name = "latitud_instalacion")
  private Double latitudInstalacion;

  @Column(name = "muestra_id")
  private Muestra muestra;
  
  @OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private List<Muestra> muestras = new ArrayList<>();

  public Muestra getMuestra() {
    return muestra;
  }

  public void setMuestra(Muestra muestra) {
    this.muestra = muestra;
    this.muestra.add(this);
  }

  public Integer getBoyaId() {
    return boyaId;
  }

  public void setBoyaId(Integer boyaId) {
    this.boyaId = boyaId;
  }

  public ColorLuzEnum getColorLuz() {
    return colorLuz;
  }

  public void setColorLuz(ColorLuzEnum colorLuz) {
    this.colorLuz = colorLuz;
  }

  public Double getLongitudInstalacion() {
    return longitudInstalacion;
  }

  public void setLongitudInstalacion(Double longitudInstalacion) {
    this.longitudInstalacion = longitudInstalacion;
  }

  public Double getLatitudInstalacion() {
    return latitudInstalacion;
  }

  public void setLatitudInstalacion(Double latitudInstalacion) {
    this.latitudInstalacion = latitudInstalacion;
  }
  
  public enum ColorLuzEnum {
    ROJA(1), AMARILLO(2), VERDE(3), AZUL(4);

    private final Integer value;

    private ColorLuzEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ColorLuzEnum parse(Integer id) {
        ColorLuzEnum status = null; // Default
        for (ColorLuzEnum item : ColorLuzEnum.values()) {
            if (item.getValue().equals(id)) {
                status = item;
                break;
            }
        }
        return status;
    }

}



}
