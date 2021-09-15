package ar.com.ada.api.boya.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.entities.Muestra;
import ar.com.ada.api.boya.repos.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository repo;
  
    public Boya crearBoya(Double latitud, Double longitud) {

        Boya boya = new Boya();
        boya.setLatitudInstalacion(latitud);
        boya.setLongitudInstalacion(longitud);
        boya.setColorLuz("azul");

        return repo.save(boya);
    }

    public List<Boya> traerBoyas() {
        return repo.findAll();
    }

    public Boya buscarBoyaId(Integer Id){
        Optional<Boya> resultado = repo.findById(Id);

        if(resultado.isPresent())
            return resultado.get();
        
        return null;
    }

    public void actualizar(Boya boya) {
        repo.save(boya);
    }



    public List<Boya> buscarPorColor(String color) {
        List<Boya> boyasColor = new ArrayList<>();

        for (Boya boya : repo.findAll()) {
            if (boya.getColorLuz().equals(color)) {
                boyasColor.add(boya);
            }

        }
        return boyasColor;
    }

}


