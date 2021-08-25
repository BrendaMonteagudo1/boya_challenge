package ar.com.ada.api.boya.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.models.request.LuzBoyaResquest;
import ar.com.ada.api.boya.models.response.GenericResponse;
import ar.com.ada.api.boya.service.BoyaService;

public class BoyaController {
    
    @Autowired
    BoyaService boyaService;
    

    @PostMapping("api/boyas") //Ningun web method devuelve void
    public ResponseEntity<?> crearBoya(@RequestBody Boya boya){
        
        GenericResponse respuesta = new GenericResponse();

        
        boyaService.crearBoya(boya); 

        respuesta.isOk = true;
        respuesta.id = boya.getBoyaId();
        respuesta.message = "Boya creada";

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> traerBoyas() {
        List<Boya> lista = boyaService.traerBoyas();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("api/boyas/{id}")
    public ResponseEntity<Boya> getBoyaPorId(@PathVariable Integer id){
        Boya boya = boyaService.buscarBoyaId(id);

        return ResponseEntity.ok(boya);
    }

    
    @PutMapping("/api/vuelos/{id}/estados")
    public ResponseEntity<GenericResponse> putActualizarEstadoVuelo(@PathVariable Integer id,
            @RequestBody LuzBoyaResquest estadoColorLuz) {

        GenericResponse r = new GenericResponse();
        r.isOk = true;
      
        Boya boya = boyaService.buscarBoyaId(id);
       
        boya.setColorLuz(estadoColorLuz.estado);
        
        boyaService.actualizar(boya);
        
      
        r.message = "actualizado";
        return ResponseEntity.ok(r);
    }
}

