// com.example.Equipo.Controller.EstadisticasController.java
package com.example.Equipo.Controller;

import com.example.Equipo.Model.Estadisticas;
import com.example.Equipo.Service.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    @Autowired
    public EstadisticasController(EstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @PostMapping
    public ResponseEntity<Estadisticas> crearEstadisticas(@RequestBody Estadisticas estadisticas) {
        Estadisticas nuevasEstadisticas = estadisticasService.crearEstadisticas(estadisticas);
        return new ResponseEntity<>(nuevasEstadisticas, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estadisticas> obtenerEstadisticasPorId(@PathVariable Long id) {
        Optional<Estadisticas> estadisticas = estadisticasService.obtenerEstadisticasPorId(id);
        return estadisticas.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Estadisticas>> obtenerTodasLasEstadisticas() {
        List<Estadisticas> estadisticas = estadisticasService.obtenerTodasLasEstadisticas();
        if (estadisticas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(estadisticas, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estadisticas> actualizarEstadisticas(@PathVariable Long id, @RequestBody Estadisticas estadisticasActualizadas) {
        Optional<Estadisticas> estadisticasExistentes = estadisticasService.obtenerEstadisticasPorId(id);
        if (estadisticasExistentes.isPresent()) {
            estadisticasActualizadas.setId(id);
            Estadisticas estadisticasActualizadasResult = estadisticasService.actualizarEstadisticas(estadisticasActualizadas);
            return new ResponseEntity<>(estadisticasActualizadasResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarEstadisticas(@PathVariable Long id) {
        if (estadisticasService.existeEstadisticas(id)) {
            estadisticasService.eliminarEstadisticas(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}