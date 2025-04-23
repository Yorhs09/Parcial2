// com.example.Equipo.Repository.JugadorRepository.java
package com.example.Equipo.Repository;

import com.example.Equipo.Model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
