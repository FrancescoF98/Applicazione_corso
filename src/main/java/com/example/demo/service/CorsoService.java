package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.CorsoDiscente;
import com.example.demo.repository.CorsoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorsoService {


    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    CorsoDiscenteService corso_discente_service;

    @Autowired
    WebClient webClient;


    public void saveWithDocenteAndDiscenti(Corso corso){

        System.out.println("");
        System.out.println("------------------------------------------------");
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
            System.out.println("> Il docente esiste ed è: " + corso.getDocente_nome());
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
            System.out.println("> Il docente NON esisteva ed è stato creato col nome: " + corso.getDocente_nome());
        }

        System.out.println(" - - ");


        List<DiscenteDTO> lista_discenti = corso.getDiscenti();
        List<DiscenteDTO> discenti_validati = new ArrayList<>();

        // Discenti ---------------------------------------------------------------
        for (DiscenteDTO discente_analizzato : lista_discenti) {
            try {
                DiscenteDTO discente = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/discenti/search")
                                .queryParam("nome", discente_analizzato.getNome())
                                .queryParam("cognome", discente_analizzato.getCognome())
                                .queryParam("matricola", discente_analizzato.getMatricola())
                                .build())
                        .retrieve()
                        .bodyToMono(DiscenteDTO.class)
                        .block();
                discente_analizzato.setId(discente.getId());
                discenti_validati.add(discente_analizzato);

                System.out.println("> Il discente esiste ed è: [ " + discente_analizzato.getNome() + " " + discente_analizzato.getCognome() + " ] - Matricola: [ " + discente_analizzato.getMatricola() + " ] - ID: [ " + discente_analizzato.getId() + " ]");
            } catch (WebClientResponseException.NotFound ex) {
                // Discente not found — create it
                DiscenteDTO nuovo_discente = new DiscenteDTO();
                nuovo_discente.setNome(discente_analizzato.getNome());
                nuovo_discente.setCognome(discente_analizzato.getCognome());
                nuovo_discente.setMatricola(discente_analizzato.getMatricola());

                DiscenteDTO discente = webClient.post()
                        .uri("/discenti/new")
                        .bodyValue(nuovo_discente)
                        .retrieve()
                        .bodyToMono(DiscenteDTO.class)
                        .block();


                DiscenteDTO discente2 = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/discenti/search")
                                .queryParam("nome", discente_analizzato.getNome())
                                .queryParam("cognome", discente_analizzato.getCognome())
                                .queryParam("matricola", discente_analizzato.getMatricola())
                                .build())
                        .retrieve()
                        .bodyToMono(DiscenteDTO.class)
                        .block();

                discente_analizzato.setId(discente2.getId());
                discenti_validati.add(discente_analizzato);
                System.out.println("> Il discente NON ESISTEVA ed è stato creato: [ " + discente_analizzato.getNome() + " " + discente_analizzato.getCognome() + " ] - Matricola: [ " + discente_analizzato.getMatricola() + " ] - ID: [ " + discente_analizzato.getId() + " ]");



            }
        }


        System.out.println("------------------------------------------------");
        System.out.println("");

        // Assegno la lista di discenti al corso
        corso.setDiscenti(discenti_validati);

        // salvo il corso
        save(corso);

        // Inserisco gli ID nella tabella CorsoDiscente
        riempi_corso_discente(corso);
    }

    public void riempi_corso_discente(Corso corso){
        System.out.println("");
        System.out.println("---------- Riempimento CorsoDiscente -------------");
        for (int i = 0; i < corso.getDiscenti().size(); i++) {
            CorsoDiscente nuovo_cs = new CorsoDiscente();

            nuovo_cs.setId_corso(corso.getId());
            nuovo_cs.setId_discente(corso.getDiscenti().get(i).getId());


            System.out.println(" > corso_discente = id_corso [" + corso.getId() + "] - id_discente [" + corso.getDiscenti().get(i).getId() + "]");

            corso_discente_service.save(nuovo_cs);
        }
        System.out.println("----------------------------------------------");
        System.out.println("");
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
        corso_discente_service.delete_corsi_by_id(id);
    }

    // Query custom
    public List<Corso> ordina_by_nome_asc() {
        return corsoRepository.ordina_by_nome_asc();
    }

    public List<Corso> ordina_by_nome_desc() {
        return corsoRepository.ordina_by_nome_desc();
    }
}

