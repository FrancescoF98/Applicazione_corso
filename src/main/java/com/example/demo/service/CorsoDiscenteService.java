package com.example.demo.service;

import com.example.demo.data.dto.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.CorsoDiscente;
import com.example.demo.repository.CorsoDiscenteRepository;
import com.example.demo.repository.CorsoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class CorsoDiscenteService {

    @Autowired
    CorsoDiscenteRepository corso_discente_repository;

    @Autowired
    WebClient webClient;



    // Per recuperare i discenti a partire dall'id in CorsoDiscente
    public List<DiscenteDTO> prendi_lista_studenti(Long id_corso) {

        // mi prendo la lista degli id
        List<Long> lista_id = corso_discente_repository.recupera_lista_id_discenti(id_corso);

        // creo una lista di discenti vuoti
        List<DiscenteDTO> discenti_recuperati = new ArrayList<>();

        // riempio la lista
        for (Long id : lista_id) {
            DiscenteDTO nuovo_discente = recupera_discente_by_id(id);
            discenti_recuperati.add(nuovo_discente);

        }

        return discenti_recuperati;
    }


    // per recuperare un discente a partire dal suo id
    public DiscenteDTO recupera_discente_by_id(Long id){
        DiscenteDTO discente = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discenti/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(DiscenteDTO.class)
                .block();
        return discente;
    }

    public List<CorsoDiscente> findAll() {
        return corso_discente_repository.findAll();
    }

    public CorsoDiscente get(Long id) {
        return corso_discente_repository.findById(id).orElseThrow();
    }

    public CorsoDiscente save(CorsoDiscente s) {
        return corso_discente_repository.save(s);
    }

    public void delete(Long id) {
        corso_discente_repository.deleteById(id);
    }

    @Transactional
    public void delete_corsi_by_id(Long id) {
        corso_discente_repository.delete_corsi_by_id(id);
    }


}
