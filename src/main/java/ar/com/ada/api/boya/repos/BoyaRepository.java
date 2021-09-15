package ar.com.ada.api.boya.repos;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.boya.entities.Boya;

@Repository
public interface BoyaRepository extends JpaRepository<Boya,Integer>{
    Boya findByBoyaId(Integer id);
    
    List<Boya> findByColorLuz(String colorLuz);
}
