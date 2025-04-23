// com.example.Equipo.Repository.PartidoRepository.java
package com.example.Equipo.Repository;

import com.example.Equipo.Model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
}