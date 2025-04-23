package com.example.Equipo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_equipo;
    private String nombre;
    private String ciudad;
    private LocalDate fundacion;

    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadores;

    @ManyToMany
    @JoinTable(
            name = "equipo_entrenador",
            joinColumns = @JoinColumn(name = "equipo_id"),
            inverseJoinColumns = @JoinColumn(name = "entrenador_id")
    )
    private List<Entrenador> entrenadores;

    @OneToMany(mappedBy = "equipoLocal")
    private List<Partido> partidosLocal;

    @OneToMany(mappedBy = "equipoVisitante")
    private List<Partido> partidosVisitante;

    public void setId(Long id) {

    }
}