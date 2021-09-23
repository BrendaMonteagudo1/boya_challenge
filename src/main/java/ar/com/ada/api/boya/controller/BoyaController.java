package ar.com.ada.api.boya.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.boya.entities.Boya;
import ar.com.ada.api.boya.models.request.LuzBoyaResquest;
import ar.com.ada.api.boya.models.response.BadResponse;
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

    // GET: Devuelve las boyas SIN las muestras
    @GetMapping("api/boyas")
    public ResponseEntity<List<Boya>> traerBoyas() {
        List<Boya> lista = boyaService.traerBoyas();

        return ResponseEntity.ok(lista);
    }

    //DEVUELVE UNA BOYA SIN MUESTRAS
    @GetMapping("api/boyas/{idBoya}")
    public ResponseEntity<?> getBoyaPorId(@PathVariable Integer id) {
        Boya boya = boyaService.buscarBoyaId(id);
        if (boya == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boya);
    }

    //OPCION DOS PARA TRAER UNA BOTA SIN MUESTRA
    @GetMapping("api/boyas/{id}")
    public ResponseEntity<?> buscarBoya2(@PathVariable Integer id){
        BadResponse respuesta= new BadResponse();
        if(boyaService.existeBoya(id)){
            Boya boya = boyaService.buscarBoyaId(id);
            return ResponseEntity.ok(boya);
        }
        else{
            respuesta.isOk=false;
            respuesta.message="El id ingresado no éxiste";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
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

    // opcion 2 de put
    @PutMapping("api/boyas/{id}")
    public ResponseEntity<?> actualizarColorBoya2(@PathVariable Integer id,
            @RequestBody LuzBoyaResquest estadoColorLuz) {
        GenericResponse respuesta = new GenericResponse();
        BadResponse badRespuesta = new BadResponse();

        if (boyaService.actualizarColorBoya(id, estadoColorLuz.estado)) {
            respuesta.isOk = true;
            respuesta.message = "Color boya actualizada con éxito";

            return ResponseEntity.ok(respuesta);
        } else {
            badRespuesta.isOk = false;
            badRespuesta.message = "El id ingresado no existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(badRespuesta);
        }

    }

}
