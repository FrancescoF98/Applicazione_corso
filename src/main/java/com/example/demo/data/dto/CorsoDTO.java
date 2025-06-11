package com.example.demo.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;

import java.util.ArrayList;
import java.util.List;

public class CorsoDTO {

    //@JsonIgnore
    private Long id;

    private String nome;

    private Integer anno_accademico;

    private Long idDoc;

    /* costruttori */
    public CorsoDTO() {}

    public CorsoDTO(Long id, String nome, Integer anno_accademico, Long idDoc) {
        this.id = id;
        this.nome = nome;
        this.anno_accademico = anno_accademico;
        this.idDoc = idDoc;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnno_accademico() {
        return anno_accademico;
    }

    public void setAnno_accademico(Integer anno_accademico) {
        this.anno_accademico = anno_accademico;
    }

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }
}