package ar.com.ada.api.boya.entities;
import javax.persistence.*;


@Entity
@Table(name = "boya")
public class Boya {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "boya_id")
  private Integer boyaId;
  
  @Column(name = "color_luz")
  private ColorLuzEnum colorLuz;

  @Column(name = "longitud_Instalacion")
  private Double longitudInstalacion;

  @Column(name = "latitud_Instalacion")
  private Double latitudInstalacion;

  private Integer muestra;
  

  public Integer getMuestra() {
    return muestra;
  }

  public void setMuestra(Integer muestra) {
    this.muestra = muestra;
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
