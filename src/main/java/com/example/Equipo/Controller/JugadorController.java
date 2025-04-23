// com.example.Equipo.Controller.JugadorController.java
package com.example.Equipo.Controller;

import com.example.Equipo.Model.Jugador;
import com.example.Equipo.Service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @PostMapping
    public ResponseEntity<Jugador> crearJugador(@RequestBody Jugador jugador) {
        Jugador nuevoJugador = jugadorService.crearJugador(jugador);
        return new ResponseEntity<>(nuevoJugador, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerJugadorPorId(@PathVariable Long id) {
        Optional<Jugador> jugador = jugadorService.obtenerJugadorPorId(id);
        return jugador.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Jugador>> obtenerTodosLosJugadores() {
        List<Jugador> jugadores = jugadorService.obtenerTodosLosJugadores();
        if (jugadores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(jugadores, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jugador> actualizarJugador(@PathVariable Long id, @RequestBody Jugador jugadorActualizado) {
        Optional<Jugador> jugadorExistente = jugadorService.obtenerJugadorPorId(id);
        if (jugadorExistente.isPresent()) {
            jugadorActualizado.setId(id);
            Jugador jugadorActualizadoResult = jugadorService.actualizarJugador(jugadorActualizado);
            return new ResponseEntity<>(jugadorActualizadoResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarJugador(@PathVariable Long id) {
        if (jugadorService.existeJugador(id)) {
            jugadorService.eliminarJugador(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
