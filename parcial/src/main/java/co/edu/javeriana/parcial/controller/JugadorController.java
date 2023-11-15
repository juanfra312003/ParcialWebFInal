package co.edu.javeriana.parcial.controller;

import co.edu.javeriana.parcial.dto.EquipoDto;
import co.edu.javeriana.parcial.dto.JugadorDto;
import co.edu.javeriana.parcial.entites.Equipo;
import co.edu.javeriana.parcial.entites.Jugador;
import co.edu.javeriana.parcial.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.javeriana.parcial.repository.JugadorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parcial/api/jugador/")
public class JugadorController {
    @Autowired
    JugadorRepository jugadorRepository;

    @Autowired
    EquipoRepository equipoRepository;

    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<JugadorDto>> getAllJugadores(){
        List <JugadorDto> jugadores = jugadorRepository.findAll().stream()
                .map(JugadorDto::new)
                .toList();
        return ResponseEntity.ok(jugadores);
    }

    @CrossOrigin
    @GetMapping({"{id}"})
    private ResponseEntity <JugadorDto> getJugadorID(@PathVariable Long id){
        Optional<Jugador> jugadorOptional = jugadorRepository.findById(id);
        return jugadorOptional.map(equipo -> ResponseEntity.ok(new JugadorDto(equipo))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping("create")
    private ResponseEntity<JugadorDto> createJugador(@RequestBody Jugador jugadorDTO){
        Optional<Equipo> equipoOptional = equipoRepository.findById(Long.valueOf(jugadorDTO.getIdEquipo().getId()));
        if (equipoOptional.isPresent()){
            return ResponseEntity.ok(new JugadorDto(jugadorRepository.save(jugadorDTO)));
        }
        return ResponseEntity.notFound().build();

    }


    @CrossOrigin
    @PutMapping({"update/{id}"})
    private ResponseEntity<JugadorDto> updateJugador(@PathVariable Long id, @RequestBody Jugador jugadorDto){
        Optional<Jugador> jugadorOptional = jugadorRepository.findById(id);
        if(jugadorOptional.isPresent()){
            Optional<Equipo> equipoOptional = equipoRepository.findById(Long.valueOf(jugadorDto.getIdEquipo().getId()));
            if (equipoOptional.isPresent()){
                return ResponseEntity.ok(new JugadorDto(jugadorRepository.save(jugadorDto)));
            }
        }else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @DeleteMapping({"delete/{id}"})
    private ResponseEntity<Boolean> deleteJugador(@PathVariable Long id){
        Optional<Jugador> jugadorOptional = jugadorRepository.findById(id);
        if(jugadorOptional.isPresent()){
            jugadorRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
