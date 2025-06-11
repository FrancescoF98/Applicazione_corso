package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class CorsoService {


    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    WebClient webClient;


    public void saveWithDocente(Corso corso){
//        // controllo se il docente esiste
//        DocenteDTO docente = webClient.get()
//                .uri("/docenti/{nome}_{cognome}", corso.getDocente_nome(), corso.getDocente_cognome())
//                .retrieve()
//                .bodyToMono(DocenteDTO.class)
//                .block();
//
//        if (docente == null) {
//            DocenteDTO nuovo_docente = new DocenteDTO();
//            nuovo_docente.setNome(corso.getDocente_nome());
//            nuovo_docente.setCognome(corso.getDocente_cognome());
//
//            // se non esiste lo creo
//            DocenteDTO new_docente = webClient.post()
//                    .uri("/docenti/new")
//                    .bodyValue(nuovo_docente) // sends JSON body
//                    .retrieve()
//                    .bodyToMono(DocenteDTO.class)
//                    .block();
////          throw new RuntimeException("Docente not found");
//        } else {
//            save(corso);
//        }



        try {
            DocenteDTO docente = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/docenti/search")
                            .queryParam("nome", corso.getDocente_nome())
                            .queryParam("cognome", corso.getDocente_cognome())
                            .build())
                    .retrieve()
                    .bodyToMono(DocenteDTO.class)
                    .block();
            save(corso);
            System.out.println("Il docente esiste ed è: " + corso.getDocente_nome());
        } catch (WebClientResponseException.NotFound ex) {
            // Docente not found — create it
            DocenteDTO nuovo_docente = new DocenteDTO();
            nuovo_docente.setNome(corso.getDocente_nome());
            nuovo_docente.setCognome(corso.getDocente_cognome());

            DocenteDTO docente = webClient.post()
                    .uri("/docenti/new")
                    .bodyValue(nuovo_docente)
                    .retrieve()
                    .bodyToMono(DocenteDTO.class)
                    .block();
            save(corso);
            System.out.println("Il docente NON esisteva ed è stato creato col nome: " + corso.getDocente_nome());
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

