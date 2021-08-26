package ar.com.ada.api.boya.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.*;

import ar.com.ada.api.boya.entities.Muestra;
import ar.com.ada.api.boya.models.response.GenericResponse;
import ar.com.ada.api.boya.service.MuestraService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuestraController {
   
    @Autowired
    MuestraService muestraService;
    
    @PostMapping("api/boyas") //Ningun web method devuelve void
    public ResponseEntity<?> crearMuestra(@RequestBody Muestra muestra){
        
        GenericResponse respuesta = new GenericResponse();

        
        muestraService.crearMuestra(muestra); 

        respuesta.isOk = true;
        respuesta.id = muestra.getMuestraId();
        respuesta.message = "muestra creada";

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("api/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> getMuestraPorBoya(@PathVariable Integer id){
        List<Muestra> muestra = muestraService.buscarBoya(id);
        return ResponseEntity.ok(muestra);
    }

    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<?> bajaLuzBoya(@PathVariable Integer id){

        muestraService.bajaLuzBoya(id);

        GenericResponse respuesta = new GenericResponse();

        respuesta.isOk = true;
        respuesta.message = "Luz actualizada";

        return ResponseEntity.ok(respuesta);

    }
}
