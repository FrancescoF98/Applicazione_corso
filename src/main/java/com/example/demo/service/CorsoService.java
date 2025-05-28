package com.example.demo.service;

import com.example.demo.data.entity.Corso;
import com.example.demo.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoService {


    @Autowired
    CorsoRepository corsoRepository;

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

