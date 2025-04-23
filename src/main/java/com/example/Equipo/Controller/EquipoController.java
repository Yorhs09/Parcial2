// com.example.Equipo.Controller.EquipoController.java
package com.example.Equipo.Controller;

import com.example.Equipo.Model.Equipo;
import com.example.Equipo.Service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    @Autowired
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        Equipo nuevoEquipo = equipoService.crearEquipo(equipo);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        Optional<Equipo> equipo = equipoService.obtenerEquipoPorId(id);
        return equipo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        if (equipos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipoActualizado) {
        Optional<Equipo> equipoExistente = equipoService.obtenerEquipoPorId(id);
        if (equipoExistente.isPresent()) {
            equipoActualizado.setId(id);
            Equipo equipoActualizadoResult = equipoService.actualizarEquipo(equipoActualizado);
            return new ResponseEntity<>(equipoActualizadoResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarEquipo(@PathVariable Long id) {
        if (equipoService.existeEquipo(id)) {
            equipoService.eliminarEquipo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}