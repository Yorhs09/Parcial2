// com.example.Equipo.Service.EntrenadorService.java
package com.example.Equipo.Service;

import com.example.Equipo.Model.Entrenador;
import com.example.Equipo.Repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    @Autowired
    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public Entrenador crearEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Optional<Entrenador> obtenerEntrenadorPorId(Long id) {
        return entrenadorRepository.findById(id);
    }

    public List<Entrenador> obtenerTodosLosEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public Entrenador actualizarEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public void eliminarEntrenador(Long id) {
        entrenadorRepository.deleteById(id);
    }

    public boolean existeEntrenador(Long id) {
        return entrenadorRepository.existsById(id);
    }
}