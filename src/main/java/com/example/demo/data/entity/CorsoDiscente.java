package com.example.demo.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "corso_discente")
public class CorsoDiscente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long id_corso;

    @Column(nullable = false)
    private Long id_discente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_corso() {
        return id_corso;
    }

    public void setId_corso(Long id_corso) {
        this.id_corso = id_corso;
    }

    public Long getId_discente() {
        return id_discente;
    }

    public void setId_discente(Long id_discente) {
        this.id_discente = id_discente;
    }
}
