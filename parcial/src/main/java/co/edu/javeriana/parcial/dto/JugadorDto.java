package co.edu.javeriana.parcial.dto;

import co.edu.javeriana.parcial.entites.Equipo;
import co.edu.javeriana.parcial.entites.Jugador;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link co.edu.javeriana.parcial.entites.Jugador}
 */
@Getter
@Setter
public class JugadorDto  {
    private Integer id;
    private String nombre;
    private String apellidos;
    private Integer numero;
    private Long idEquipo;

    public JugadorDto() {
        // Constructor vacío
    }

    public JugadorDto(Jugador jugador){
        this.id = jugador.getId();
        this.nombre = jugador.getNombre();
        this.apellidos = jugador.getApellidos();
        this.numero = jugador.getNumero();
        this.idEquipo = Long.valueOf(jugador.getIdEquipo().getId());
    }
}