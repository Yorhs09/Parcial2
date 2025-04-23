// com.example.Equipo.Service.EstadisticasService.java
package com.example.Equipo.Service;

import com.example.Equipo.Model.Estadisticas;
import com.example.Equipo.Repository.EstadisticasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadisticasService {

    private final EstadisticasRepository estadisticasRepository;

    @Autowired
    public EstadisticasService(EstadisticasRepository estadisticasRepository) {
        this.estadisticasRepository = estadisticasRepository;
    }

    public Estadisticas crearEstadisticas(Estadisticas estadisticas) {
        return estadisticasRepository.save(estadisticas);
    }

    public Optional<Estadisticas> obtenerEstadisticasPorId(Long id) {
        return estadisticasRepository.findById(id);
    }

    public List<Estadisticas> obtenerTodasLasEstadisticas() {
        return estadisticasRepository.findAll();
    }

    public Estadisticas actualizarEstadisticas(Estadisticas estadisticas) {
        return estadisticasRepository.save(estadisticas);
    }

    public void eliminarEstadisticas(Long id) {
        estadisticasRepository.deleteById(id);
    }

    public boolean existeEstadisticas(Long id) {
        return estadisticasRepository.existsById(id);
    }
}
