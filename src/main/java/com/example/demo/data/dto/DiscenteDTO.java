package com.example.demo.data.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class DiscenteDTO {

    //@JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cognome")
    private String cognome;

    @JsonProperty("matricola")
    private Integer matricola;

    @JsonProperty("eta")
    private Integer eta;


    /* costruttori */
    public DiscenteDTO() {}

    public DiscenteDTO(Long id, String nome, String cognome, Integer matricola, Integer eta) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.eta = eta;
    }

    @Override
    public String toString() {
        return "DiscenteDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", matricola=" + matricola +
                ", eta=" + eta +
                '}';
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


    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    public Integer getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }


}
