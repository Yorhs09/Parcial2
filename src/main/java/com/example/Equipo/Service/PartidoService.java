// com.example.Equipo.Service.PartidoService.java
package com.example.Equipo.Service;

import com.example.Equipo.Model.Partido;
import com.example.Equipo.Repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;

    @Autowired
    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

    public Partido crearPartido(Partido partido) {
        return partidoRepository.save(partido);
    }

    public Optional<Partido> obtenerPartidoPorId(Long id) {
        return partidoRepository.findById(id);
    }

    public List<Partido> obtenerTodosLosPartidos() {
        return partidoRepository.findAll();
    }

    public Partido actualizarPartido(Partido partido) {
        return partidoRepository.save(partido);
    }

    public void eliminarPartido(Long id) {
        partidoRepository.deleteById(id);
    }

    public boolean existePartido(Long id) {
        return partidoRepository.existsById(id);
    }
}
