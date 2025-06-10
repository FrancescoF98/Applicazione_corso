package com.example.demo.service;

import com.example.demo.data.dto.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CorsoService {


    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String docenteServiceUrl = "http://localhost:8081/docenti/";

    public DocenteDTO getDocenteById(Long id) {
        try {
            return restTemplate.getForObject(docenteServiceUrl + id, DocenteDTO.class);
        } catch (HttpClientErrorException e) {
            // Handle not found, bad request, etc.
            return null;
        }
    }

//    public Corso createCorsoWithDocente(Long docenteId) {
//        DocenteDTO docente = getDocenteById(docenteId);
//        if (docente == null) {
//            throw new RuntimeException("Docente not found");
//        }
//
//        Corso corso = new Corso();
//        corso.setDocente(docente);
//        // Save corso to DB
//        return corso;
//    }

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

