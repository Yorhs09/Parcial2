// com.example.Equipo.Controller.EntrenadorController.java
package com.example.Equipo.Controller;

import com.example.Equipo.Model.Entrenador;
import com.example.Equipo.Service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrenadores")
public class EntrenadorController {

    private final EntrenadorService entrenadorService;

    @Autowired
    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public ResponseEntity<Entrenador> crearEntrenador(@RequestBody Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorService.crearEntrenador(entrenador);
        return new ResponseEntity<>(nuevoEntrenador, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable Long id) {
        Optional<Entrenador> entrenador = entrenadorService.obtenerEntrenadorPorId(id);
        return entrenador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Entrenador>> obtenerTodosLosEntrenadores() {
        List<Entrenador> entrenadores = entrenadorService.obtenerTodosLosEntrenadores();
        if (entrenadores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(entrenadores, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable Long id, @RequestBody Entrenador entrenadorActualizado) {
        Optional<Entrenador> entrenadorExistente = entrenadorService.obtenerEntrenadorPorId(id);
        if (entrenadorExistente.isPresent()) {
            entrenadorActualizado.setId(id);
            Entrenador entrenadorActualizadoResult = entrenadorService.actualizarEntrenador(entrenadorActualizado);
            return new ResponseEntity<>(entrenadorActualizadoResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarEntrenador(@PathVariable Long id) {
        if (entrenadorService.existeEntrenador(id)) {
            entrenadorService.eliminarEntrenador(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
