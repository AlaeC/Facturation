package com.example.facturation.repository;
import com.example.facturation.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author LENOVO
 **/
public interface FactureRepository extends JpaRepository<Facture, Long> {

    // rechercher par client
    List<Facture> findByClientId(Long clientId);

    // rechercher par date
    List<Facture> findByDate(LocalDate date);
}