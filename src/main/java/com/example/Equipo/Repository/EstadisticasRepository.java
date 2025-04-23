// com.example.Equipo.Repository.EstadisticasRepository.java
package com.example.Equipo.Repository;

import com.example.Equipo.Model.Estadisticas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticasRepository extends JpaRepository<Estadisticas, Long> {
}
