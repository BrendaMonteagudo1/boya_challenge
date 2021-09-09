package ar.com.ada.api.boya.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.boya.entities.Muestra;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra,Integer> {

    Muestra findByMuestraId(Integer id);
}
