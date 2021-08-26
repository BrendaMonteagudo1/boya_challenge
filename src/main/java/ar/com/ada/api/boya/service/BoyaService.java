package ar.com.ada.api.boya.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.entities.Muestra;
import ar.com.ada.api.boya.repos.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository repo;
  
    public void crearBoya(Boya boya) {
        repo.save(boya);
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

    public Muestra getBoyaPorId(Integer id) {
        Optional<Boya> resultado = repo.findById(id);

        return null;
    }
}


