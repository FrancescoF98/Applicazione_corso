package com.example.demo.data.entity;

import com.example.demo.data.dto.DocenteDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corsi")
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer anno_accademico;

    @Column(name = "id_doc")
    private Long idDoc;

    @Column(name = "Nome Docente")
    private String docente_nome;

    @Column(name = "Cognome Docente")
    private String docente_cognome;

    /* costruttori */
    public Corso() {}

    public Corso(String nome, Integer anno_accademico, Long idDoc, String docente_nome, String docente_cognome) {
        this.nome = nome;
        this.anno_accademico = anno_accademico;
        this.idDoc = idDoc;
        this.docente_nome = docente_nome;
        this.docente_cognome = docente_cognome;
    }

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

    public String getDocente_nome() {
        return docente_nome;
    }

    public void setDocente_nome(String docente_nome) {
        this.docente_nome = docente_nome;
    }

    public String getDocente_cognome() {
        return docente_cognome;
    }

    public void setDocente_cognome(String docente_cognome) {
        this.docente_cognome = docente_cognome;
    }
}