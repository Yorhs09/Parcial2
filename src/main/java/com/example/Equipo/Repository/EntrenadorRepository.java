// com.example.Equipo.Repository.EntrenadorRepository.java
package com.example.Equipo.Repository;

import com.example.Equipo.Model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
}
