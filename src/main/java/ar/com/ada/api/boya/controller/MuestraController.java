package ar.com.ada.api.boya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

import ar.com.ada.api.boya.entities.Muestra;
import ar.com.ada.api.boya.models.request.MuestraRequest;
import ar.com.ada.api.boya.models.response.GenericResponse;
import ar.com.ada.api.boya.models.response.MuestraMinimaResponse;
import ar.com.ada.api.boya.models.response.MuestraPorColorResponse;
import ar.com.ada.api.boya.service.MuestraService;
import ar.com.ada.api.boya.models.response.BadResponse;
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
        respuesta.color = muestra.getBoya().getColorLuz();
        respuesta.message = "muestra creada";

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> getMuestraDeBoya(@PathVariable Integer idBoya) {

        return ResponseEntity.ok(muestraService.buscarMuestras(idBoya));
    }

    // OPCION DOS DE DELETE
    @DeleteMapping("api/muestras/{id}")
    public ResponseEntity<?> resetarColorBoyaMuestra(@PathVariable Integer id) {
        GenericResponse respuesta = new GenericResponse();
        BadResponse badRespuesta = new BadResponse();
        if (muestraService.resetearColorBoyaMuestra(id)) {// reseteo y a la vez devuelvo true o false
            respuesta.id = id;
            respuesta.isOk = true;
            respuesta.message = "Color de boya resetado a azul con éxito";

            return ResponseEntity.ok(respuesta);
        } else {
            badRespuesta.isOk = false;
            badRespuesta.message = "El id de muestra no existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(badRespuesta);
        }

    }

    // OPCION 1 DE DELETE
    @DeleteMapping("/muestra/{id}")
    public ResponseEntity<GenericResponse> borrarMuestra(@PathVariable Integer id) {
        GenericResponse respuesta = new GenericResponse();

        Muestra muestra = muestraService.muestraPorId(id); //
        muestraService.setColorAzul(muestra);

        respuesta.isOk = true;
        respuesta.message = "Muestra borrada";
        return ResponseEntity.ok(respuesta);

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

    // GET: devuelve la lista de muestras de una boya, indicado por "idBoya"
    @GetMapping("api/muestras/boyas/{idBoya}")
    public ResponseEntity<?> traerMuestras(@PathVariable Integer idBoya) {
        GenericResponse respuesta = new GenericResponse();
        if (muestraService.buscarMuestras(idBoya) != null) {
            return ResponseEntity.ok(muestraService.buscarMuestras(idBoya));
        } else {
            respuesta.isOk = false;
            respuesta.message = "No existe id ingresado";
            return ResponseEntity.badRequest().body(respuesta);
        }

    }

}
