package ar.com.ada.api.boya.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.boya.entities.Boya;

@Repository
public interface BoyaRepository extends JpaRepository<Boya,Integer>{

}
