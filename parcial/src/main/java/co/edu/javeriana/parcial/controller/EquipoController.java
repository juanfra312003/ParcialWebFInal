package co.edu.javeriana.parcial.controller;

import co.edu.javeriana.parcial.dto.EquipoDto;
import co.edu.javeriana.parcial.entites.Equipo;
import co.edu.javeriana.parcial.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parcial/api/equipo/")
public class EquipoController {
    @Autowired
    EquipoRepository equipoRepository;


    @CrossOrigin
    @GetMapping()
    private ResponseEntity<List<EquipoDto>> getAllEquipos(){
        List <EquipoDto> equipos = equipoRepository.findAll().stream()
                .map(EquipoDto::new)
                .toList();
        return ResponseEntity.ok(equipos);
    }

    @CrossOrigin
    @GetMapping({"{id}"})
    private ResponseEntity <EquipoDto> getEquipoId(@PathVariable Long id){
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        return equipoOptional.map(equipo -> ResponseEntity.ok(new EquipoDto(equipo))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping("create")
    private ResponseEntity<EquipoDto> createEquipo(@RequestBody Equipo equipoDto){
        Equipo equipo = new Equipo();
        equipo.setNombre(equipoDto.getNombre());
        equipo.setCopasInternacionales(equipoDto.getCopasInternacionales());
        equipo.setCiudad(equipoDto.getCiudad());

        equipoRepository.save(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EquipoDto(equipoRepository.save(equipo)));
    }

    @CrossOrigin
    @PutMapping({"update/{id}"})
    private ResponseEntity<EquipoDto> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipoDto){
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        if(equipoOptional.isPresent()){
            Equipo equipo = equipoOptional.get();
            equipo.setNombre(equipoDto.getNombre());
            equipo.setCopasInternacionales(equipoDto.getCopasInternacionales());
            equipo.setCopasInternacionales(equipoDto.getCopasInternacionales());
            equipoRepository.save(equipo);
            return ResponseEntity.ok(new EquipoDto(equipo));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @DeleteMapping({"delete/{id}"})
    private ResponseEntity<Boolean> deleteEquipo(@PathVariable Long id){
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        if(equipoOptional.isPresent()){
            equipoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
