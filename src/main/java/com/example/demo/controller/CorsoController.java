package com.example.demo.controller;

import com.example.demo.converter.Converter;
import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;

    @Autowired
    Converter converter;


    // LISTA
    @GetMapping("/lista")
    public ResponseEntity<List<CorsoDTO>> list(@RequestParam(name = "filtro", required = false) String filtro) {
        List<Corso> corsi = new ArrayList<>();

        if ("asc".equalsIgnoreCase(filtro)) {
            corsi = corsoService.ordina_by_nome_asc();
        } else if ("desc".equalsIgnoreCase(filtro)){
            corsi = corsoService.ordina_by_nome_desc();
        } else {
            corsi = corsoService.findAll();
        }

        return ResponseEntity.ok(converter.corso_convert_to_dto(corsi));

    }


    // POST - nuovo
    @PostMapping("/new")
    public ResponseEntity<CorsoDTO> showAdd(@RequestBody CorsoDTO corso) {

        // converto
        Corso nuovo = converter.corso_convert_to_entity(corso);

        // salvo
        corsoService.saveWithDocente(nuovo);
        //
        return ResponseEntity.ok(corso);
    }


    // PUT - modifica
    @PutMapping("/{id}/edit")
    public ResponseEntity<CorsoDTO> showEdit(@PathVariable Long id, @RequestBody CorsoDTO aggiornato) {
        Corso corso = corsoService.get(id);

        //
        corso.setNome(aggiornato.getNome());
        corso.setAnno_accademico(aggiornato.getAnno_accademico());
        corso.setIdDoc(aggiornato.getIdDoc());
        corso.setDocente_nome(aggiornato.getDocente_nome());
        corso.setDocente_cognome(aggiornato.getDocente_cognome());

        corsoService.saveWithDocente(corso);
        return ResponseEntity.ok(aggiornato);
    }


    // DELETE
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        corsoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

