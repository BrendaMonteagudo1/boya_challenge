package ar.com.ada.api.boya.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.entities.Muestra;

import ar.com.ada.api.boya.models.response.MuestraPorColorResponse;
import ar.com.ada.api.boya.repos.MuestraRepository;

@Service
public class MuestraService {

    @Autowired
    MuestraRepository repo;
    @Autowired
    BoyaService boyaService;

    public Muestra crearMuestra(Integer boyaId, Date horario, String matricula, Double latitud, Double longitud,
            Double alturaNivelDelMar) {

        Muestra muestra = new Muestra();

        muestra.setHorario(horario);
        muestra.setMatricula(matricula);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setAlturaNivelDelMar(alturaNivelDelMar);

        Boya boya = boyaService.buscarBoyaId(boyaId);

        if (alturaNivelDelMar < -100 || alturaNivelDelMar > 100) {
            boya.setColorLuz("rojo");
        } else if (alturaNivelDelMar < -50 || alturaNivelDelMar > 50) {
            boya.setColorLuz("amarillo");
        } else {
            boya.setColorLuz("verde");
        }

        boya.agregarMuestra(muestra);

        return repo.save(muestra);

    }

    public Muestra muestraPorId(Integer id) {
        return repo.findByMuestraId(id);
    }

    public void setColorAzul(Muestra muestra) {
        muestra.getBoya().setColorLuz("azul");
        repo.save(muestra);
    }

    public void borrar(Muestra muestra) {
        repo.delete(muestra);
    }

    public List<Muestra> buscarMuestras(Integer idBoya) {
        Boya boya = boyaService.buscarBoyaId(idBoya);
        return boya.getMuestras();
    }

    public List<Muestra> buscarMuestras2(Integer idBoya) {
        Boya boya = boyaService.buscarBoyaId(idBoya);
        if (boya!=null){
            return boya.getMuestras();
        }
        else 
            return null;
 
    }
    

    public String colorMuestra(Double alturaNivelDelMar) {

        if (alturaNivelDelMar < -100 || alturaNivelDelMar > 100) {
            return ("rojo");
        } else if (alturaNivelDelMar < -50 || alturaNivelDelMar > 50) {
            return ("amarillo");
        } else {
            return ("verde");
        }
    }

    public List<MuestraPorColorResponse> buscarMuestrasPorColor(String color) {

        List<MuestraPorColorResponse> muestrasPorColor = new ArrayList<>();
        // List<Boya> boyasColor = boyaService.buscarPorColor(color);

        for (Muestra muestra : repo.findAll()) {
            MuestraPorColorResponse muestraPorColor = new MuestraPorColorResponse();

            if (colorMuestra(muestra.getAlturaNivelDelMar()).equals(color.toLowerCase())) {

                muestraPorColor.boyaId = muestra.getBoya().getBoyaId();
                muestraPorColor.horario = muestra.getHorario();
                muestraPorColor.alturaNivelDelMar = muestra.getAlturaNivelDelMar();

                muestrasPorColor.add(muestraPorColor);// add(muestraPorColor);
            }
        }
        return muestrasPorColor;

    }

    public Muestra buscarMuestraMinima(Integer boyaId) {

        Boya boya = boyaService.buscarBoyaId(boyaId);

        List<Muestra> muestras = boya.getMuestras();

        Muestra muestraMinima = muestras.stream().min(Comparator.comparing(Muestra::getAlturaNivelDelMar))
                .orElseThrow(NoSuchElementException::new); // usar en el punto de buscar lista de boyas por color, que
                                                           // la variable sea de tipo lista?

        return muestraMinima;

    }
    // https://www.baeldung.com/java-collection-min-max <--- Explicacion
    // equalsIgnoreCase (no case sensitive) .toLowerCase

    public boolean resetearColorBoyaMuestra(Integer muestraId) {
        Muestra muestra = repo.findByMuestraId(muestraId);
        /*
         * Integer boyaId = muestra.getBoya().getBoyaId(); Boya boya =
         * boyaService.buscarBoya(boyaId);
         */
        if (muestra != null) {
            Boya boya = muestra.getBoya();
            boya.setColorLuz("AZUL");
            boyaService.guardarBoya(boya);
            return true;
        } else
            return false;

    }
}
