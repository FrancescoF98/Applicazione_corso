package com.example.demo.repository;


import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.CorsoDiscente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorsoDiscenteRepository extends JpaRepository<CorsoDiscente, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM corso_discente WHERE id_corso = ?1", nativeQuery = true)
    void delete_corsi_by_id(Long id);

    @Query(value = "SELECT id_discente FROM corso_discente WHERE id_corso = ?1", nativeQuery = true)
    List<Long> recupera_lista_id_discenti(Long id_corso);
}

