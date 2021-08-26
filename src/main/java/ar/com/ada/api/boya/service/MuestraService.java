package ar.com.ada.api.boya.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.entities.Muestra;
import ar.com.ada.api.boya.entities.Boya.ColorLuzEnum;
import ar.com.ada.api.boya.repos.MuestraRepository;
@Service
public class MuestraService {
    
    @Autowired
    MuestraRepository repo;
    @Autowired
    BoyaService boyaService;
    
    public void crearMuestra(Muestra muestra) {
        repo.save(muestra);
    }

    public List<Muestra> buscarBoya(Integer id) {
        return repo.findByBoyaDeMuestra(id);
    }


    public void bajaLuzBoya(Integer id) {
        
        Muestra muestra = boyaService.getBoyaPorId(id);
        
        Boya boya = boyaService.buscarBoyaId(id);
        boya.setColorLuz(ColorLuzEnum.AZUL);

        repo.save(muestra);
	}

}




