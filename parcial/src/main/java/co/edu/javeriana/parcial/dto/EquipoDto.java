package co.edu.javeriana.parcial.dto;

import co.edu.javeriana.parcial.entites.Equipo;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link co.edu.javeriana.parcial.entites.Equipo}
 */
@Getter
@Setter
public class EquipoDto {
    private Integer id;
    private String nombre;
    private String ciudad;
    private Integer copasInternacionales;

    public EquipoDto() {
        // Constructor vacío
    }

    public EquipoDto (Equipo equipo){
        this.id = equipo.getId();
        this.nombre = equipo.getNombre();
        this.ciudad = equipo.getCiudad();
        this.copasInternacionales = equipo.getCopasInternacionales();
    }
}