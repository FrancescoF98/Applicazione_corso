package com.example.demo.converter;

import com.example.demo.data.dto.CorsoDTO;
import com.example.demo.data.entity.Corso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    // Da ENTITY a DTO - lista
    public List<CorsoDTO> corso_convert_to_dto(List<Corso> elementi_in_entrata){

        // Creo una lista vuota
        List<CorsoDTO> elementi_convertiti = new ArrayList<>();

        // la riempio di elementi convertiti
        for (int i = 0; i < elementi_in_entrata.size(); i++) {

            Long id = elementi_in_entrata.get(i).getId();
            String nome = elementi_in_entrata.get(i).getNome();
            Integer anno_accademico = elementi_in_entrata.get(i).getAnno_accademico();
            Long idDoc = elementi_in_entrata.get(i).getIdDoc();

            CorsoDTO nuovo_elemento = new CorsoDTO(id, nome, anno_accademico, idDoc);

            elementi_convertiti.add(nuovo_elemento);
        }

        // restituisco la lista convertita
        return elementi_convertiti;
    }

    // Da ENTITY a DTO - singolo elemento
    public CorsoDTO corso_convert_to_dto(Corso elementi_in_entrata){

        CorsoDTO nuovo_elemento = new CorsoDTO();

        nuovo_elemento.setId(elementi_in_entrata.getId());
        nuovo_elemento.setNome(elementi_in_entrata.getNome());
        nuovo_elemento.setIdDoc(elementi_in_entrata.getIdDoc());

        return nuovo_elemento;
    }

    // Da DTO a ENTITY - lista
    public List<Corso> corso_convert_to_entity(List<CorsoDTO> elementi_in_entrata){

        // Creo una lista vuota
        List<Corso> elementi_convertiti = new ArrayList<>();

        // la riempio di elementi convertiti
        for (int i = 0; i < elementi_in_entrata.size(); i++) {

            Long id = elementi_in_entrata.get(i).getId();
            String nome = elementi_in_entrata.get(i).getNome();
            Integer anno_accademico = elementi_in_entrata.get(i).getAnno_accademico();
            Long idDoc = elementi_in_entrata.get(i).getIdDoc();
            //List<Discente> discenti = discente_convert_to_entity(elementi_in_entrata.get(i).getDiscenti());

            Corso nuovo_elemento = new Corso();
            nuovo_elemento.setId(id);
            nuovo_elemento.setNome(nome);
            nuovo_elemento.setAnno_accademico(anno_accademico);
            nuovo_elemento.setIdDoc(idDoc);

            elementi_convertiti.add(nuovo_elemento);
        }

        // restituisco la lista convertita
        return elementi_convertiti;
    }

    // Da DTO a ENTITY - singolo elemento
    public Corso corso_convert_to_entity(CorsoDTO elementi_in_entrata){

        Long id = elementi_in_entrata.getId();
        String nome = elementi_in_entrata.getNome();
        Integer anno_accademico = elementi_in_entrata.getAnno_accademico();
        Long idDoc = elementi_in_entrata.getIdDoc();

        Corso nuovo_elemento = new Corso();

        nuovo_elemento.setNome(nome);
        nuovo_elemento.setAnno_accademico(anno_accademico);
        nuovo_elemento.setIdDoc(idDoc);

        return nuovo_elemento;
    }

    // --------------------------------------------------------------------------------------------------
}
