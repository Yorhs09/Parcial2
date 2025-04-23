// com.example.Equipo.Repository.EquipoRepository.java
package com.example.Equipo.Repository;

import com.example.Equipo.Model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
}
