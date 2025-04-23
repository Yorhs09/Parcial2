// com.example.Equipo.Controller.PartidoController.java
package com.example.Equipo.Controller;

import com.example.Equipo.Model.Partido;
import com.example.Equipo.Service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    @Autowired
    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @PostMapping
    public ResponseEntity<Partido> crearPartido(@RequestBody Partido partido) {
        Partido nuevoPartido = partidoService.crearPartido(partido);
        return new ResponseEntity<>(nuevoPartido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partido> obtenerPartidoPorId(@PathVariable Long id) {
        Optional<Partido> partido = partidoService.obtenerPartidoPorId(id);
        return partido.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Partido>> obtenerTodosLosPartidos() {
        List<Partido> partidos = partidoService.obtenerTodosLosPartidos();
        if (partidos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(partidos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partido> actualizarPartido(@PathVariable Long id, @RequestBody Partido partidoActualizado) {
        Optional<Partido> partidoExistente = partidoService.obtenerPartidoPorId(id);
        if (partidoExistente.isPresent()) {
            partidoActualizado.setId(id);
            Partido partidoActualizadoResult = partidoService.actualizarPartido(partidoActualizado);
            return new ResponseEntity<>(partidoActualizadoResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarPartido(@PathVariable Long id) {
        if (partidoService.existePartido(id)) {
            partidoService.eliminarPartido(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}