package ar.com.ada.api.boya.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.models.request.LuzBoyaResquest;
import ar.com.ada.api.boya.models.response.GenericResponse;
import ar.com.ada.api.boya.service.BoyaService;

@RestController
public class BoyaController {

    @Autowired
    BoyaService boyaService;

    @PostMapping("api/boyas")
    public ResponseEntity<?> crearBoya(@RequestBody Boya boya) {

        GenericResponse respuesta = new GenericResponse();

        Boya nueva = boyaService.crearBoya(boya.getLatitudInstalacion(), boya.getLongitudInstalacion());

        respuesta.id = nueva.getBoyaId();
        respuesta.isOk = true;
        respuesta.message = "La boya ha sido creada con exito";

        return ResponseEntity.ok(respuesta);

    }

    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> traerBoyas() {
        List<Boya> lista = boyaService.traerBoyas();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("api/boyas/{idBoya}")
    public ResponseEntity<Boya> getBoyaPorId(@PathVariable Integer id) {
        Boya boya = boyaService.buscarBoyaId(id);
        if (boya == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boya);
    }

    @PutMapping("/api/boyas/{id}/estados")
    public ResponseEntity<GenericResponse> putActualizarColorBoya(@PathVariable Integer id,
            @RequestBody LuzBoyaResquest estadoColorLuz) {

        GenericResponse r = new GenericResponse();

        Boya boya = boyaService.buscarBoyaId(id);

        boya.setColorLuz(estadoColorLuz.estado);

        boyaService.actualizar(boya);

        r.isOk = true;
        r.message = "Luz actualizada";
        return ResponseEntity.ok(r);
    }
}
