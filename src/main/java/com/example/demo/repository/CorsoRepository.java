package com.example.demo.repository;


import com.example.demo.data.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long> {

    @Query(value = "SELECT * FROM corsi ORDER BY nome ASC", nativeQuery = true)
    List<Corso> ordina_by_nome_asc();

    @Query(value = "SELECT * FROM corsi ORDER BY nome DESC", nativeQuery = true)
    List<Corso> ordina_by_nome_desc();

    // Per quando si elimina un docente
    @Query(value = "SELECT * FROM corsi WHERE id_doc = ?1", nativeQuery = true)
    List<Corso> trova_corsi_con_id_docente(Long id_doc);
}

