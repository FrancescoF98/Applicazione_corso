package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CorsoService {


    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    WebClient webClient;


    public void saveWithDocente(Corso corso){
        System.out.println("L'id del docente è " + corso.getIdDoc());
        // controllo se il docente esiste
        DocenteDTO docente = webClient.get()
                .uri("/docenti/{id}", corso.getIdDoc())
                .retrieve()
                .bodyToMono(DocenteDTO.class)
                .block();

        System.out.println("Il nome del docente è " + docente.getNome());
        if (docente == null) {
            throw new RuntimeException("Docente not found");
        } else {
            save(corso);
        }
    }

    public List<Corso> findAll() {
        return corsoRepository.findAll();
    }

    public Corso get(Long id) {
        return corsoRepository.findById(id).orElseThrow();
    }

    public Corso save(Corso s) {
        return corsoRepository.save(s);
    }

    public void delete(Long id) {
        corsoRepository.deleteById(id);
    }

    // Query custom
    public List<Corso> ordina_by_nome_asc() {
        return corsoRepository.ordina_by_nome_asc();
    }

    public List<Corso> ordina_by_nome_desc() {
        return corsoRepository.ordina_by_nome_desc();
    }
}

