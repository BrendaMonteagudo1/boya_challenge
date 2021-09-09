package ar.com.ada.api.boya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.*;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.entities.Muestra;
import ar.com.ada.api.boya.models.request.MuestraRequest;
import ar.com.ada.api.boya.models.response.GenericResponse;
import ar.com.ada.api.boya.models.response.MuestraMinimaResponse;
import ar.com.ada.api.boya.models.response.MuestraPorColorResponse;
import ar.com.ada.api.boya.service.MuestraService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuestraController {

    @Autowired
    MuestraService muestraService;

    @PostMapping("api/muestras") // Ningun web method devuelve void
    public ResponseEntity<?> crearMuestra(@RequestBody MuestraRequest request) {

        GenericResponse respuesta = new GenericResponse();

        Muestra muestra = muestraService.crearMuestra(request.boyaId, request.horario, request.matricula,
                request.latitud, request.longitud, request.alturaNivelDelMar);

        respuesta.isOk = true;
        respuesta.id = muestra.getMuestraId();
        respuesta.color = getBoyaId().getColorLuz();
        respuesta.message = "muestra creada";

        return ResponseEntity.ok(respuesta);

    }

    private Boya getBoyaId() {
        return null;
    }

    @GetMapping("api/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> getMuestraPorBoya(@PathVariable Integer id) {
        List<Muestra> muestra = muestraService.buscarBoyaId(id);
        return ResponseEntity.ok(muestra);
    }

   /* @DeleteMapping("/muestras/{id}")
    public ResponseEntity<?> bajaLuzBoya(@PathVariable Integer id) {

        muestraService.bajaLuzBoya(id);

        GenericResponse respuesta = new GenericResponse();

        respuesta.isOk = true;
        respuesta.message = "Luz actualizada";

        return ResponseEntity.ok(respuesta);

    }*/



   @DeleteMapping("/muestra/{id}")
    public ResponseEntity<GenericResponse> borrarMuestra(@PathVariable Integer id) {

        GenericResponse r = new GenericResponse();

        Muestra muestra = muestraService.buscarBoyaId(id); /// areglar error!
        muestraService.setColorAzul(muestra);

        r.isOk = true;
        r.message = "Muestra borrada";
        return ResponseEntity.ok(r);

    }

    @GetMapping("/muestras/colores/{color}")
    public ResponseEntity<List<MuestraPorColorResponse>> traerMuestrasPorColor(@PathVariable String color) {
        return ResponseEntity.ok(muestraService.buscarMuestrasPorColor(color));
    }

    @GetMapping("/muestras/minima/{idBoya}")
    public ResponseEntity<?> getMuestraMinBoya(@PathVariable Integer idBoya) {

        MuestraMinimaResponse response = new MuestraMinimaResponse();

        Muestra muestra = muestraService.buscarMuestraMinima(idBoya);

        response.color = muestra.getBoya().getColorLuz();
        response.alturaNivelDelMarMinima = muestra.getAlturaNivelDelMar();
        response.horario = muestra.getHorario();

        return ResponseEntity.ok(response);

    }

}
