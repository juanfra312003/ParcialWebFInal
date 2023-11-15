package co.edu.javeriana.parcial.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "ciudad", nullable = false, length = 45)
    private String ciudad;

    @Column(name = "copas_internacionales", nullable = false)
    private Integer copasInternacionales;

}